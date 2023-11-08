package com.rule.engine.common.constant;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/8 5:50 下午
 * description：
 */
public class CacheKeys {

    public static final int FIVE_MIN_SECONDS = 5 * 60;

    /**
     * 事件缓存key eventSource + eventName
     */
    public static final String EVENT_CACHE_KEY = "event_cache_%s_%s";
}
