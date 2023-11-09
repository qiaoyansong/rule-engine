package com.rule.engine.dal.mapper;

import com.rule.engine.dal.domain.IndicatorRuleEventParsingInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IndicatorRuleEventParsingInfoMapper {
    boolean deleteByPrimaryKey(Long id);

    boolean insert(IndicatorRuleEventParsingInfo record);

    boolean insertSelective(IndicatorRuleEventParsingInfo record);

    IndicatorRuleEventParsingInfo selectByPrimaryKey(Long id);

    boolean updateByPrimaryKeySelective(IndicatorRuleEventParsingInfo record);

    boolean updateByPrimaryKey(IndicatorRuleEventParsingInfo record);

    /**
     * 获取事件Id对应的指标规则信息。
     *
     * @param eventId 事件ID
     * @param status  状态
     * @return
     */
    List<IndicatorRuleEventParsingInfo> listEventIndicatorRuleByEventId(@Param("eventId") Long eventId, @Param("status") int status);
}