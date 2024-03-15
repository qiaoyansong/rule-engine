package com.rule.engine.biz.beanmapper;

import com.alibaba.fastjson.JSON;
import com.rule.engine.api.enums.IndicatorConfigStatusEnum;
import com.rule.engine.api.mis.param.indicator.MisAddIndicatorInfoParam;
import com.rule.engine.biz.bo.IndicatorBaseInfoBO;
import com.rule.engine.dal.domain.IndicatorBaseInfoDO;
import com.rule.engine.dal.domain.IndicatorRuleEventParsingInfo;
import com.rule.engine.dal.domain.IndicatorTemplateInfo;
import org.mapstruct.*;

import java.util.List;
import java.util.Objects;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/8 5:25 下午
 * description：
 */
@Mapper(componentModel = "spring")
public interface IndicatorBeanMapper {
    IndicatorBaseInfoBO indicatorBaseInfoDO2IndicatorBaseInfoBO(IndicatorBaseInfoDO queryIndicatorByIndicatorId);

    @Mapping(source = "indicatorValueType", target = "indicatorReturnType")
    @Mapping(source = "operatorId", target = "creatorId")
    @Mapping(source = "operatorName", target = "creatorName")
    IndicatorBaseInfoDO misAddIndicatorInfoParam2IndicatorBaseInfoDO(MisAddIndicatorInfoParam misAddIndicatorInfoParam);

    @AfterMapping
    default void fillDefaultInfo(@MappingTarget IndicatorBaseInfoDO indicatorBaseInfoD, MisAddIndicatorInfoParam misAddIndicatorInfoParam) {
        if (Objects.isNull(indicatorBaseInfoD)) {
            return;
        }
        indicatorBaseInfoD.setStatus(IndicatorConfigStatusEnum.STATUS_ACTIVE.getCode());
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "gmtCreate", ignore = true)
    @Mapping(target = "gmtModify", ignore = true)
    @Mapping(target = "indicatorId", source = "indicatorBaseInfoDO.id")
    @Mapping(target = "eventId", source = "misAddIndicatorInfoParam.eventParseInfo.eventId")
    @Mapping(target = "marshallField", source = "misAddIndicatorInfoParam.eventParseInfo.marshallField")
    IndicatorRuleEventParsingInfo covertIndicatorRuleEventParsingInfo(MisAddIndicatorInfoParam misAddIndicatorInfoParam, IndicatorBaseInfoDO indicatorBaseInfoDO);

    @AfterMapping
    default void fillDefaultInfo(@MappingTarget IndicatorRuleEventParsingInfo indicatorRuleEventParsingInfo, MisAddIndicatorInfoParam misAddIndicatorInfoParam, IndicatorBaseInfoDO indicatorBaseInfoDO) {
        if (Objects.isNull(indicatorRuleEventParsingInfo)) {
            return;
        }
        indicatorRuleEventParsingInfo.setStatus(IndicatorConfigStatusEnum.STATUS_ACTIVE.getCode());
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "gmtCreate", ignore = true)
    @Mapping(target = "gmtModify", ignore = true)
    @Mapping(target = "indicatorId", source = "indicatorBaseInfoDO.id")
    @Mapping(target = "rightValuePlaceHolder", source = "misAddIndicatorInfoParam.indicatorTemplate.rightValuePlaceHolder")
    @Mapping(target = "rightValueInputType", source = "misAddIndicatorInfoParam.indicatorTemplate.rightValueInputType")
    @Mapping(target = "judgeTypeList", source = "misAddIndicatorInfoParam.indicatorTemplate.judgeTypeList", qualifiedByName = "stringList2Json")
    @Mapping(target = "rightValueList", source = "misAddIndicatorInfoParam.indicatorTemplate.rightValueList", qualifiedByName = "rightValueList2Json")
    @Mapping(target = "interfaceName", source = "misAddIndicatorInfoParam.indicatorTemplate.interfaceName")
    IndicatorTemplateInfo covertIndicatorTemplateInfo(MisAddIndicatorInfoParam misAddIndicatorInfoParam, IndicatorBaseInfoDO indicatorBaseInfoDO);

    @Named("stringList2Json")
    default String stringList2Json(List<String> list) {
        return JSON.toJSONString(list);
    }

    @Named("rightValueList2Json")
    default String rightValueList2Json(List<MisAddIndicatorInfoParam.Operands> list) {
        return JSON.toJSONString(list);
    }
}
