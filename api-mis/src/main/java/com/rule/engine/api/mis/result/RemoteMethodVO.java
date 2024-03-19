package com.rule.engine.api.mis.result;

import com.rule.engine.api.enums.IndicatorValueTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/3/18 5:10 下午
 * description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemoteMethodVO implements Serializable {

    private static final long serialVersionUID = -8575661217114232334L;

    /**
     * 远程服务id，本地函数为空
     */
    private Long remoteMethodId;

    /**
     * 服务全限定名.
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
    private List<ArgInfoVO> argumentList;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ArgInfoVO implements Serializable {

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
