package com.rule.engine.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/8 5:01 下午
 * description：
 */
@Getter
@AllArgsConstructor
public enum EventSourceEnum {

    /**
     * 消息
     */
    MQ(1),
    /**
     * RPC 调用
     */
    INTERFACE(2);
    private final Integer code;
}
