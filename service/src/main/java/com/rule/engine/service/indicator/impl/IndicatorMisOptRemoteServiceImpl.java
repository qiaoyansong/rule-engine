package com.rule.engine.service.indicator.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.rule.engine.api.enums.*;
import com.rule.engine.api.mis.indicator.IndicatorMisOptRemoteService;
import com.rule.engine.api.mis.param.indicator.MisAddIndicatorInfoParam;
import com.rule.engine.api.result.RpcResult;
import com.rule.engine.api.utils.RpcResultUtil;
import com.rule.engine.biz.exception.BizException;
import com.rule.engine.biz.indicator.IndicatorOptService;
import com.rule.engine.biz.util.BizChecker;
import com.rule.engine.api.indicator.IndicatorComputeRemoteService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/1/29 4:44 下午
 * description：
 */
@Service
public class IndicatorMisOptRemoteServiceImpl implements IndicatorMisOptRemoteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndicatorComputeRemoteService.class);

    @Resource
    private IndicatorOptService indicatorOptService;

    private ImmutableMap<IndicatorTypeEnum, Consumer<MisAddIndicatorInfoParam>> valid = ImmutableMap.of(
            IndicatorTypeEnum.INDICATOR_EVENT_PARSING, this::validEventParse,
            IndicatorTypeEnum.INDICATOR_RMI_INVOKING, this::validRmi
    );

    @Override
    public RpcResult<Boolean> addIndicatorInfo(MisAddIndicatorInfoParam param) {
        try {
            BizChecker.checkMisCommonOpt(param, ErrorCodeEnum.PARAM_ERROR, null);
            BizChecker.check(StringUtils.isNotBlank(param.getIndicatorName()), ErrorCodeEnum.PARAM_ERROR);
            IndicatorTypeEnum indicatorTypeEnum = IndicatorTypeEnum.valueOf(param.getIndicatorType());
            BizChecker.check(Objects.nonNull(indicatorTypeEnum), ErrorCodeEnum.PARAM_ERROR);
            BizChecker.check(Objects.nonNull(IndicatorValueTypeEnum.getByCode(param.getIndicatorValueType())), ErrorCodeEnum.PARAM_ERROR);
            BizChecker.check(Objects.nonNull(param.getIndicatorTemplate()), ErrorCodeEnum.PARAM_ERROR);
            // 校验模版
            validIndicatorTemplate(param.getIndicatorTemplate());
            // 不同指标类型定制化校验
            Consumer<MisAddIndicatorInfoParam> validConsumer = valid.get(indicatorTypeEnum);
            if (Objects.nonNull(validConsumer)) {
                validConsumer.accept(param);
            }
            return RpcResultUtil.success(indicatorOptService.addIndicatorInfo(param));
        } catch (BizException e) {
            LOGGER.warn("IndicatorMisOptRemoteServiceImpl#addIndicatorInfo warn param={}, e=", JSON.toJSONString(param), e);
            return RpcResultUtil.fail(e.getCode(), e.getMsg());
        } catch (Exception e) {
            LOGGER.warn("IndicatorMisOptRemoteServiceImpl#addIndicatorInfo error param={}, e=", JSON.toJSONString(param), e);
            return RpcResultUtil.fail(ErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    /**
     * 校验指标模版
     *
     * @param indicatorTemplate
     */
    private void validIndicatorTemplate(MisAddIndicatorInfoParam.IndicatorTemplate indicatorTemplate) {
        InputTypeEnum inputTypeEnum = InputTypeEnum.getByCode(indicatorTemplate.getRightValueInputType());
        BizChecker.check(Objects.nonNull(inputTypeEnum), ErrorCodeEnum.PARAM_ERROR);
        BizChecker.check(CollectionUtils.isNotEmpty(indicatorTemplate.getJudgeTypeList()), ErrorCodeEnum.PARAM_ERROR);
        indicatorTemplate.getJudgeTypeList().forEach(judgeType -> {
            BizChecker.check(Objects.nonNull(JudgeTypeEnum.getByCode(judgeType)), ErrorCodeEnum.PARAM_ERROR);
        });
        if (InputTypeEnum.DROPDOWN_RADIO_SELECTION.equals(inputTypeEnum) ||
                InputTypeEnum.DROPDOWN_MULTIPLE_SELECTION.equals(inputTypeEnum)) {
            if (StringUtils.isBlank(indicatorTemplate.getInterfaceName()) && CollectionUtils.isEmpty(indicatorTemplate.getRightValueList())) {
                throw new BizException(ErrorCodeEnum.PARAM_ERROR);
            }
            if (StringUtils.isNotBlank(indicatorTemplate.getInterfaceName()) && CollectionUtils.isNotEmpty(indicatorTemplate.getRightValueList())) {
                throw new BizException(ErrorCodeEnum.PARAM_ERROR);
            }
            if (CollectionUtils.isNotEmpty(indicatorTemplate.getRightValueList())) {
                indicatorTemplate.getRightValueList().forEach(rightValue -> {
                    BizChecker.check(StringUtils.isNotBlank(rightValue.getName()), ErrorCodeEnum.PARAM_ERROR);
                    BizChecker.check(Objects.nonNull(rightValue.getValue()), ErrorCodeEnum.PARAM_ERROR);
                });
            }
        }
    }

    /**
     * 外部服务类型指标 定制化校验
     *
     * @param misAddIndicatorInfoParam
     */
    private void validRmi(MisAddIndicatorInfoParam misAddIndicatorInfoParam) {
        // todo qys
    }

    /**
     * 事件解析类型指标 定制化校验
     *
     * @param misAddIndicatorInfoParam
     */
    private void validEventParse(MisAddIndicatorInfoParam misAddIndicatorInfoParam) {
        MisAddIndicatorInfoParam.EventParseInfo eventParseInfo = misAddIndicatorInfoParam.getEventParseInfo();
        BizChecker.check(Objects.nonNull(eventParseInfo), ErrorCodeEnum.PARAM_ERROR);
        BizChecker.check(Objects.nonNull(eventParseInfo.getEventId()), ErrorCodeEnum.PARAM_ERROR);
        BizChecker.check(StringUtils.isNotBlank(eventParseInfo.getMarshallField()), ErrorCodeEnum.PARAM_ERROR);
    }
}
