package com.rule.engine.api.mis.param.indicator;

import com.rule.engine.api.enums.*;
import com.rule.engine.api.mis.param.BaseMisOptParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/1/29 2:50 下午
 * description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MisAddIndicatorInfoParam extends BaseMisOptParam implements Serializable {

    private static final long serialVersionUID = -8315955833703174966L;

    /**
     * 指标名称
     */
    private String indicatorName;

    /**
     * 指标描述
     */
    private String indicatorDesc;

    /**
     * 指标类型
     * #{@link IndicatorTypeEnum}
     */
    private Integer indicatorType;

    /**
     * 指标值类型，如果是三方服务，则指标值类型为三方服务的返回值类型，且不支持修改
     * #{@link IndicatorValueTypeEnum}
     */
    private Integer indicatorValueType;

    /**
     * 指标模版信息
     */
    private IndicatorTemplate indicatorTemplate;

    /**
     * 事件解析配置，仅指标类型为事件解析的时候有值
     */
    private EventParseInfo eventParseInfo;

    /**
     * 三方服务配置
     */
    private RemoteMethodConfig remoteMethodConfig;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RemoteMethodConfig implements Serializable {

        private static final long serialVersionUID = 4081652092823247837L;

        /**
         * 远程服务id
         */
        private Long remoteMethodId;

        /**
         * 远程服务全限定名
         */
        private String remoteMethodName;

        /**
         * 参数列表
         */
        private List<ArgInfo> argInfoList;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class ArgInfo implements Serializable {

            private static final long serialVersionUID = -5602712799690135204L;

            /**
             * 实际参数名字 对应三方服务接口的形参名称，仅展示 不支持修改
             */
            private String argName;

            /**
             * 是否动态参数，如果是动态参数 支持进行参数交互输入，即允许手动输入参数
             */
            private Boolean dynamicArg;

            /**
             * 引用类型
             * #{@link RefTypeEnum}
             */
            private Integer argRefType;

            /**
             * 引入参数
             */
            private Object argValue;

            /**
             * 参数数据类型,外部三方服务 对应形参的类型 只支持查看不支持变更
             * #{@link IndicatorValueTypeEnum}
             */
            private Integer argType;

            /**
             * 参数交互输入类型，当且仅当为动态参数的时候 支持填写
             * #{@link InputTypeEnum}
             */
            private Integer argInputType;

            /**
             * 参数取值列表，适用于参数输入类型为下拉列表的情况
             */
            private List<Operands> rightValueList;

            /**
             * 接口查询的外部服务名称全限定名称，适用于参数输入类型为下拉列表 以及 更复杂参数输入类型的情况
             */
            private String interfaceName;
        }

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IndicatorTemplate implements Serializable {

        private static final long serialVersionUID = 6691450180962691237L;

        /**
         * 右值提示文案
         */
        private String rightValuePlaceHolder;

        /**
         * 右值输入类型
         * #{@link InputTypeEnum}
         */
        private Integer rightValueInputType;

        /**
         * 操作符类型
         * #{@link JudgeTypeEnum#character}
         */
        private List<String> judgeTypeList;

        /**
         * 右值取值列表，适用于右值输入类型为下拉列表的情况
         */
        private List<Operands> rightValueList;

        /**
         * 接口查询的外部服务名称全限定名称，适用于右值输入类型为下拉列表 以及 更复杂右值输入类型的情况
         */
        private String interfaceName;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Operands implements Serializable {

        private static final long serialVersionUID = -1151638628504688260L;

        /**
         * 下拉列表选项名称，用于前端展示
         */
        private String name;

        /**
         * 下拉列表实际选项，对应的value值
         */
        private Object value;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EventParseInfo implements Serializable {

        private static final long serialVersionUID = -3679458741500588332L;

        /**
         * 事件id
         */
        private Long eventId;

        /**
         * 解析字段的json path
         */
        private String marshallField;

    }

}
