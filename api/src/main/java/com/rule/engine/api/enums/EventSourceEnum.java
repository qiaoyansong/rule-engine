package com.rule.engine.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/8 5:01 下午
 * description：
 */
@Getter
@AllArgsConstructor
public enum EventSourceEnum {

    /**
     * 消息，系统内部通过mq consumer的方式进行处理
     */
    MQ(1, "MQ"),

    /**
     * 外部服务，直接通过rpc调用本服务
     */
    INTERFACE(2, "直接调用接口的方式");
    private Integer code;
    private String desc;

    private static final Map<Integer, EventSourceEnum> TYPE_MAP;

    static {
        Map<Integer, EventSourceEnum> map = new HashMap<>();
        for (EventSourceEnum bizTypeEnum : EventSourceEnum.values()) {
            map.put(bizTypeEnum.getCode(), bizTypeEnum);
        }
        TYPE_MAP = Collections.unmodifiableMap(map);
    }

    public static EventSourceEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        return TYPE_MAP.get(code);
    }
}
