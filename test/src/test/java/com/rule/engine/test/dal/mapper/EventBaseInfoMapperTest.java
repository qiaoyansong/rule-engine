package com.rule.engine.test.dal.mapper;

import com.alibaba.fastjson.JSON;
import com.rule.engine.api.enums.EventSourceEnum;
import com.rule.engine.dal.domain.EventBaseInfoDO;
import com.rule.engine.dal.mapper.EventBaseInfoMapper;
import com.rule.engine.test.BaseTestApplication;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/8 4:56 下午
 * description：
 */
public class EventBaseInfoMapperTest extends BaseTestApplication {

    @Resource
    private EventBaseInfoMapper eventBaseInfoMapper;

    @Test
    public void insertSelective_Test_Simple() {
        EventBaseInfoDO eventBaseInfoDO = new EventBaseInfoDO();
        eventBaseInfoDO.setEventSource(EventSourceEnum.MQ.getCode());
        eventBaseInfoDO.setEventIdentifier("test_mq_topic");
        eventBaseInfoDO.setEventName("测试解析指标");
        eventBaseInfoDO.setEventDesc("测试解析指标");
        eventBaseInfoDO.setEventTemplate("{\n" +
                "  \"bizType\": 1,\n" +
                "  \"clientType\": 5\n" +
                "}");
        eventBaseInfoDO.setCheckScript("ctx['bizType'] == 1");
        boolean selective = eventBaseInfoMapper.insertSelective(eventBaseInfoDO);
        Assert.assertTrue(selective);
    }

    @Test
    public void listByIdentifier_Test_Simple() {
        List<EventBaseInfoDO> interfaces = eventBaseInfoMapper.listByIdentifier(EventSourceEnum.INTERFACE.getCode(), "test_mq_topic");
        Assert.assertTrue(CollectionUtils.isEmpty(interfaces));
        List<EventBaseInfoDO> mqs = eventBaseInfoMapper.listByIdentifier(EventSourceEnum.MQ.getCode(), "test_mq_topic");
        System.out.println(JSON.toJSONString(mqs));
    }

}
