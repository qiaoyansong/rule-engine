package com.rule.engine.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/24 5:23 下午
 * description：
 */
@Getter
@AllArgsConstructor
public enum RuleRelTypeEnum {

    /**
     * &&
     */
    AND("&&"),

    /**
     * ||
     */
    OR("||");

    private final String relDesc;
}
