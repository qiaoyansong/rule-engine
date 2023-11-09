package com.rule.engine.biz.indicator.calc.impl;

import com.rule.engine.api.enums.ErrorCodeEnum;
import com.rule.engine.api.enums.IndicatorValueTypeEnum;
import com.rule.engine.api.result.indicator.IndicatorResultInfo;
import com.rule.engine.biz.bo.EventBO;
import com.rule.engine.biz.bo.EventParseIndicatorInfoBO;
import com.rule.engine.biz.event.EventBaseInfoService;
import com.rule.engine.biz.exception.BizException;
import com.rule.engine.biz.indicator.calc.EventParsingIndicatorInfoService;
import com.rule.engine.biz.indicator.calc.IndicatorComputeBizService;
import com.rule.engine.common.utils.JsonPathUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/9 3:37 下午
 * description：
 */
@Service
public class IndicatorComputeBizServiceImpl implements IndicatorComputeBizService {

    @Resource
    private EventBaseInfoService eventBaseInfoService;

    @Resource
    private EventParsingIndicatorInfoService eventParsingIndicatorInfoService;

    @Override
    public IndicatorResultInfo handleBatchIndicatorCalculation(Integer eventSource, String eventName, String body) {
        // 获取匹配的事件
        EventBO bodyMatchEvent = eventBaseInfoService.getBodyMatchEvent(eventSource, eventName, body);
        if (Objects.isNull(bodyMatchEvent)) {
            throw new BizException(ErrorCodeEnum.NON_MATCH_EVENT);
        }
        // 根据事件匹配解析指标
        List<EventParseIndicatorInfoBO> parseIndicatorInfoBOS = eventParsingIndicatorInfoService.listEventIndicatorByEventId(bodyMatchEvent.getId());
        if (CollectionUtils.isEmpty(parseIndicatorInfoBOS)) {
            return null;
        }
        IndicatorResultInfo result = new IndicatorResultInfo();
        result.setIndicatorResultDetails(parseIndicatorInfoBOS.stream()
                .map(parseIndicatorInfoBO -> {
                    IndicatorResultInfo.IndicatorResultDetail indicatorResultDetail = new IndicatorResultInfo.IndicatorResultDetail();
                    indicatorResultDetail.setIndicatorId(parseIndicatorInfoBO.getIndicatorId());
                    indicatorResultDetail.setIndicatorValue(IndicatorValueTypeEnum.parseVal(parseIndicatorInfoBO.getIndicatorReturnType(), JsonPathUtils.read(body, parseIndicatorInfoBO.getMarshallField())));
                    indicatorResultDetail.setIndicatorValueType(parseIndicatorInfoBO.getIndicatorReturnType());
                    return indicatorResultDetail;
                }).collect(Collectors.toList()));
        return result;
    }

}
