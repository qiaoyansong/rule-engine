package com.rule.engine.service.indicator.impl;

import com.rule.engine.api.mis.indicator.IndicatorMisOptRemoteService;
import com.rule.engine.api.mis.param.indicator.MisAddIndicatorInfoParam;
import com.rule.engine.api.result.RpcResult;
import org.springframework.stereotype.Service;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/1/29 4:44 下午
 * description：
 */
@Service
public class IndicatorMisOptRemoteServiceImpl implements IndicatorMisOptRemoteService {

    @Override
    public RpcResult<Boolean> addIndicatorInfo(MisAddIndicatorInfoParam param) {
        return null;
    }
}
