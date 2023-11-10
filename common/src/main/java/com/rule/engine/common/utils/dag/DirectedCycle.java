package com.rule.engine.common.utils.dag;

import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/10 10:52 上午
 * description：算法导论22.3节深度优先搜索中，对每个节点分为三种状态，白、灰、黑。开始时所有节点都是白色，
 * 当开始访问某个节点时该节点变为灰色，当该节点的所有邻接点都访问完，该节点颜色变为黑色。
 * 那么我们的算法则为：如果遍历的过程中发现某个节点有一条边指向颜色为灰的节点，那么存在环
 */
public class DirectedCycle<T> {

    /**
     * 标记当前节点是否访问过
     * 0为当前结点未访问, 1当前节点开始访问, -1表示当前结点的所有临界点都被访问过。
     */
    private Map<T, Integer> color;

    /**
     * 邻接表
     */
    private Digraph<T> digraph;

    /**
     * 白色
     */
    public static final Integer WHITE = 0;

    /**
     * 灰色
     */
    public static final Integer GREY = 1;

    /**
     * 黑色
     */
    public static final Integer BLACK = -1;

    /**
     * 是否是DAG
     */
    private boolean DAG = true;


    public DirectedCycle(Digraph<T> digraph) {
        this.digraph = digraph;
        List<T> allVertex = digraph.fetchAllVertex();
        color = CollectionUtils.isEmpty(allVertex) ? Maps.newHashMap() : Maps.newHashMapWithExpectedSize(allVertex.size());
    }

    public boolean isDAG() {
        return DAG;
    }

    public Integer getColorByVertex(T node) {
        return color.getOrDefault(node, WHITE);
    }

    /**
     * 深度搜索
     *
     * @param node
     */
    public void dfs(T node) {
        color.put(node, GREY);
        Iterable<T> iterable = digraph.fetchEdgesByVertex(node);
        if (Objects.nonNull(iterable)) {
            // 查看一个顶点的所有出度顶点
            for (T w : iterable) {
                if (!DAG) {
                    return;
                }
                if (GREY.equals(color.getOrDefault(w, WHITE))) {
                    DAG = false;
                    return;
                } else if (BLACK.equals(color.getOrDefault(w, WHITE))) {
                    return;
                } else {
                    dfs(w);
                }
            }
        }
        color.put(node, BLACK);
    }

}
