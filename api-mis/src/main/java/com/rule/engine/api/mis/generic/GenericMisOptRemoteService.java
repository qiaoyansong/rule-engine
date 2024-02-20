package com.rule.engine.api.mis.generic;

import com.rule.engine.api.mis.param.generic.RemoteMethodCalculationParam;
import com.rule.engine.api.result.RpcResult;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/1/29 4:55 下午
 * description：
 */
public interface GenericMisOptRemoteService {

    /**
     * 添加外部服务
     *
     * @param param
     * @return
     */
    RpcResult<Boolean> addRemoteMethod(RemoteMethodCalculationParam param);

}
