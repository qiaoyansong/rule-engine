package com.rule.engine.biz.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/9 10:46 上午
 * description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventParseIndicatorInfoBO {

    /**
     * 指标ID.
     */
    private Long    indicatorId;

    /**
     * 指标名字。
     */
    private String  indicatorName;

    /**
     * 指标返回类型。
     *  #{@link com.rule.engine.api.enums.IndicatorValueTypeEnum}
     */
    private Integer indicatorReturnType;

    /**
     * 事件ID.
     */
    private Long    eventId;

    /**
     * 指标类型。
     * #{@link com.rule.engine.api.enums.IndicatorTypeEnum}
     */
    private Integer indicatorType;

    /**
     * 解析的字段名称
     */
    private String  marshallField;
}
