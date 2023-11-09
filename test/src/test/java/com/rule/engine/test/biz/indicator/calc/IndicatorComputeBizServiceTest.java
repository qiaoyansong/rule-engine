package com.rule.engine.test.biz.indicator.calc;

import com.alibaba.fastjson.JSON;
import com.rule.engine.api.enums.EventSourceEnum;
import com.rule.engine.api.result.indicator.IndicatorResultInfo;
import com.rule.engine.biz.indicator.calc.IndicatorComputeBizService;
import com.rule.engine.test.BaseTestApplication;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/9 7:07 下午
 * description：
 */
public class IndicatorComputeBizServiceTest extends BaseTestApplication {

    @Resource
    private IndicatorComputeBizService indicatorComputeBizService;

    @Test
    public void handleBatchIndicatorCalculation_Test_Simple() {
        String body = "{\n" +
                "  \"bizType\": 1,\n" +
                "  \"clientType\": 5\n" +
                "}";
        IndicatorResultInfo indicatorResultInfo = indicatorComputeBizService.handleBatchIndicatorCalculation(EventSourceEnum.MQ.getCode(), "test_mq_topic", body);
        Assert.assertNotNull(indicatorResultInfo);
        System.out.println(JSON.toJSONString(indicatorResultInfo.getIndicatorResultDetails()));
    }


}
