package com.rule.engine.test.biz.event;

import com.alibaba.fastjson.JSON;
import com.rule.engine.api.enums.EventSourceEnum;
import com.rule.engine.biz.bo.EventBO;
import com.rule.engine.biz.event.EventBaseInfoService;
import com.rule.engine.test.BaseTestApplication;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/8 7:44 下午
 * description：
 */
public class EventBaseInfoServiceTest extends BaseTestApplication {

    @Resource
    private EventBaseInfoService eventBaseInfoService;

    @Test
    public void getBodyMatchEvent_Test_nonMatch() {
        Map map = new HashMap();
        map.put("bizType", 2);
        map.put("clientType", 5);
        EventBO eventBO = eventBaseInfoService.getBodyMatchEvent(EventSourceEnum.MQ.getCode(), "test_mq_topic", JSON.toJSONString(map));
        Assert.assertNull(eventBO);
    }

    @Test
    public void getBodyMatchEvent_Test_match() {
        Map map = new HashMap();
        map.put("bizType", 1);
        map.put("clientType", 5);
        EventBO eventBO = eventBaseInfoService.getBodyMatchEvent(EventSourceEnum.MQ.getCode(), "test_mq_topic", JSON.toJSONString(map));
        System.out.println(JSON.toJSONString(eventBO));
    }

}
