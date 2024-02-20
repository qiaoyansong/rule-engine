package com.rule.engine.api.mis.param.generic;

import com.rule.engine.api.enums.IndicatorValueTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/1/29 4:57 下午
 * description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemoteMethodCalculationParam implements Serializable {

    private static final long serialVersionUID = -8405020163031142338L;

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
    private List<ArgInfo> argumentList;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ArgInfo implements Serializable {

        private static final long serialVersionUID = -5602712799690135204L;

        /**
         * 实际参数名字.
         */
        private String argName;

        /**
         * 参数数据类型
         * #{@link IndicatorValueTypeEnum}
         */
        private Integer argType;

        /**
         * 参数描述信息
         */
        private String argDesc;
    }

}
