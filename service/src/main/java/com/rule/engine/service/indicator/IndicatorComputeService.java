package com.rule.engine.service.indicator;

import com.rule.engine.api.enums.EventSourceEnum;
import com.rule.engine.api.result.RpcResult;
import com.rule.engine.api.result.indicator.IndicatorResultInfo;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/9 3:11 下午
 * description：
 */
public interface IndicatorComputeService {

    /**
     * 批量计算指标。
     *
     * @param eventSource #{@link EventSourceEnum#code}
     * @param eventName   事件名或接口全限定名
     * @return
     */
    RpcResult<IndicatorResultInfo> handleBatchIndicatorCalculation(Integer eventSource, String eventName, String body);
}
