package com.rule.engine.common.utils.dag;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import lombok.*;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/9 7:24 下午
 * description：图
 */
@Getter
@Setter
@ToString
public class Digraph<T> {

    /**
     * 邻接表
     */
    private List<DigraphTuple<T>> bagTuple;

    public Digraph() {
        this.bagTuple = Lists.newArrayList();
    }

    /**
     * 添加DAG图的顶点
     *
     * @param node
     */
    public void addNode(T node) {
        DigraphTuple<T> tuple = findDigraphTupleByNode(node);
        if (Objects.isNull(tuple)) {
            tuple = new DigraphTuple<>(node, null);
            bagTuple.add(tuple);
        }
    }

    /**
     * 添加DAG图的边
     * node1和node2是两个顶点，中间加一条边，
     *
     * @param node1
     * @param node2
     */
    public void addEdge(T node1, T node2) {
        DigraphTuple<T> tuple1 = findDigraphTupleByNode(node1);
        DigraphTuple<T> tuple2 = findDigraphTupleByNode(node2);
        if (Objects.isNull(tuple1)) {
            tuple1 = new DigraphTuple<>(node1, null);
            bagTuple.add(tuple1);
        }
        if (Objects.isNull(tuple2)) {
            tuple2 = new DigraphTuple<>(node2, null);
            bagTuple.add(tuple2);
        }
        tuple1.addNode(node2);
    }

    /**
     * 返回一个顶点的连通顶点出度的结合
     *
     * @param v
     * @return
     */
    public Iterable<T> fetchEdgesByVertex(T v) {
        DigraphTuple<T> tuple = findDigraphTupleByNode(v);
        if (Objects.isNull(tuple)) {
            return null;
        }
        return tuple.dagBagNode;
    }

    /**
     * 获取图的所有节点信息.
     *
     * @return
     */
    public List<T> fetchAllVertex() {
        if (CollectionUtils.isEmpty(bagTuple)) {
            return Lists.newArrayList();
        }
        return bagTuple.stream()
                .map(DigraphTuple::getNode)
                .collect(Collectors.toList());
    }

    /**
     * 将图中所有方向反转
     *
     * @return 返回一个图将所有方向反转后的副本
     */
    public Digraph<T> reverse() {
        List<T> nodes = fetchAllVertex();
        if (CollectionUtils.isEmpty(nodes)) {
            return new Digraph<>();
        }
        Digraph<T> R = buildDigraph(nodes);
        Iterable<T> iterable;
        for (T node : nodes) {
            iterable = fetchEdgesByVertex(node);
            if (Objects.nonNull(iterable)) {
                for (T child : iterable) {
                    R.addEdge(child, node);
                }
            }
        }
        return R;
    }

    private Digraph<T> buildDigraph(List<T> nodeLists) {
        Digraph<T> digraph = new Digraph<>();
        for (T node : nodeLists) {
            digraph.bagTuple.add(new DigraphTuple<>(node, null));
        }
        return digraph;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DigraphTuple<T> {

        /**
         * 头结点(哨兵节点，头结点不在出度节点中，出度节点仅代表所能直接到达的节点)
         */
        private T node;

        /**
         * 出度节点
         */
        private DAGBagNode<T> dagBagNode;

        /**
         * 出度节点新增item
         *
         * @param item
         */
        public void addNode(T item) {
            if (Objects.isNull(dagBagNode)) {
                // init
                dagBagNode = new DAGBagNode<>(item, null);
            } else {
                // append node
                DAGBagNode<T> curNode = new DAGBagNode<>();
                curNode.item = item;
                curNode.next = dagBagNode;
                dagBagNode = curNode;
            }
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class DAGBagNode<T> implements Iterable<T> {

            /**
             * 元素
             */
            public T item;

            /**
             * 下一个节点
             */
            public DAGBagNode<T> next;

            @Override
            public Iterator<T> iterator() {
                return new DAGIterator<>(this);
            }
        }
    }

    private DigraphTuple<T> findDigraphTupleByNode(T item) {
        Preconditions.checkArgument(Objects.nonNull(item));
        for (DigraphTuple<T> tuple : bagTuple) {
            if (tuple.node.equals(item)) {
                return tuple;
            }
        }
        return null;
    }
}
