package com.rule.engine.biz.event.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.rule.engine.biz.beanmapper.EventBeanMapper;
import com.rule.engine.biz.bo.EventBO;
import com.rule.engine.biz.event.EventBaseInfoCacheService;
import com.rule.engine.biz.event.EventQryService;
import com.rule.engine.common.constant.CacheKeys;
import com.rule.engine.common.utils.GroovyEngine;
import com.rule.engine.dal.mapper.EventBaseInfoMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.script.CompiledScript;
import javax.script.ScriptException;
import java.util.List;
import java.util.Map;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/8 5:27 下午
 * description：
 */
@Service
public class EventQryServiceImpl implements EventQryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventQryServiceImpl.class);

    @Resource
    private EventBaseInfoCacheService eventBaseInfoCacheService;

    @Resource
    private EventBaseInfoMapper eventBaseInfoMapper;

    @Resource
    private EventBeanMapper eventBeanMapper;

    @Override
    public EventBO getBodyMatchEvent(Integer eventSource, String eventName, String body) {
        List<EventBO> events = eventBaseInfoCacheService.getInfoFromCache(String.format(CacheKeys.EVENT_CACHE_KEY, eventSource, eventName), () -> this.listByIdentifier(eventSource, eventName), 60, new TypeReference<List<EventBO>>() {
        });
        if (CollectionUtils.isEmpty(events)) {
            return null;
        }
        int i;
        EventBO eventBO;
        boolean groovyPassed;
        Map params = JSON.parseObject(body, Map.class);
        for (i = 0; i < events.size(); i++) {
            eventBO = events.get(i);
            if (StringUtils.isNotBlank(eventBO.getCheckScript())) {
                CompiledScript compiledScript = GroovyEngine.compile(eventBO.getCheckScript());
                try {
                    groovyPassed = GroovyEngine.evalReturnBool(compiledScript, params);
                } catch (ScriptException e) {
                    LOGGER.warn("execute groovy script error, eventName={} , eventId={}， msg:{}， e", eventName, eventBO.getId(), e);
                    groovyPassed = false;
                }
                if (groovyPassed) {
                    return eventBO;
                }
            } else {
                // 没有检测脚本直接返回
                return eventBO;
            }
        }
        return null;
    }

    private List<EventBO> listByIdentifier(Integer eventSource, String eventName) {
        return eventBeanMapper.eventBaseInfoDO2EventBO(eventBaseInfoMapper.listByIdentifier(eventSource, eventName));
    }

}
