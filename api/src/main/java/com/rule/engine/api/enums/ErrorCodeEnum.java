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

    NOT_EXISTS_CACHE(600001, "缓存中不存在!"),
    SYSTEM_ERROR(600002, "服务器开小差了~"),
    PARAM_ERROR(600003, "入参错误"),
    NON_MATCH_EVENT(600004, "无匹配事件"),
    ;

    private final int code;

    private final String desc;
}
