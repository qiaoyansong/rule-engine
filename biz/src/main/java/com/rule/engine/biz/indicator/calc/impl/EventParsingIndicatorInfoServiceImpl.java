package com.rule.engine.biz.indicator.calc.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.rule.engine.api.enums.IndicatorConfigStatusEnum;
import com.rule.engine.biz.bo.EventParseIndicatorInfoBO;
import com.rule.engine.biz.indicator.calc.EventParsingIndicatorInfoService;
import com.rule.engine.common.constant.CommonConstant;
import com.rule.engine.common.utils.ThreadPoolUtil;
import com.rule.engine.dal.domain.IndicatorBaseInfoDO;
import com.rule.engine.dal.domain.IndicatorRuleEventParsingInfo;
import com.rule.engine.dal.mapper.IndicatorBaseInfoMapper;
import com.rule.engine.dal.mapper.IndicatorRuleEventParsingInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/9 11:02 上午
 * description：
 */
@Service
public class EventParsingIndicatorInfoServiceImpl implements EventParsingIndicatorInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventParsingIndicatorInfoService.class);

    private LoadingCache<Long, List<EventParseIndicatorInfoBO>> eventIndicatorRuleCache;

    @Resource
    private IndicatorRuleEventParsingInfoMapper indicatorRuleEventParsingInfoMapper;

    @Resource
    private IndicatorBaseInfoMapper indicatorBaseInfoMapper;

    private static final ListeningExecutorService listeningExecutor;

    static {
        listeningExecutor = MoreExecutors.listeningDecorator(ThreadPoolUtil.EVENT_PARSING_INDICATOR_INFO_REFRESH);
    }

    @Override
    public List<EventParseIndicatorInfoBO> listEventIndicatorByEventId(Long eventId) {
        if (Objects.isNull(eventId)) {
            return Collections.emptyList();
        }

        List<EventParseIndicatorInfoBO> eventIndicatorInfoBOList = null;
        try {
            eventIndicatorInfoBOList = eventIndicatorRuleCache.get(eventId);
        } catch (Exception e) {
            LOGGER.error("[EventIndicatorInfoService#listEventIndicatorByEventId] error, eventId={}, e=",
                    eventId, e);
        }

        return eventIndicatorInfoBOList;
    }

    /**
     * 根据时间id获取 解析指标规则BO
     *
     * @param eventId
     * @return
     */
    private List<EventParseIndicatorInfoBO> getEventParseIndicatorInfoBOSByEventId(Long eventId) {
        List<IndicatorRuleEventParsingInfo> indicatorRuleInfoDOList = indicatorRuleEventParsingInfoMapper
                .listEventIndicatorRuleByEventId(eventId, IndicatorConfigStatusEnum.STATUS_ACTIVE.getCode());
        Set<Long> indicatorIdSet = extractIndicatorIdSet(indicatorRuleInfoDOList);
        if (CollectionUtils.isEmpty(indicatorIdSet)) {
            return Collections.emptyList();
        }
        List<IndicatorBaseInfoDO> indicatorBaseInfoDOList = indicatorBaseInfoMapper.listIndicatorBaseInfo(
                Lists.newArrayList(indicatorIdSet), IndicatorConfigStatusEnum.STATUS_ACTIVE.getCode());
        if (CollectionUtils.isEmpty(indicatorBaseInfoDOList)) {
            return Collections.emptyList();
        }
        return transformToEventIndicatorInfoBO(indicatorRuleInfoDOList, indicatorBaseInfoDOList);
    }

    private List<EventParseIndicatorInfoBO> transformToEventIndicatorInfoBO(List<IndicatorRuleEventParsingInfo> indicatorRuleInfoDOList, List<IndicatorBaseInfoDO> indicatorBaseInfoDOList) {
        if (CollectionUtils.isEmpty(indicatorRuleInfoDOList) || CollectionUtils.isEmpty(indicatorBaseInfoDOList)) {
            return Collections.emptyList();
        }
        Map<Long, IndicatorBaseInfoDO> indicatorBaseInfoDOMap = Maps.uniqueIndex(indicatorBaseInfoDOList, IndicatorBaseInfoDO::getId);
        List<EventParseIndicatorInfoBO> eventIndicatorInfoBOList = Lists.newArrayList();
        for (IndicatorRuleEventParsingInfo ruleInfoDO : indicatorRuleInfoDOList) {
            Long indicatorId = ruleInfoDO.getIndicatorId();
            if (indicatorId == null || indicatorId.longValue() <= 0L) {
                continue;
            }
            IndicatorBaseInfoDO baseInfoDO = indicatorBaseInfoDOMap.get(indicatorId);
            if (baseInfoDO == null) {
                continue;
            }
            EventParseIndicatorInfoBO indicatorInfoBO = new EventParseIndicatorInfoBO();
            indicatorInfoBO.setIndicatorId(indicatorId);
            indicatorInfoBO.setIndicatorType(baseInfoDO.getIndicatorType());
            indicatorInfoBO.setIndicatorName(baseInfoDO.getIndicatorName());
            indicatorInfoBO.setIndicatorReturnType(baseInfoDO.getIndicatorReturnType());
            indicatorInfoBO.setMarshallField(ruleInfoDO.getMarshallField());
            indicatorInfoBO.setEventId(ruleInfoDO.getEventId());
            eventIndicatorInfoBOList.add(indicatorInfoBO);
        }
        return eventIndicatorInfoBOList;
    }

    /**
     * 根据事件解析指标规则获取指标ID
     *
     * @param indicatorRuleInfoDOList
     * @return
     */
    private Set<Long> extractIndicatorIdSet(List<IndicatorRuleEventParsingInfo> indicatorRuleInfoDOList) {
        if (CollectionUtils.isEmpty(indicatorRuleInfoDOList)) {
            return null;
        }
        return indicatorRuleInfoDOList.stream()
                .filter(indicatorRuleEventParsingInfo -> Objects.nonNull(indicatorRuleEventParsingInfo) && Objects.nonNull(indicatorRuleEventParsingInfo.getIndicatorId()))
                .map(IndicatorRuleEventParsingInfo::getIndicatorId)
                .collect(Collectors.toSet());
    }

    @PostConstruct
    private void initEventIndicatorInfoService() {
        eventIndicatorRuleCache = CacheBuilder.newBuilder()
                .maximumSize(200)
                .expireAfterWrite(CommonConstant.INDICATOR_LOCAL_CACHE_TIME, TimeUnit.MINUTES)
                .refreshAfterWrite(CommonConstant.INDICATOR_LOCAL_CACHE_TIME, TimeUnit.MINUTES)
                .build(new CacheLoader<Long, List<EventParseIndicatorInfoBO>>() {
                    @Override
                    public List<EventParseIndicatorInfoBO> load(Long eventId) throws Exception {
                        return getEventParseIndicatorInfoBOSByEventId(eventId);
                    }

                    @Override
                    public ListenableFuture<List<EventParseIndicatorInfoBO>> reload(Long eventId, List<EventParseIndicatorInfoBO> oldValue) throws Exception {
                        EventParseIndicatorInfoRefreshCallable refreshCallable = new EventParseIndicatorInfoRefreshCallable(
                                eventId);
                        return listeningExecutor.submit(refreshCallable);
                    }
                });
    }

    private class EventParseIndicatorInfoRefreshCallable implements Callable<List<EventParseIndicatorInfoBO>> {

        private Long eventId;

        public EventParseIndicatorInfoRefreshCallable(Long eventId) {
            this.eventId = eventId;
        }

        @Override
        public List<EventParseIndicatorInfoBO> call() throws Exception {
            return getEventParseIndicatorInfoBOSByEventId(eventId);
        }
    }
}
