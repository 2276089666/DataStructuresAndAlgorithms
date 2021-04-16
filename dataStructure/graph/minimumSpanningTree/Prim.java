package graph.minimumSpanningTree;

import graph.Graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import static graph.GraphGenerator.createGraph;

/**
 * @Author ws
 * @Date 2021/4/16 18:06
 * @Version 1.0
 */
public class Prim {
    /**
     * 最小生成树算法之Prim
     * 1）可以从任意节点出发来寻找最小生成树
     * 2）某个点加入到被选取的点中后，解锁这个点出发的所有新的边
     * 3）在所有解锁的边中选最小的边，然后看看这个边会不会形成环
     * 4）如果会，不要当前边，继续考察剩下解锁的边中最小的边，重复3）
     * 5）如果不会，要当前边，将该边的指向点加入到被选取的点中，重复2）
     * 6）当所有点都被选取，最小生成树就得到了
     */

    public static Set<Graph.Edge> primMST(Graph graph) {
        if (graph == null) {
            return null;
        }
        PriorityQueue<Graph.Edge> heap = new PriorityQueue<>(new MyEdgeComparator());
        HashSet<Graph.Node> set = new HashSet<>();
        HashSet<Graph.Edge> result = new HashSet<>(); // 依次挑选的的边在result里
        for (Graph.Node node : graph.nodes.values()) {  // 随便挑一个节点
            if (!set.contains(node)){
                set.add(node);
                for (Graph.Edge edge : node.edges) { // 由一个点，解锁所有相连的边
                    heap.add(edge);  // 小根堆排序
                }
                while (!heap.isEmpty()){
                    Graph.Edge poll = heap.poll();  // 该节点出度边权重最小的
                    Graph.Node toNode = poll.to;  // 可能的一个新的点
                    if (!set.contains(toNode)){ // 不含有的时候，就是新的点
                        set.add(toNode);
                        result.add(poll);  // 这条边应该被选
                        for (Graph.Edge edge : toNode.edges) {
                            heap.add(edge);
                        }
                    }
                }
            }
            break;
        }
        return result;
    }

    public static class MyEdgeComparator implements Comparator<Graph.Edge>{
        @Override
        public int compare(Graph.Edge o1, Graph.Edge o2) {
            return o1.weight-o2.weight;
        }
    }


    public static void main(String[] args) {
        int[][] matrix=new int[][]{{2,3,55},{3,4,66},{2,5,77},{5,4,88}};
        Graph graph = createGraph(matrix);
        Set<Graph.Edge> edges = primMST(graph);
    }
}
