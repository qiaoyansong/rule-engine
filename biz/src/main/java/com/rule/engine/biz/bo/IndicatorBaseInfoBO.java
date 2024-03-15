package com.rule.engine.biz.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/8 3:50 下午
 * description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndicatorBaseInfoBO {

    /**
     * 表主键。
     */
    private Long id;

    /**
     * 指标名字。
     */
    private String indicatorName;

    /**
     * 指标描述。
     */
    private String indicatorDesc;

    /**
     * 指标返回类型。
     * #{@link com.rule.engine.api.enums.IndicatorValueTypeEnum}
     */
    private Integer indicatorReturnType;

    /**
     * 指标类型。
     * #{@link com.rule.engine.api.enums.IndicatorTypeEnum}
     */
    private Integer indicatorType;

    /**
     * 指标状态。
     * #{@link com.rule.engine.api.enums.IndicatorConfigStatusEnum}
     */
    private Integer status;

    /**
     * 创建者姓名。
     */
    private String creatorName;

    /**
     * 创建者ID.
     */
    private Long creatorId;

}
