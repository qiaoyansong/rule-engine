package com.rule.engine.api.result.indicator;

import com.rule.engine.api.enums.IndicatorValueTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/9 3:14 下午
 * description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndicatorResultInfo implements Serializable {

    private static final long serialVersionUID = -8281131898216819261L;

    private List<IndicatorResultDetail> indicatorResultDetails;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IndicatorResultDetail implements Serializable {

        private static final long serialVersionUID = -5214084287351272373L;

        /**
         * 指标ID.
         */
        private Long indicatorId;

        /**
         * 指标值.
         */
        private Object indicatorValue;

        /**
         * 指标值数据类型.
         * #{@link IndicatorValueTypeEnum}
         */
        private Integer indicatorValueType;
    }

}
