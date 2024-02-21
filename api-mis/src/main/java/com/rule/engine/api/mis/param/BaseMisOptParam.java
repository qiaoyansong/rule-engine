package com.rule.engine.api.mis.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/2/21 11:42 上午
 * description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseMisOptParam implements Serializable {

    private static final long serialVersionUID = -4398901401891013616L;

    /**
     * 操作人id
     */
    private Long operatorId;

    /**
     * 操作人名字
     */
    private String operatorName;
}
