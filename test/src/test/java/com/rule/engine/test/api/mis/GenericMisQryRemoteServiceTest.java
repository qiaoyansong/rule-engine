package com.rule.engine.test.api.mis;

import com.alibaba.fastjson.JSON;
import com.rule.engine.api.mis.generic.GenericMisQryRemoteService;
import com.rule.engine.api.mis.result.RemoteMethodVO;
import com.rule.engine.api.result.RpcResult;
import com.rule.engine.test.BaseTestApplication;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/3/19 3:35 下午
 * description：
 */
public class GenericMisQryRemoteServiceTest extends BaseTestApplication {

    @Resource
    private GenericMisQryRemoteService genericMisQryRemoteService;

    @Test
    public void listAllLocalJavaFunction_Test_Simple() {
        RpcResult<List<RemoteMethodVO>> rpcResult = genericMisQryRemoteService.listAllLocalJavaFunction();
        Assert.assertNotNull(rpcResult.getData());
        System.out.println(JSON.toJSONString(rpcResult.getData()));
    }

}
