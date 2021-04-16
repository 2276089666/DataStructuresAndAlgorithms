package graph;

import java.util.*;

import static graph.GraphGenerator.createGraph;

/**
 * @Author ws
 * @Date 2021/4/16 14:27
 * @Version 1.0
 */
public class TopologySort {
    /**
     * 拓扑排序：
     * 1）在图中找到所有入度为0的点输出
     * 2）把所有入度为0的点在图中删掉，继续找入度为0的点输出，周而复始
     * 3）图的所有点都被删除后，依次输出的顺序就是拓扑排序
     *
     * 要求：有向图且其中没有环
     * 应用：可以解决依赖文件编译的先后顺序，异步编排的任务顺序
     *
     */

    public static List<Graph.Node> topologySort(Graph graph) {
        if (graph == null) {
            return null;
        }
        // Integer是该节点的入度
        HashMap<Graph.Node, Integer> map = new HashMap<>();
        // 入度为0的节点进入这个队列
        Queue<Graph.Node> queue = new LinkedList<>();
        for (Graph.Node node : graph.nodes.values()) {
            map.put(node, node.in);
            if (node.in == 0) {
                queue.add(node);
            }
        }

        ArrayList<Graph.Node> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            Graph.Node poll = queue.poll();
            result.add(poll);
            if (!poll.nextList.isEmpty()) {
                for (Graph.Node node : poll.nextList) {
                    map.put(node,map.get(node)-1);
                    if (map.get(node) == 0) {
                        queue.add(node);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix=new int[][]{{2,3,55},{3,4,66},{2,5,77},{5,4,88}};
        Graph graph = createGraph(matrix);
        List<Graph.Node> nodes = topologySort(graph);
    }
}
