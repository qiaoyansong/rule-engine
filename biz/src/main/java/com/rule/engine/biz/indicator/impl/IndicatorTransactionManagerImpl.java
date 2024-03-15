package com.rule.engine.biz.indicator.impl;

import com.rule.engine.api.mis.param.indicator.MisAddIndicatorInfoParam;
import com.rule.engine.biz.beanmapper.IndicatorBeanMapper;
import com.rule.engine.biz.indicator.IndicatorTransactionManager;
import com.rule.engine.dal.domain.IndicatorBaseInfoDO;
import com.rule.engine.dal.mapper.IndicatorBaseInfoMapper;
import com.rule.engine.dal.mapper.IndicatorRuleEventParsingInfoMapper;
import com.rule.engine.dal.mapper.IndicatorTemplateInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/2/23 5:17 下午
 * description：
 */
@Service
public class IndicatorTransactionManagerImpl implements IndicatorTransactionManager {

    @Resource
    private IndicatorBeanMapper indicatorBeanMapper;

    @Resource
    private IndicatorBaseInfoMapper indicatorBaseInfoMapper;

    @Resource
    private IndicatorRuleEventParsingInfoMapper indicatorRuleEventParsingInfoMapper;

    @Resource
    private IndicatorTemplateInfoMapper indicatorTemplateInfoMapper;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Boolean addEventParseIndicator(MisAddIndicatorInfoParam misAddIndicatorInfoParam) {
        IndicatorBaseInfoDO indicatorBaseInfoDO = indicatorBeanMapper.misAddIndicatorInfoParam2IndicatorBaseInfoDO(misAddIndicatorInfoParam);
        indicatorBaseInfoMapper.insertSelective(indicatorBaseInfoDO);
        indicatorTemplateInfoMapper.insertSelective(indicatorBeanMapper.covertIndicatorTemplateInfo(misAddIndicatorInfoParam, indicatorBaseInfoDO));
        return indicatorRuleEventParsingInfoMapper.insertSelective(indicatorBeanMapper.covertIndicatorRuleEventParsingInfo(misAddIndicatorInfoParam, indicatorBaseInfoDO));
    }

}
