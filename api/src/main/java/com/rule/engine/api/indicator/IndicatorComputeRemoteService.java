package com.rule.engine.api.indicator;

import com.rule.engine.api.result.RpcResult;
import com.rule.engine.api.result.indicator.IndicatorResultInfo;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/9 3:11 下午
 * description：
 */
public interface IndicatorComputeRemoteService {

    /**
     * 批量计算指标。
     *
     * @param eventName   事件名或接口全限定名
     * @return
     */
    RpcResult<IndicatorResultInfo> handleBatchIndicatorCalculation(String eventName, String body);
}
