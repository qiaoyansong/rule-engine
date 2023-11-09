package com.rule.engine.biz.event.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.rule.engine.api.enums.ErrorCodeEnum;
import com.rule.engine.biz.event.EventBaseInfoCacheService;
import com.rule.engine.common.constant.CacheKeys;
import com.rule.engine.biz.exception.BizException;
import com.rule.engine.dal.redis.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Random;
import java.util.function.Supplier;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/8 5:38 下午
 * description：
 */
@Service
public class EventBaseInfoCacheServiceImpl implements EventBaseInfoCacheService {

    @Resource
    private RedisService redisService;

    @Override
    public <T> T getInfoFromCache(String key, Supplier<T> getInfoFromDb, Integer ttlSeconds, TypeReference<T> type) {
        try {
            String cacheValue = redisService.get(key);
            if (StringUtils.isBlank(cacheValue)) {
                throw new BizException(ErrorCodeEnum.NOT_EXISTS_CACHE.getCode(), ErrorCodeEnum.NOT_EXISTS_CACHE.getDesc());
            }
            return JSON.parseObject(cacheValue, type);
        } catch (Throwable t) {
            T info = getInfoFromDb.get();
            if (Objects.nonNull(info)) {
                addCache(key, info, ttlSeconds);
            }
            return info;
        }
    }

    @Override
    public <T> boolean refreshCache(String key, Supplier<T> getInfoFromDb, Integer ttlSeconds) {
        T info = getInfoFromDb.get();
        if (Objects.nonNull(info)) {
            return addCache(key, info, ttlSeconds);
        }
        return true;
    }

    private <T> boolean addCache(String key, T value, Integer ttlSeconds) {
        Random random = new Random();
        int finalTtl = ttlSeconds + random.nextInt(CacheKeys.FIVE_MIN_SECONDS);
        return redisService.setex(key, finalTtl, JSON.toJSONString(value));
    }
}
