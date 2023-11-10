package com.rule.engine.biz.dag.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.rule.engine.biz.bo.StageInfoBO;
import com.rule.engine.biz.dag.DagService;
import com.rule.engine.common.utils.dag.Digraph;
import com.rule.engine.common.utils.dag.DirectedCycle;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/10 3:50 下午
 * description：
 */
@Service
public class DagServiceImpl implements DagService {

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

    @Override
    public <T> List<StageInfoBO<T>> buildBatchExecuteInfo(Digraph<T> digraph) {
        int stageIndex = 1;
        List<StageInfoBO<T>> stageInfoBOList = Lists.newArrayList();
        /*
         * 按批次获取各个层级的出度为零的顶点，同一批次出度为零的顶点构成一个执行批次。
         */
        Set<T> leafExecutionNodes = null;
        do {
            leafExecutionNodes = batchCollectLeafExecutionNode(digraph);
            if (CollectionUtils.isEmpty(leafExecutionNodes)) {
                break;
            }
            StageInfoBO<T> stageInfoBO = new StageInfoBO<>();
            stageInfoBO.setStageId(stageIndex);
            stageInfoBO.setExecutionNodes(leafExecutionNodes);
            stageInfoBOList.add(stageInfoBO);
            stageIndex++;
            // 删除叶子结点
            digraph.removeTarNodes(leafExecutionNodes);
        } while (true);

        return stageInfoBOList;
    }

    /**
     * 批量获取图的叶子结点
     *
     * @param digraph
     * @return
     */
    private <T> Set<T> batchCollectLeafExecutionNode(Digraph<T> digraph) {
        List<T> executionNodeInfo = digraph.fetchAllVertex();
        if (CollectionUtils.isEmpty(executionNodeInfo)) {
            return Sets.newHashSet();
        }
        Set<T> leafExecutionNodeSet = Sets.newHashSet();
        for (T node : executionNodeInfo) {
            Iterable<T> adjNodes = digraph.fetchEdgesByVertex(node);
            if (adjNodes == null || !adjNodes.iterator().hasNext()) {
                leafExecutionNodeSet.add(node);
            }
        }
        return leafExecutionNodeSet;
    }

}
