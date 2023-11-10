package com.rule.engine.biz.dag;

import com.rule.engine.common.utils.dag.Digraph;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/10 3:48 下午
 * description：
 */
public interface DagJudgeService {

    /**
     * 判断一个图是否有环
     *
     * @param digraph
     * @return
     */
    <T> boolean isDag(Digraph<T> digraph);

}
