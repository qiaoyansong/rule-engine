package com.rule.engine.api.mis.generic;

import com.rule.engine.api.mis.result.RemoteMethodVO;
import com.rule.engine.api.result.RpcResult;

import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/3/18 4:54 下午
 * description：
 */
public interface GenericMisQryRemoteService {

    /**
     * 查看所有本地函数
     *
     * @return
     */
    RpcResult<List<RemoteMethodVO>> listAllLocalJavaFunction();
}
