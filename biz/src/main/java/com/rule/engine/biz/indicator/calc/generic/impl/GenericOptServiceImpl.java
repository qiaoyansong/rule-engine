package com.rule.engine.biz.indicator.calc.generic.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.rule.engine.api.constant.RpcCode;
import com.rule.engine.api.enums.IndicatorValueTypeEnum;
import com.rule.engine.api.mis.param.generic.RemoteMethodCalculationParam;
import com.rule.engine.api.result.RpcResult;
import com.rule.engine.biz.beanmapper.RemoteMethodCalculationBeanMapper;
import com.rule.engine.biz.bo.CalculationResultBO;
import com.rule.engine.biz.bo.RemoteMethodCalculationInfoBO;
import com.rule.engine.biz.indicator.calc.generic.GenericOptService;
import com.rule.engine.common.constant.CommonConstant;
import com.rule.engine.common.utils.ThreadPoolUtil;
import com.rule.engine.dal.mapper.RemoteMethodInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;
import org.apache.dubbo.spring.boot.autoconfigure.DubboConfigurationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/1/29 5:34 下午
 * description：
 */
@Service
public class GenericOptServiceImpl implements GenericOptService {

    @Resource
    private RemoteMethodInfoMapper remoteMethodInfoMapper;

    @Resource
    private RemoteMethodCalculationBeanMapper remoteMethodCalculationBeanMapper;

    @Resource
    private DubboConfigurationProperties dubboConfigurationProperties;

    private ApplicationConfig applicationConfig;

