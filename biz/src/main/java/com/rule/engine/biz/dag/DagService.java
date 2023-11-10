package com.rule.engine.biz.dag;

import com.rule.engine.biz.bo.StageInfoBO;
import com.rule.engine.common.utils.dag.Digraph;

import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/10 3:48 下午
 * description：
 */
public interface DagService {

    /**
     * 判断一个图是否有环
     *
     * @param digraph
     * @return
     */
    <T> boolean isDag(Digraph<T> digraph);

    /**
     * 根据图批量生成调度信息
     *
     * @param digraph
     * @return
     */
    <T> List<StageInfoBO<T>> buildBatchExecuteInfo(Digraph<T> digraph);

}
