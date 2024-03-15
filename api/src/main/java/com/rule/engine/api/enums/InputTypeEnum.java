package com.rule.engine.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/1/29 3:18 下午
 * description：
 */
@Getter
@AllArgsConstructor
public enum InputTypeEnum {

    INPUT(1, "输入框组件"),
    DROPDOWN_RADIO_SELECTION(2, "下拉单选组件"),
    DROPDOWN_MULTIPLE_SELECTION(3, "下拉多选组件"),
    ;
    private Integer code;
    private String desc;

    private static final Map<Integer, InputTypeEnum> TYPE_MAP;

    static {
        Map<Integer, InputTypeEnum> map = new HashMap<>();
        for (InputTypeEnum bizTypeEnum : InputTypeEnum.values()) {
            map.put(bizTypeEnum.getCode(), bizTypeEnum);
        }
        TYPE_MAP = Collections.unmodifiableMap(map);
    }

    public static InputTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        return TYPE_MAP.get(code);
    }
}
