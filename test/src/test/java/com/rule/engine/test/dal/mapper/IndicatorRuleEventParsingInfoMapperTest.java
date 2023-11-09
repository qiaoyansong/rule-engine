package com.rule.engine.test.dal.mapper;

import com.rule.engine.api.enums.IndicatorConfigStatusEnum;
import com.rule.engine.dal.domain.IndicatorRuleEventParsingInfo;
import com.rule.engine.dal.mapper.IndicatorRuleEventParsingInfoMapper;
import com.rule.engine.test.BaseTestApplication;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/9 11:32 上午
 * description：
 */
public class IndicatorRuleEventParsingInfoMapperTest extends BaseTestApplication {

    @Resource
    private IndicatorRuleEventParsingInfoMapper indicatorRuleEventParsingInfoMapper;

    @Test
    public void insertSelective_Test_Simple() {
        IndicatorRuleEventParsingInfo indicatorRuleEventParsingInfo = new IndicatorRuleEventParsingInfo();
        indicatorRuleEventParsingInfo.setEventId(1L);
        indicatorRuleEventParsingInfo.setIndicatorId(2L);
        indicatorRuleEventParsingInfo.setMarshallField("$.clientType");
        indicatorRuleEventParsingInfo.setStatus(IndicatorConfigStatusEnum.STATUS_ACTIVE.getCode());
        boolean selective = indicatorRuleEventParsingInfoMapper.insertSelective(indicatorRuleEventParsingInfo);
        Assert.assertTrue(selective);
    }

}
