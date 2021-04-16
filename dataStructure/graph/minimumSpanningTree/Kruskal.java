package graph.minimumSpanningTree;

import graph.Graph;
import graph.UnionFind;
import java.util.*;
import static graph.GraphGenerator.createGraph;

/**
 * @Author ws
 * @Date 2021/4/16 15:39
 * @Version 1.0
 */
public class Kruskal {
    /**
     * 最小生成树算法之Kruskal
     * 1）总是从权值最小的边开始考虑，依次考察权值依次变大的边
     * 2）当前的边要么进入最小生成树的集合，要么丢弃
     * 3）如果当前的边进入最小生成树的集合中不会形成环，就要当前边
     * 4）如果当前的边进入最小生成树的集合中会形成环，就不要当前边
     * 5）考察完所有边之后，最小生成树的集合也得到了
     */

    // 我们使用并查集来解决联通性判断和合并
    public static Set<Graph.Edge> kruskalMST(Graph graph) {
        if (graph == null) {
            return null;
        }
        ArrayList<Graph.Node> nodes = new ArrayList<>(graph.nodes.values());
        UnionFind.UnionSet<Graph.Node> nodeUnionSet = new UnionFind.UnionSet<>(nodes);
        PriorityQueue<Graph.Edge> heap = new PriorityQueue<>(new MyEdgeComparator());
        if (!graph.edges.isEmpty()) {
            for (Graph.Edge edge : graph.edges) {
                heap.add(edge);
            }
        }
        HashSet<Graph.Edge> resultSet = new HashSet<>();
        while (!heap.isEmpty()) {
            Graph.Edge edge = heap.poll();
            if (!nodeUnionSet.isSameSet(edge.from, edge.to)) {
                nodeUnionSet.union(edge.from, edge.to);
                resultSet.add(edge);
            }
        }
        return resultSet;
    }

    public static class MyEdgeComparator implements Comparator<Graph.Edge> {
        @Override
        public int compare(Graph.Edge o1, Graph.Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static void main(String[] args) {
        int[][] matrix=new int[][]{{2,3,55},{3,4,66},{2,5,77},{5,4,88}};
        Graph graph = createGraph(matrix);
        Set<Graph.Edge> edges = kruskalMST(graph);
    }
}
