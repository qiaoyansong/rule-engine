package com.rule.engine.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/8 5:46 下午
 * description：
 */
@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

    NOT_EXISTS_CACHE(600001, "缓存中不存在!");

    private final int code;

    private final String desc;
}
