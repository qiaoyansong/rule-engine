package com.rule.engine.test.api.mis;

import com.rule.engine.api.enums.IndicatorTypeEnum;
import com.rule.engine.api.enums.IndicatorValueTypeEnum;
import com.rule.engine.api.enums.InputTypeEnum;
import com.rule.engine.api.enums.JudgeTypeEnum;
import com.rule.engine.api.mis.indicator.IndicatorMisOptRemoteService;
import com.rule.engine.api.mis.param.indicator.MisAddIndicatorInfoParam;
import com.rule.engine.api.result.RpcResult;
import com.rule.engine.test.BaseTestApplication;
import org.junit.Assert;
import org.junit.Test;
import org.testng.collections.Lists;

import javax.annotation.Resource;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/3/15 3:49 下午
 * description：
 */
public class IndicatorMisOptRemoteServiceTest extends BaseTestApplication {

    @Resource
    private IndicatorMisOptRemoteService indicatorMisOptRemoteService;

    /**
     * 添加解析指标并且模版为下拉列表
     */
    @Test
    public void addIndicatorInfo_Test_addEventParseAndTemplateIsRightValueList() {
        MisAddIndicatorInfoParam misAddIndicatorInfoParam = new MisAddIndicatorInfoParam();
        misAddIndicatorInfoParam.setOperatorId(0L);
        misAddIndicatorInfoParam.setOperatorName("root");
        misAddIndicatorInfoParam.setIndicatorName("发单起点天气");
        misAddIndicatorInfoParam.setIndicatorDesc("发单起点天气");
        misAddIndicatorInfoParam.setIndicatorType(IndicatorTypeEnum.INDICATOR_EVENT_PARSING.getCode());
        misAddIndicatorInfoParam.setIndicatorValueType(IndicatorValueTypeEnum.TYPE_TEXT.getCode());

        MisAddIndicatorInfoParam.IndicatorTemplate indicatorTemplate = new MisAddIndicatorInfoParam.IndicatorTemplate();
        misAddIndicatorInfoParam.setIndicatorTemplate(indicatorTemplate);
        indicatorTemplate.setRightValuePlaceHolder("发单起点天气类型");
        indicatorTemplate.setRightValueInputType(InputTypeEnum.DROPDOWN_RADIO_SELECTION.getCode());
        indicatorTemplate.setJudgeTypeList(Lists.newArrayList(JudgeTypeEnum.EQ.getCharacter(), JudgeTypeEnum.IN.getCharacter()));
        MisAddIndicatorInfoParam.Operands operands1 = new MisAddIndicatorInfoParam.Operands();
        operands1.setName("晴天");
        operands1.setValue("clear day");
        MisAddIndicatorInfoParam.Operands operands2 = new MisAddIndicatorInfoParam.Operands();
        operands2.setName("阴天");
        operands2.setValue("cloudy day");
        indicatorTemplate.setRightValueList(Lists.newArrayList(operands1, operands2));

        MisAddIndicatorInfoParam.EventParseInfo eventParseInfo = new MisAddIndicatorInfoParam.EventParseInfo();
        misAddIndicatorInfoParam.setEventParseInfo(eventParseInfo);
        eventParseInfo.setEventId(2L);
        eventParseInfo.setMarshallField("$.weather");

        RpcResult<Boolean> rpcResult = indicatorMisOptRemoteService.addIndicatorInfo(misAddIndicatorInfoParam);
        Assert.assertNotNull(rpcResult);
        Assert.assertTrue(rpcResult.getData());
    }

}
