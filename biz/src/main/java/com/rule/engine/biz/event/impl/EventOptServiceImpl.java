package com.rule.engine.biz.event.impl;

import com.rule.engine.api.mis.param.event.MisAddEventInfoParam;
import com.rule.engine.biz.beanmapper.EventBeanMapper;
import com.rule.engine.biz.event.EventOptService;
import com.rule.engine.dal.mapper.EventBaseInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/2/22 4:38 下午
 * description：
 */
@Service
public class EventOptServiceImpl implements EventOptService {

    @Resource
    private EventBeanMapper eventBeanMapper;

    @Resource
    private EventBaseInfoMapper eventBaseInfoMapper;

    @Override
    public Boolean addEventInfo(MisAddEventInfoParam param) {
        return eventBaseInfoMapper.insertSelective(eventBeanMapper.misAddEventInfoParam2EventBaseInfoDO(param));
    }
}
