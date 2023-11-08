package com.rule.engine.test.dal.mapper;

import com.alibaba.fastjson.JSON;
import com.rule.engine.api.enums.IndicatorConfigStatusEnum;
import com.rule.engine.api.enums.IndicatorTypeEnum;
import com.rule.engine.api.enums.IndicatorValueTypeEnum;
import com.rule.engine.dal.domain.IndicatorBaseInfoDO;
import com.rule.engine.dal.mapper.IndicatorBaseInfoMapper;
import com.rule.engine.test.BaseTestApplication;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/8 3:34 下午
 * description：
 */
public class IndicatorBaseInfoMapperTest extends BaseTestApplication {

    @Resource
    private IndicatorBaseInfoMapper indicatorBaseInfoMapper;

    @Test
    public void insertSelective_Test_Simple() {
        IndicatorBaseInfoDO indicatorBaseInfoDO = new IndicatorBaseInfoDO();
        indicatorBaseInfoDO.setIndicatorName("用户端类型");
        indicatorBaseInfoDO.setIndicatorDesc("用户端类型");
        indicatorBaseInfoDO.setIndicatorReturnType(IndicatorValueTypeEnum.TYPE_TEXT.getCode());
        indicatorBaseInfoDO.setIndicatorType(IndicatorTypeEnum.INDICATOR_EVENT_PARSING.getCode());
        indicatorBaseInfoDO.setStatus(IndicatorConfigStatusEnum.STATUS_ACTIVE.getCode());
        boolean selective = indicatorBaseInfoMapper.insertSelective(indicatorBaseInfoDO);
        Assert.assertTrue(selective);
    }

    @Test
    public void queryIndicatorByIndicatorId_Test_Simple() {
        IndicatorBaseInfoDO indicatorBaseInfoDO = indicatorBaseInfoMapper.queryIndicatorByIndicatorId(1L, IndicatorConfigStatusEnum.STATUS_ACTIVE.getCode());
        System.out.println(JSON.toJSONString(indicatorBaseInfoDO));
    }

}
