package com.rule.engine.common.utils.dag;

import java.util.Iterator;
import java.util.Objects;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/9 8:18 下午
 * description：
 */
public class DAGIterator<T> implements Iterator<T> {

    private Digraph.DigraphTuple.DAGBagNode<T> node;

    public DAGIterator(Digraph.DigraphTuple.DAGBagNode<T> head) {
        node = head;
    }

    @Override
    public boolean hasNext() {
        return Objects.nonNull(node);
    }

    @Override
    public T next() {
        Digraph.DigraphTuple.DAGBagNode<T> preNode = node;
        node = node.next;
        return preNode.item;
    }
}
