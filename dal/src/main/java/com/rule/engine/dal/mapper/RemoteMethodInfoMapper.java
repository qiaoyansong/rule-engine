package com.rule.engine.dal.mapper;

import com.rule.engine.dal.domain.RemoteMethodInfo;

public interface RemoteMethodInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RemoteMethodInfo record);

    boolean insertSelective(RemoteMethodInfo record);

    RemoteMethodInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RemoteMethodInfo record);

    int updateByPrimaryKey(RemoteMethodInfo record);
}