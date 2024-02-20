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
    MQ(1, "使用MQ的方式"),
    /**
     * RPC 调用
     */
    INTERFACE(2, "直接调用接口的方式");
    private Integer code;
    private String desc;
}
