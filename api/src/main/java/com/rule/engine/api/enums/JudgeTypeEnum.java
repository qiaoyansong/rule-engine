package com.rule.engine.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
}
