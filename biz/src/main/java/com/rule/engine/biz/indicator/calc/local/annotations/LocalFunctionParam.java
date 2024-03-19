package com.rule.engine.biz.indicator.calc.local.annotations;

import com.rule.engine.api.enums.IndicatorValueTypeEnum;

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

    /**
     * 参数数据类型
     * #{@link IndicatorValueTypeEnum}
     */
    IndicatorValueTypeEnum argType();

    /**
     * 参数描述信息
     *
     * @return
     */
    String argDesc();
}
