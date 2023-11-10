package com.rule.engine.biz.util;

import com.rule.engine.api.enums.ErrorCodeEnum;
import com.rule.engine.biz.exception.BizException;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/9 3:19 下午
 * description：
 */
public class BizChecker {

    private BizChecker() {

    }

    public static void check(boolean condition, ErrorCodeEnum errorCodeEnum) {
        if (!condition) {
            throw new BizException(errorCodeEnum);
        }
    }

    public static void check(boolean condition, ErrorCodeEnum errorCodeEnum, String excDesc) {
        if (!condition) {
            throw new BizException(errorCodeEnum, excDesc);
        }
    }

    public static void checkNotNull(Object obj, ErrorCodeEnum errorCodeEnum) {
        if (obj == null) {
            throw new BizException(errorCodeEnum);
        }
    }

    public static void checkNotNull(Object obj, ErrorCodeEnum errorCodeEnum, String excDesc) {
        if (obj == null) {
            throw new BizException(errorCodeEnum, excDesc);
        }
    }

    public static void checkNotEmpty(Collection<?> collection, ErrorCodeEnum errorCodeEnum) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BizException(errorCodeEnum);
        }
    }

    public static void checkNotEmpty(Collection<?> collection, ErrorCodeEnum errorCodeEnum, String excDesc) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BizException(errorCodeEnum, excDesc);
        }
    }
}