package com.rule.engine.common.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/8 4:02 下午
 * description：
 */
public class ThreadPoolUtil {

    /**
     * 指标基础信息更新线程池
     */
    public static final ThreadPoolExecutor INDICATOR_BASE_INFO_REFRESH =
            new ThreadPoolExecutor(
                    15,
                    15,
                    0,
                    TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>(100000),
                    new ThreadFactoryBuilder().setNameFormat("indicator-base-info-refresh-pool_").build(),
                    new ThreadPoolExecutor.CallerRunsPolicy());

    /**
     * 事件解析指标规则基础信息更新线程池
     */
    public static final ThreadPoolExecutor EVENT_PARSING_INDICATOR_INFO_REFRESH =
            new ThreadPoolExecutor(
                    15,
                    15,
                    0,
                    TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>(100000),
                    new ThreadFactoryBuilder().setNameFormat("event-parsing-indicator-info-refresh-pool_").build(),
                    new ThreadPoolExecutor.CallerRunsPolicy());

    /**
     * 事件解析指标规则基础信息更新线程池
     */
    public static final ThreadPoolExecutor GENERIC_SERVICE_REFRESH =
            new ThreadPoolExecutor(
                    15,
                    15,
                    0,
                    TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>(100000),
                    new ThreadFactoryBuilder().setNameFormat("generic-service-refresh-pool_").build(),
                    new ThreadPoolExecutor.CallerRunsPolicy());
}
