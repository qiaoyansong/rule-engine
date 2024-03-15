package com.rule.engine.biz.indicator.calc.local.helper;

import com.rule.engine.api.enums.IndicatorValueTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/3/6 5:07 下午
 * description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalFunctionDefinition {

    /**
     * 接口全限定类名
     */
    private String functionName;

    /**
     * 接口描述信息
     */
    private String functionDesc;

    /**
     * bean
     */
    private Object object;

    /**
     * 对应的方法
     */
    private Method method;

    /**
     * 参数名称List
     */
    private List<String> args;

    /**
     * 指标值类型，如果是三方服务，则指标值类型为三方服务的返回值类型，且不支持修改
     * #{@link IndicatorValueTypeEnum}
     */
    private Integer functionValueType;
}
