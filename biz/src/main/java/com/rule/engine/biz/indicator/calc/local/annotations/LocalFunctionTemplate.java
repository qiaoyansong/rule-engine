package com.rule.engine.biz.indicator.calc.local.annotations;

import com.rule.engine.api.enums.IndicatorValueTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/3/6 5:26 下午
 * description：
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LocalFunctionTemplate {

    /**
     * 接口返回值类型
     */
    IndicatorValueTypeEnum functionValueType();

    /**
     * 接口全限定类名
     */
    String functionName();

    /**
     * 接口描述信息
     */
    String functionDesc();
}
