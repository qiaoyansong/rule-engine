package com.rule.engine.biz.indicator;

import com.rule.engine.api.mis.param.indicator.MisAddIndicatorInfoParam;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/2/23 4:48 下午
 * description：
 */
public interface IndicatorTransactionManager {

    /**
     * 添加事件解析类型指标
     *
     * @param misAddIndicatorInfoParam
     * @return
     */
    Boolean addEventParseIndicator(MisAddIndicatorInfoParam misAddIndicatorInfoParam);
}
