package com.rule.engine.biz.indicator.calc.local.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/3/6 5:25 下午
 * description：
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LocalFunctionClazz {
}
