package com.rule.engine.api.mis.event;

import com.rule.engine.api.mis.param.event.MisAddEventInfoParam;
import com.rule.engine.api.result.RpcResult;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/2/21 11:36 上午
 * description：
 */
public interface EventMisOptRemoteService {

    /**
     * 添加指标
     *
     * @param param
     * @return
     */
    RpcResult<Boolean> addEventInfo(MisAddEventInfoParam param);
}
