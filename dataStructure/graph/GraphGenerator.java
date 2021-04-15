package graph;

/**
 * @Author ws
 * @Date 2021/4/15 22:01
 * @Version 1.0
 */
public class GraphGenerator {

    // N*3的矩阵
    // [from节点上面的值,to节点上面的值,边的权重weight]
    public static Graph createGraph(int[][] matrix){
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            int fromValue = matrix[i][0];
            int toValue = matrix[i][1];
            int weight = matrix[i][2];
            // 设置两个节点
            if (!graph.nodes.containsKey(fromValue)){
                graph.nodes.put(fromValue,new Graph.Node(fromValue));
            }
            if (!graph.nodes.containsKey(toValue)){
                graph.nodes.put(toValue,new Graph.Node(toValue));
            }
            Graph.Node fromNode = graph.nodes.get(fromValue);
            Graph.Node toNode = graph.nodes.get(toValue);
            // 设置边
            Graph.Edge edge = new Graph.Edge(weight, fromNode, toNode);
            fromNode.edges.add(edge);

            fromNode.nextList.add(toNode);
            fromNode.out++;

            toNode.in++;

            graph.edges.add(edge);
        }
        return graph;
    }
}
