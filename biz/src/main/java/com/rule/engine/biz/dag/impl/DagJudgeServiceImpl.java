package com.rule.engine.biz.dag.impl;

import com.rule.engine.biz.dag.DagJudgeService;
import com.rule.engine.common.utils.dag.Digraph;
import com.rule.engine.common.utils.dag.DirectedCycle;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/10 3:50 下午
 * description：
 */
@Service
public class DagJudgeServiceImpl implements DagJudgeService {

    @Override
    public <T> boolean isDag(Digraph<T> digraph) {
        DirectedCycle<T> directedCycle = new DirectedCycle<>(digraph);
        List<T> allVertex = digraph.fetchAllVertex();
        if (CollectionUtils.isEmpty(allVertex)) {
            return true;
        }
        for (T vertex : allVertex) {
            if (Objects.isNull(vertex)) {
                continue;
            }
            // 该结点后边的结点都被访问过了，跳过它
            if (DirectedCycle.BLACK.equals(directedCycle.getColorByVertex(vertex))) {
                continue;
            }
            directedCycle.dfs(vertex);
            if (Boolean.FALSE.equals(directedCycle.isDAG())) {
                return false;
            }
        }
        return true;
    }

}
