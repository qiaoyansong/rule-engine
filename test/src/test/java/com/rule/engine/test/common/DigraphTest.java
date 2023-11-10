package com.rule.engine.test.common;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.rule.engine.biz.dag.DagService;
import com.rule.engine.common.utils.dag.Digraph;
import com.rule.engine.test.BaseTestApplication;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/9 7:39 下午
 * description：
 */
public class DigraphTest extends BaseTestApplication {

    @Resource
    private DagService dagService;

    /**
     *     1
     *  2    3
     * 4   5  6
     *    7
     * 整体的关系如上图所示
     */
    private ImmutableMap<Integer, List<Integer>> mockData = ImmutableMap.of(
            1, Lists.newArrayList(2, 3),
            2, Lists.newArrayList(4),
            3, Lists.newArrayList(5, 6),
            5, Lists.newArrayList(7)
    );


    @Test
    public void test() {
        Digraph<Integer> digraph = new Digraph<>();
        buildExecutionDAGInternal(1, digraph);
        System.out.println(JSON.toJSONString(digraph));
        System.out.println(JSON.toJSONString(digraph.fetchEdgesByVertex(1)));
        System.out.println(JSON.toJSONString(digraph.fetchEdgesByVertex(2)));
        System.out.println(JSON.toJSONString(digraph.fetchEdgesByVertex(3)));
        System.out.println(JSON.toJSONString(digraph.fetchEdgesByVertex(4)));
        System.out.println(JSON.toJSONString(digraph.fetchEdgesByVertex(5)));
        System.out.println(JSON.toJSONString(digraph.fetchEdgesByVertex(6)));
        System.out.println(JSON.toJSONString(digraph.fetchEdgesByVertex(7)));
        System.out.println(JSON.toJSONString(digraph.fetchAllVertex()));
        System.out.println(JSON.toJSONString(digraph.reverse()));

    }

    /**
     * 需要注意的是，这种递归方式生成dag图，如果本身存在循环引用的问题 会导致栈溢出
     * 但是Digraph提供了 判断图是否存在环的方法(适用于代码手动 addNode addEdge而不是通过递归的方式 这点需要特别注意)
     */
    private void buildExecutionDAGInternal(int i, Digraph<Integer> digraph) {
        digraph.addNode(i);
        List<Integer> refNodes = mockData.get(i);
        if (CollectionUtils.isEmpty(refNodes)) {
            return;
        }
        refNodes.forEach(refNode -> {
            digraph.addEdge(i, refNode);
            buildExecutionDAGInternal(refNode, digraph);
        });
    }

    @Test
    public void isCyclicDependence_Test_simple() {
        Digraph<Integer> digraph = new Digraph<>();
        digraph.addEdge(1, 2);
        digraph.addEdge(2, 3);
        digraph.addEdge(3, 4);
        digraph.addEdge(2, 5);
        digraph.addEdge(5, 4);
        System.out.println(JSON.toJSONString(digraph));
        System.out.println(JSON.toJSONString(digraph.fetchEdgesByVertex(1)));
        System.out.println(JSON.toJSONString(digraph.fetchEdgesByVertex(2)));
        System.out.println(JSON.toJSONString(digraph.fetchEdgesByVertex(3)));
        System.out.println(JSON.toJSONString(digraph.fetchEdgesByVertex(4)));
        System.out.println(JSON.toJSONString(digraph.fetchEdgesByVertex(5)));
        System.out.println(JSON.toJSONString(digraph.fetchAllVertex()));
        System.out.println(dagService.isDag(digraph));
    }

    @Test
    public void isCyclicDependence_Test_NotDAG() {
        Digraph<Integer> digraph = new Digraph<>();
        digraph.addEdge(1, 3);
        digraph.addEdge(2, 3);
        digraph.addEdge(3, 4);
        digraph.addEdge(4, 5);
        digraph.addEdge(5, 2);
        System.out.println(JSON.toJSONString(digraph));
        System.out.println(JSON.toJSONString(digraph.fetchEdgesByVertex(1)));
        System.out.println(JSON.toJSONString(digraph.fetchEdgesByVertex(2)));
        System.out.println(JSON.toJSONString(digraph.fetchEdgesByVertex(3)));
        System.out.println(JSON.toJSONString(digraph.fetchEdgesByVertex(4)));
        System.out.println(JSON.toJSONString(digraph.fetchEdgesByVertex(5)));
        System.out.println(JSON.toJSONString(digraph.fetchAllVertex()));
        System.out.println(dagService.isDag(digraph));
    }
}
