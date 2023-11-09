package com.rule.engine.biz.indicator.calc;

import com.rule.engine.biz.bo.EventParseIndicatorInfoBO;

import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/9 10:45 上午
 * description：事件解析指标Service
 */
public interface EventParsingIndicatorInfoService {

    /**
     * 获取事件ID关联的所有事件解析指标。
     *
     * @param eventId
     * @return
     */
    List<EventParseIndicatorInfoBO> listEventIndicatorByEventId(Long eventId);
}
