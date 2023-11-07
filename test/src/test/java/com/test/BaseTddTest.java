package com.test;

import lombok.Getter;
import org.junit.Before;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/26 10:26 上午
 * description：
 */
@Getter
public class BaseTddTest {


    @Before
    public void init() throws Exception {
        // 确定的上下游服务
//        helloRpcService = new HelloRpcServiceImpl();
//        helloService = new HelloServiceImpl();
//        MockHelper.mockField(helloRpcService, "helloService", helloService);
//        do2DtoHelper = Mappers.getMapper( Do2DtoHelper.class);
//        MockHelper.mockField(helloService, "do2DtoHelper", do2DtoHelper);
//
//        // 不确定的上下游服务，直接mock出来
//        userMapper = Mockito.mock(UserMapper.class);
//        MockHelper.mockField(helloService, "userMapper", userMapper);
    }
}
