package com.rule.engine.test.biz.indicator.calc;

import com.alibaba.fastjson.JSON;
import com.rule.engine.biz.bo.EventParseIndicatorInfoBO;
import com.rule.engine.biz.indicator.calc.EventParsingIndicatorInfoService;
import com.rule.engine.test.BaseTestApplication;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/9 11:38 上午
 * description：
 */
public class EventParsingIndicatorInfoServiceTest extends BaseTestApplication {

    @Resource
    private EventParsingIndicatorInfoService eventParsingIndicatorInfoService;

    @Test
    public void listEventIndicatorByEventId_Test_Simple() {
        List<EventParseIndicatorInfoBO> eventParseIndicatorInfoBOS = eventParsingIndicatorInfoService.listEventIndicatorByEventId(1L);
        System.out.println(JSON.toJSONString(eventParseIndicatorInfoBOS));
    }

}
