package com.rule.engine.test.biz.indicator;

import com.alibaba.fastjson.JSON;
import com.rule.engine.biz.bo.IndicatorBaseInfoBO;
import com.rule.engine.biz.indicator.IndicatorBaseInfoService;
import com.rule.engine.test.BaseTestApplication;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/8 4:24 下午
 * description：
 */
public class IndicatorBaseInfoServiceTest extends BaseTestApplication {

    @Resource
    private IndicatorBaseInfoService indicatorBaseInfoService;

    @Test
    public void queryIndicatorBaseInfo_Test_Simple() {
        IndicatorBaseInfoBO indicatorBaseInfoBO = indicatorBaseInfoService.queryIndicatorBaseInfo(1L);
        System.out.println(JSON.toJSONString(indicatorBaseInfoBO));
    }
}
