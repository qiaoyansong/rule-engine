package com.rule.engine.biz.bo;

import com.rule.engine.api.enums.IndicatorValueTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/3/6 4:59 下午
 * description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FunctionCalculationInfoBO {

    /**
     * 函数名字(全限定名).
     */
    private String functionName;

    /**
     * 函数返回类型.
     * #{@link IndicatorValueTypeEnum}
     */
    private Integer functionMethodReturnType;

    /**
     * 函数描述信息.
     */
    private String functionMethodDesc;

    /**
     * 实际参数列表.
     */
    private List<ArgumentBO> argumentList;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ArgumentBO {

        /**
         * 实际参数名字.
         */
        private String argName;

        /**
         * 实际参数类型.
         * #{@link IndicatorValueTypeEnum}
         */
        private Integer argType;

        /**
         * 实际参数值.
         */
        private Object argValue;
    }
}
