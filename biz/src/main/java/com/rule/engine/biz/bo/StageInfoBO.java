package com.rule.engine.biz.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/10 5:11 下午
 * description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StageInfoBO<T> {

    /**
     * 执行阶段编号.
     */
    private Integer stageId;

    /**
     * 本阶段批量执行的数据集合.
     */
    private Set<T> executionNodes;
}
