package com.rule.engine.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/28 2:32 下午
 * description：
 */
@Getter
@AllArgsConstructor
public enum JudgeTypeEnum {

    EQ("=", "等于"),
    NE("!=", "不等于"),
    LT("<", "小于"),
    GT(">", "大于"),
    LE("<=", "小于等于"),
    GE(">=", "大于等于"),
    IN("in", "in"),
    NOTIN("not in", "not in"),
    ;
    private String character;
    private String desc;

    private static final Map<String, JudgeTypeEnum> TYPE_MAP;

    static {
        Map<String, JudgeTypeEnum> map = new HashMap<>();
        for (JudgeTypeEnum bizTypeEnum : JudgeTypeEnum.values()) {
            map.put(bizTypeEnum.getCharacter(), bizTypeEnum);
        }
        TYPE_MAP = Collections.unmodifiableMap(map);
    }

    public static JudgeTypeEnum getByCode(String code) {
        if (code == null) {
            return null;
        }
        return TYPE_MAP.get(code);
    }
}
