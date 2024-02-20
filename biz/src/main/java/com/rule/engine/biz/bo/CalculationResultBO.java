package com.rule.engine.biz.bo;

import com.rule.engine.api.enums.IndicatorValueTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/1/29 5:40 下午
 * description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculationResultBO {

    /**
     * 数据类型.
     * #{@link IndicatorValueTypeEnum}
     */
    private Integer dataType;

    /**
     * 数据值。
     */
    private Object value;
}
