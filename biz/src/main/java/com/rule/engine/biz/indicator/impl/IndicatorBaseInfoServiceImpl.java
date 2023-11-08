package com.rule.engine.biz.indicator.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.rule.engine.api.enums.IndicatorConfigStatusEnum;
import com.rule.engine.biz.beanmapper.IndicatorBeanMapper;
import com.rule.engine.biz.bo.IndicatorBaseInfoBO;
import com.rule.engine.biz.indicator.IndicatorBaseInfoService;
import com.rule.engine.common.constant.CommonConstant;
import com.rule.engine.common.utils.ThreadPoolUtil;
import com.rule.engine.dal.mapper.IndicatorBaseInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/8 3:51 下午
 * description：
 */
@Service
public class IndicatorBaseInfoServiceImpl implements IndicatorBaseInfoService {

    @Resource
    private IndicatorBaseInfoMapper indicatorBaseInfoMapper;

    @Resource
    private IndicatorBeanMapper indicatorBeanMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(IndicatorBaseInfoServiceImpl.class);

    private LoadingCache<Long, IndicatorBaseInfoBO> indicatorInfoCache;

    private static final ListeningExecutorService listeningExecutor;

    static {
        listeningExecutor = MoreExecutors.listeningDecorator(ThreadPoolUtil.INDICATOR_BASE_INFO_REFRESH);
    }

    @Override
    public IndicatorBaseInfoBO queryIndicatorBaseInfo(Long indicatorId) {
        IndicatorBaseInfoBO indicatorBaseInfoBO = null;
        try {
            indicatorBaseInfoBO = indicatorInfoCache.get(indicatorId);
        } catch (Exception e) {
            LOGGER.error(
                    "IndicatorBaseInfoService#queryIndicatorBaseInfo load indicator base info from cache error, indicatorId={}, e", indicatorId, e);
        }
        return indicatorBaseInfoBO;
    }

    @PostConstruct
    private void initIndicatorBaseInfoService() {
        indicatorInfoCache = CacheBuilder.newBuilder().maximumSize(800)
                .expireAfterWrite(CommonConstant.INDICATOR_LOCAL_CACHE_TIME, TimeUnit.MINUTES)
                .refreshAfterWrite(CommonConstant.INDICATOR_LOCAL_CACHE_REFRESH_TIME, TimeUnit.MINUTES)
                .build(new CacheLoader<Long, IndicatorBaseInfoBO>() {
                    @Override
                    public IndicatorBaseInfoBO load(Long indicatorId) throws Exception {
                        return getIndicatorBaseInfoBOByIndicatorId(indicatorId);
                    }

                    @Override
                    public ListenableFuture<IndicatorBaseInfoBO> reload(Long indicatorId,
                                                                        IndicatorBaseInfoBO oldValue) throws Exception {
                        IndicatorBaseInfoRefreshCallable refreshCallable = new IndicatorBaseInfoRefreshCallable(
                                indicatorId);
                        return listeningExecutor.submit(refreshCallable);
                    }
                });
    }

    private IndicatorBaseInfoBO getIndicatorBaseInfoBOByIndicatorId(Long indicatorId) {
        return indicatorBeanMapper.indicatorBaseInfoDO2IndicatorBaseInfoBO(indicatorBaseInfoMapper.queryIndicatorByIndicatorId(indicatorId,
                IndicatorConfigStatusEnum.STATUS_ACTIVE.getCode()));
    }

    private class IndicatorBaseInfoRefreshCallable implements Callable<IndicatorBaseInfoBO> {

        private Long indicatorId;

        public IndicatorBaseInfoRefreshCallable(Long indicatorId) {
            this.indicatorId = indicatorId;
        }

        @Override
        public IndicatorBaseInfoBO call() throws Exception {
            return getIndicatorBaseInfoBOByIndicatorId(indicatorId);
        }
    }
}

