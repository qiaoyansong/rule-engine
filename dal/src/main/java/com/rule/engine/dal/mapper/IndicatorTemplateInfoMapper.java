package com.rule.engine.dal.mapper;

import com.rule.engine.dal.domain.IndicatorTemplateInfo;

public interface IndicatorTemplateInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IndicatorTemplateInfo record);

    int insertSelective(IndicatorTemplateInfo record);

    IndicatorTemplateInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IndicatorTemplateInfo record);

    int updateByPrimaryKey(IndicatorTemplateInfo record);
}