package com.rule.engine.api.mis.param;

import com.rule.engine.api.enums.JudgeTypeEnum;
import com.rule.engine.api.enums.RuleRelTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/24 5:19 下午
 * description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleConditionParam implements Serializable {

    private static final long serialVersionUID = 24583515542618771L;

    private List<Node> nodes;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Node implements Serializable {

        private static final long serialVersionUID = -3386387155392463841L;

        /**
         * #{@link RuleRelTypeEnum#relDesc}
         */
        private String beforeOpt;

        private List<Expression> expressions;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Expression implements Serializable {

            private static final long serialVersionUID = 7880820217055287065L;

            /**
             * 指标ID left
             */
            private Long indicatorId;

            /**
             * 判断类型
             * #{@link JudgeTypeEnum#desc}
             */
            private String judgeType;

            /**
             * 判断值 right
             */
            private Object judgeValue;

            /**
             * #{@link RuleRelTypeEnum#relDesc}
             */
            private String beforeOpt;
        }
    }
}