    private RegistryConfig registryConfig;

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericOptServiceImpl.class);

    private LoadingCache<String, ReferenceConfig<GenericService>> genericServiceLoadingCache;

    private static final ListeningExecutorService listeningExecutor;

    static {
        listeningExecutor = MoreExecutors.listeningDecorator(ThreadPoolUtil.GENERIC_SERVICE_REFRESH);
    }

    @Override
    public Boolean addRemoteMethod(RemoteMethodCalculationParam param) {
        return remoteMethodInfoMapper.insertSelective(remoteMethodCalculationBeanMapper.remoteMethodCalculationParam2RemoteMethodInfo(param));
    }

    @Override
    public CalculationResultBO handleRemoteMethodCalculation(RemoteMethodCalculationInfoBO calculationInfoBO) {
        ImmutablePair<String, String> methodServiceAndMethodName = loadRemoteMethodServiceAndMethodName(calculationInfoBO.getRemoteMethodName());
        if (Objects.isNull(methodServiceAndMethodName)) {
            LOGGER.warn("GenericOptServiceImpl#handleRemoteMethodCalculation methodServiceAndMethodName is empty, calculationInfoBO={}", JSON.toJSONString(calculationInfoBO));
            return null;
        }
        String serviceName = methodServiceAndMethodName.getLeft();
        String methodName = methodServiceAndMethodName.getRight();
        if (StringUtils.isBlank(serviceName) || StringUtils.isBlank(methodName)) {
            LOGGER.warn("GenericOptServiceImpl#handleRemoteMethodCalculation serviceName or methodName is empty, calculationInfoBO={}", JSON.toJSONString(calculationInfoBO));
            return null;
        }
        List<String> argumentTypes = transformToArgumentTypes(calculationInfoBO.getArgumentList());
        List<Object> argumentValues = transformToArgumentValues(calculationInfoBO.getArgumentList());

        Object result = invokeRemoteMethod(serviceName, methodName, argumentTypes, argumentValues,
                calculationInfoBO.getRemoteMethodReturnType());

        CalculationResultBO calculationResultBO = new CalculationResultBO();
        calculationResultBO.setDataType(calculationInfoBO.getRemoteMethodReturnType());
        calculationResultBO.setValue(result);
        return calculationResultBO;
    }

    private Object invokeRemoteMethod(String serviceName, String methodName, List<String> argumentTypes, List<Object> argValues, Integer dataType) {
        if (StringUtils.isBlank(serviceName) || StringUtils.isBlank(methodName)) {
            return null;
        }
        ReferenceConfig<GenericService> genericServiceReferenceConfig = null;
        try {
            genericServiceReferenceConfig = genericServiceLoadingCache.get(serviceName);
        } catch (ExecutionException e) {
        }
        GenericService genericService = null;
        if (Objects.nonNull(genericServiceReferenceConfig)) {
            genericService = genericServiceReferenceConfig.get();
        }
        if (Objects.isNull(genericService)) {
            LOGGER.warn("GenericOptServiceImpl#invokeRemoteMethod generic service is null, serviceName={}, methodName={}, dataType={}, argTypes={}", serviceName, methodName, dataType, JSON.toJSONString(argumentTypes));
            return null;
        }

        Object result = genericService.$invoke(methodName, argumentTypes.toArray(new String[argumentTypes.size()]),
                argValues.toArray(new Object[argValues.size()]));
        if (result == null) {
            LOGGER.warn("GenericOptServiceImpl#invokeRemoteMethod invoke remote method with empty result, serviceName={}, methodName={}, dataType={}, argTypes={}", serviceName, methodName, dataType, JSON.toJSONString(argumentTypes));
            return null;
        }

        String json = JSON.toJSONString(result);
        RpcResult rpcResult = JSON.parseObject(json, RpcResult.class);
        if (rpcResult == null || rpcResult.getCode() != RpcCode.SUCCESS) {
            LOGGER.warn(
                    "GenericOptServiceImpl#invokeRemoteMethod invoke remote method with error code, serviceName={}, methodName={}, dataType={}, argTypes={}",
                    serviceName, methodName, dataType, JSON.toJSONString(argumentTypes));
            return null;
        }
        return IndicatorValueTypeEnum.parseVal(dataType, rpcResult.getData());
    }

    private ImmutablePair<String, String> loadRemoteMethodServiceAndMethodName(String remoteMethodName) {
        if (StringUtils.isBlank(remoteMethodName)) {
            return null;
        }

        int lastIndex = remoteMethodName.lastIndexOf(".");
        if (lastIndex <= 0) {
            return null;
        }

        return ImmutablePair.of(remoteMethodName.substring(0, lastIndex), remoteMethodName.substring(lastIndex + 1));
    }

    private List<String> transformToArgumentTypes(List<RemoteMethodCalculationInfoBO.ArgumentBO> argumentList) {
        List<String> argumentNames = Lists.newArrayList();
        if (CollectionUtils.isEmpty(argumentList)) {
            return argumentNames;
        }

        for (RemoteMethodCalculationInfoBO.ArgumentBO argumentBO : argumentList) {
            if (argumentBO == null || StringUtils.isBlank(argumentBO.getArgName())) {
                return Collections.emptyList();
            }
            IndicatorValueTypeEnum indicatorValueTypeEnum = IndicatorValueTypeEnum.getByCode(argumentBO.getArgType());
            argumentNames.add(indicatorValueTypeEnum.getJavaTypeDesc());
        }

        return argumentNames;
    }

    private List<Object> transformToArgumentValues(List<RemoteMethodCalculationInfoBO.ArgumentBO> argumentList) {
        List<Object> arguments = Lists.newArrayList();

        for (RemoteMethodCalculationInfoBO.ArgumentBO argumentBO : argumentList) {
            if (argumentBO == null) {
                return Collections.emptyList();
            }

            Object argValue = IndicatorValueTypeEnum.parseVal(argumentBO.getArgType(), argumentBO.getArgValue());
            if (argValue == null) {
                return null;
            }

            arguments.add(argValue);
        }

        return arguments;
    }

    /**
     * 获取dubbo泛化调用的通用服务引用.
     *
     * @param serviceName 服务名字
     * @return
     */
    private ReferenceConfig<GenericService> loadGenericService(String serviceName) {
        if (Strings.isNullOrEmpty(serviceName)) {
            return null;
        }
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setApplication(applicationConfig);
        reference.setRegistry(registryConfig);
        reference.setInterface(serviceName);
        reference.setVersion("1.0.0");
        reference.setGeneric(true);
        reference.setTimeout(1900);
        reference.setRetries(0);
        return reference;
    }

    @PostConstruct
    private void initEventIndicatorInfoService() {
        genericServiceLoadingCache = CacheBuilder.newBuilder()
                .maximumSize(200)
                .expireAfterWrite(CommonConstant.INDICATOR_LOCAL_CACHE_TIME, TimeUnit.MINUTES)
                .refreshAfterWrite(CommonConstant.INDICATOR_LOCAL_CACHE_TIME, TimeUnit.MINUTES)
                .build(new CacheLoader<String, ReferenceConfig<GenericService>>() {
                    @Override
                    public ReferenceConfig<GenericService> load(String serviceName) throws Exception {
                        return loadGenericService(serviceName);
                    }

                    @Override
                    public ListenableFuture<ReferenceConfig<GenericService>> reload(String serviceName, ReferenceConfig<GenericService> oldValue) throws Exception {
                        EventParseIndicatorInfoRefreshCallable refreshCallable = new EventParseIndicatorInfoRefreshCallable(
                                serviceName);
                        return listeningExecutor.submit(refreshCallable);
                    }
                });
        // todo 不同dubbo配置 获取的方式可能不同，比如多注册中心 就需要dubboConfigurationProperties.getRegistries()
        applicationConfig = dubboConfigurationProperties.getApplication();
        registryConfig = dubboConfigurationProperties.getRegistry();
    }

    private class EventParseIndicatorInfoRefreshCallable implements Callable<ReferenceConfig<GenericService>> {

        private String serviceName;

        public EventParseIndicatorInfoRefreshCallable(String serviceName) {
            this.serviceName = serviceName;
        }

        @Override
        public ReferenceConfig<GenericService> call() throws Exception {
            return loadGenericService(serviceName);
        }
    }
}
