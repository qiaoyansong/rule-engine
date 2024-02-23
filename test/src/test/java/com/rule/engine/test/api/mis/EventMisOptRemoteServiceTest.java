package com.rule.engine.test.api.mis;

import com.rule.engine.api.enums.EventSourceEnum;
import com.rule.engine.api.mis.event.EventMisOptRemoteService;
import com.rule.engine.api.mis.param.event.MisAddEventInfoParam;
import com.rule.engine.api.result.RpcResult;
import com.rule.engine.test.BaseTestApplication;
import org.apache.dubbo.spring.boot.autoconfigure.DubboConfigurationProperties;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/2/23 10:29 上午
 * description：
 */
public class EventMisOptRemoteServiceTest extends BaseTestApplication {

    @Resource
    private EventMisOptRemoteService eventMisOptRemoteService;

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private DubboConfigurationProperties dubboConfigurationProperties;

    @Test
    public void addEventInfo_Test_Simple() {
        MisAddEventInfoParam misAddEventInfoParam = new MisAddEventInfoParam();
        misAddEventInfoParam.setOperatorId(1L);
        misAddEventInfoParam.setOperatorName("root");
        misAddEventInfoParam.setEventSource(EventSourceEnum.INTERFACE.getCode());
        misAddEventInfoParam.setEventIdentifier("weather_notify_api");
        misAddEventInfoParam.setEventName("天气通知API");
        misAddEventInfoParam.setEventTemplate("{\n" +
                "  \"cityId\": 1,\n" +
                "  \"cityName\": \"北京市\",\n" +
                "  \"weather\": \"clear day\"\n" +
                "}");
        RpcResult<Boolean> rpcResult = eventMisOptRemoteService.addEventInfo(misAddEventInfoParam);
        Assert.assertTrue(rpcResult.getData());
    }

}
