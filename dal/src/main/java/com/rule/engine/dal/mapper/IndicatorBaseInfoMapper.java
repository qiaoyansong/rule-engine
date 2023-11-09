package com.rule.engine.dal.mapper;

import com.rule.engine.dal.domain.IndicatorBaseInfoDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IndicatorBaseInfoMapper {
    boolean deleteByPrimaryKey(Long id);

    boolean insert(IndicatorBaseInfoDO record);

    boolean insertSelective(IndicatorBaseInfoDO record);

    IndicatorBaseInfoDO selectByPrimaryKey(Long id);

    boolean updateByPrimaryKeySelective(IndicatorBaseInfoDO record);

    boolean updateByPrimaryKey(IndicatorBaseInfoDO record);

    /**
     * 获取指定ID对应的解析指标信息。
     *
     * @param indicatorId 指标ID
     * @param status      指标状态
     * @return
     */
    IndicatorBaseInfoDO queryIndicatorByIndicatorId(@Param("indicatorId") Long indicatorId, @Param("status") Integer status);

    /**
     * 获取指定指标ID列表对应的指标信息。
     *
     * @param indicatorIds
     *
     * @param status
     *
     * @return
     */
    List<IndicatorBaseInfoDO> listIndicatorBaseInfo(@Param("indicatorIds") List<Long> indicatorIds,
                                                    @Param("status") Integer status);
}