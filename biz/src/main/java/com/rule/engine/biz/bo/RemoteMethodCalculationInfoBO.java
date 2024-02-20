package com.rule.engine.biz.bo;

import com.rule.engine.api.enums.IndicatorValueTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/1/29 5:41 下午
 * description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemoteMethodCalculationInfoBO {

    /**
     * 外部服务ID.
     */
    private Long id;

    /**
     * 外部服务名称.
     */
    private String remoteMethodName;

    /**
     * 外部服务返回类型.
     * #{@link IndicatorValueTypeEnum}
     */
    private Integer remoteMethodReturnType;

    /**
     * 外部服务描述信息.
     */
    private String remoteMethodDesc;

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
