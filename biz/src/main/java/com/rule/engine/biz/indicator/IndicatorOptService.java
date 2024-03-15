package com.rule.engine.biz.indicator;

import com.rule.engine.api.mis.param.indicator.MisAddIndicatorInfoParam;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/1/29 4:44 下午
 * description：
 */
public interface IndicatorOptService {

    /**
     * 添加指标
     *
     * @param param
     * @return
     */
    Boolean addIndicatorInfo(MisAddIndicatorInfoParam param);
}
