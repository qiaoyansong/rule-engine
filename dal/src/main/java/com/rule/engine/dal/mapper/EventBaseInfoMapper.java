package com.rule.engine.dal.mapper;

import com.rule.engine.dal.domain.EventBaseInfoDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EventBaseInfoMapper {
    boolean deleteByPrimaryKey(Long id);

    boolean insert(EventBaseInfoDO record);

    boolean insertSelective(EventBaseInfoDO record);

    EventBaseInfoDO selectByPrimaryKey(Long id);

    boolean updateByPrimaryKeySelective(EventBaseInfoDO record);

    boolean updateByPrimaryKeyWithBLOBs(EventBaseInfoDO record);

    boolean updateByPrimaryKey(EventBaseInfoDO record);

    List<EventBaseInfoDO> listByIdentifier(@Param("eventSource") Integer eventSource, @Param("identifier") String identifier);
}