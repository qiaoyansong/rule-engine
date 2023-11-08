package com.rule.engine.biz.event;

import com.rule.engine.api.enums.EventSourceEnum;
import com.rule.engine.biz.bo.EventBO;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/8 5:23 下午
 * description：
 */
public interface EventBaseInfoService {

    /**
     * 获取最匹配的事件
     *
     * @param eventSource #{@link EventSourceEnum#code}
     * @param eventName   事件名或接口全限定名
     * @param body        事件body或接口入参
     * @return
     */
    EventBO getBodyMatchEvent(Integer eventSource, String eventName, String body);
}
