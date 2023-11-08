package com.rule.engine.biz.indicator;

import com.rule.engine.biz.bo.IndicatorBaseInfoBO;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/8 3:49 下午
 * description：
 */
public interface IndicatorBaseInfoService {

    /**
     * 获取指标基本信息.
     *
     * @param indicatorId  指标ID
     *
     * @return
     */
    IndicatorBaseInfoBO queryIndicatorBaseInfo(Long indicatorId);
}
