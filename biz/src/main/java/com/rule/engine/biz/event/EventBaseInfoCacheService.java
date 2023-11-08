package com.rule.engine.biz.event;

import com.alibaba.fastjson.TypeReference;

import java.util.function.Supplier;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/8 5:37 下午
 * description：
 */
public interface EventBaseInfoCacheService {

    /**
     * 从缓存中获取数据，如果缓存中不存在，则从底层数据获取，并加入缓存
     *
     * @param key           缓存key
     * @param getInfoFromDb 从DB获取数据的方法
     * @param ttlSeconds    缓存失效时间
     * @param type
     * @return
     */
    <T> T getInfoFromCache(String key, Supplier<T> getInfoFromDb, Integer ttlSeconds, TypeReference<T> type);

    /**
     * 刷新缓存
     *
     * @param key
     * @param getInfoFromDb
     * @param ttlSeconds
     * @return
     */
    <T> boolean refreshCache(String key, Supplier<T> getInfoFromDb, Integer ttlSeconds);
}
