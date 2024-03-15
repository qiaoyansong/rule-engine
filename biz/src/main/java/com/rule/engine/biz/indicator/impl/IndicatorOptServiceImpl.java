package com.rule.engine.biz.indicator.impl;

import com.rule.engine.api.enums.ErrorCodeEnum;
import com.rule.engine.api.enums.IndicatorTypeEnum;
import com.rule.engine.api.mis.param.indicator.MisAddIndicatorInfoParam;
import com.rule.engine.biz.indicator.IndicatorOptService;
import com.rule.engine.biz.indicator.IndicatorTransactionManager;
import com.rule.engine.biz.util.BizChecker;
import com.rule.engine.dal.domain.EventBaseInfoDO;
import com.rule.engine.dal.mapper.EventBaseInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/2/23 4:30 下午
 * description：
 */
@Service
public class IndicatorOptServiceImpl implements IndicatorOptService {

    @Resource
    private IndicatorTransactionManager indicatorTransactionManager;

    @Resource
    private EventBaseInfoMapper eventBaseInfoMapper;

    @Override
    public Boolean addIndicatorInfo(MisAddIndicatorInfoParam param) {
        IndicatorTypeEnum indicatorTypeEnum = IndicatorTypeEnum.valueOf(param.getIndicatorType());
        if (IndicatorTypeEnum.INDICATOR_EVENT_PARSING.getCode() == indicatorTypeEnum.getCode()) {
            EventBaseInfoDO eventBaseInfoDO = eventBaseInfoMapper.selectByPrimaryKey(param.getEventParseInfo().getEventId());
            BizChecker.check(Objects.nonNull(eventBaseInfoDO), ErrorCodeEnum.NON_MATCH_EVENT);
            return indicatorTransactionManager.addEventParseIndicator(param);
        }
        // todo other
        return Boolean.FALSE;
    }
}
