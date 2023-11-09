package com.rule.engine.biz.indicator.calc;

import com.rule.engine.api.enums.EventSourceEnum;
import com.rule.engine.api.result.indicator.IndicatorResultInfo;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/9 3:33 下午
 * description：
 */
public interface IndicatorComputeBizService {

    /**
     * 根据事件 + 入参 批量计算指标。
     *
     * @param eventSource #{@link EventSourceEnum#code}
     * @param eventName   事件名或接口全限定名
     * @param body
     * @return
     */
    IndicatorResultInfo handleBatchIndicatorCalculation(Integer eventSource, String eventName, String body);
}
