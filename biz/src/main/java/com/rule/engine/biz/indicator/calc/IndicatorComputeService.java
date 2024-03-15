package com.rule.engine.biz.indicator.calc;

import com.rule.engine.api.result.indicator.IndicatorResultInfo;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/9 3:33 下午
 * description：
 */
public interface IndicatorComputeService {

    /**
     * 根据事件 + 入参 批量计算指标。
     *
     * @param eventName   事件名或接口全限定名
     * @param body
     * @return
     */
    IndicatorResultInfo handleBatchIndicatorCalculation(String eventName, String body);
}
