package com.rule.engine.biz.indicator.calc.local.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/3/6 5:40 下午
 * description：
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface LocalFunctionParam {

    /**
     * 入参名称
     *
     * @return
     */
    String argName();
}
