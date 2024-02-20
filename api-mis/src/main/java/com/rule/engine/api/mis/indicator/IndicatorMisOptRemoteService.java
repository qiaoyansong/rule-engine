package com.rule.engine.api.mis.indicator;

import com.rule.engine.api.mis.param.indicator.MisAddIndicatorInfoParam;
import com.rule.engine.api.result.RpcResult;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/1/29 2:35 下午
 * description：
 */
public interface IndicatorMisOptRemoteService {

    /**
     * 添加指标
     *
     * @param param
     * @return
     */
    RpcResult<Boolean> addIndicatorInfo(MisAddIndicatorInfoParam param);

}
