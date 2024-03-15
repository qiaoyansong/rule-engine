package com.rule.engine.biz.indicator.calc.local.helper;

import com.google.common.collect.Lists;
import com.rule.engine.api.enums.ErrorCodeEnum;
import com.rule.engine.biz.exception.BizException;
import com.rule.engine.biz.indicator.calc.local.annotations.LocalFunctionClazz;
import com.rule.engine.biz.indicator.calc.local.annotations.LocalFunctionParam;
import com.rule.engine.biz.indicator.calc.local.annotations.LocalFunctionTemplate;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/3/6 5:05 下午
 * description：
 */
@Component
public class LocalJavaFunctionHelper implements InitializingBean, ApplicationContextAware  {

    private static ApplicationContext context;

    private Map<String, LocalFunctionDefinition> bizAdapter = new ConcurrentHashMap();

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalJavaFunctionHelper.class);


    /**
     * 获取单个 事件服务的定义 信息
     */
    public LocalFunctionDefinition getDefinition(String eventClazzName) {
        if (StringUtils.isBlank(eventClazzName)) {
            return null;
        }
        return bizAdapter.get(eventClazzName);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (null != context) {
            Map<String, Object> map = context.getBeansWithAnnotation(LocalFunctionClazz.class);
            if (MapUtils.isEmpty(map)) {
                return;
            }
            map.forEach((key, bean) -> {
                Method[] methods = bean.getClass().getDeclaredMethods();
                Arrays.stream(methods).forEach(method -> {
                    LocalFunctionTemplate methodAnnotation = method.getAnnotation(LocalFunctionTemplate.class);
                    if (methodAnnotation == null) {
                        return;
                    }
                    LocalFunctionDefinition put = bizAdapter.put(methodAnnotation.functionName(), converterTo(bean, method, methodAnnotation));
                    if (put != null) {
                        LOGGER.error("LocalJavaFunctionHelper#afterPropertiesSet, same functionName");
                        throw new BizException(ErrorCodeEnum.SYSTEM_ERROR);
                    }
                });
            });
        }
    }

    private LocalFunctionDefinition converterTo(Object bean, Method method, LocalFunctionTemplate methodAnnotation) {
        // 方法体的 形参
        List<String> argsParam = getArgs(method);
        LocalFunctionDefinition info = new LocalFunctionDefinition();
        info.setFunctionName(methodAnnotation.functionName());
        info.setFunctionDesc(methodAnnotation.functionDesc());
        info.setFunctionValueType(methodAnnotation.functionValueType().getCode());
        info.setArgs(argsParam);
        info.setMethod(method);
        info.setObject(bean);
        return info;
    }

    private static List<String> getArgs(Method method) {
        // 方法体的 注解
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if (parameterAnnotations.length <= 0) {
            return Lists.newArrayList();
        }
        ArrayList<String> rtn = Lists.newArrayList();
        Arrays.stream(parameterAnnotations).forEach(val -> {
            if (val == null || val.length <= 0) {
                return;
            }
            Arrays.stream(val).forEach(value -> {
                if (!(value instanceof LocalFunctionParam)) {
                    return;
                }
                LocalFunctionParam annotation = (LocalFunctionParam) value;
                if (StringUtils.isBlank(annotation.argName())) {
                    return;
                }
                rtn.add(annotation.argName());
            });
        });
        return rtn;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
