package graph.traverse;

import graph.Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import static graph.GraphGenerator.createGraph;

/**
 * @Author ws
 * @Date 2021/4/16 13:27
 * @Version 1.0
 */
public class BFS {
    public static void bfs(Graph.Node node) {
        if (node == null) {
            return;
        }
        HashSet<Graph.Node> set = new HashSet<>();
        Queue<Graph.Node> queue = new LinkedList<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Graph.Node current = queue.poll();
            System.out.println(current.value);
            if (!current.nextList.isEmpty()) {
                for (Graph.Node nextNode : current.nextList) {
                    if (!set.contains(nextNode)) {
                        set.add(nextNode);
                        queue.add(nextNode);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix=new int[][]{{2,3,55},{3,4,66},{2,5,77},{5,4,88}};
        Graph graph = createGraph(matrix);
        Graph.Node node = graph.nodes.get(2);
        bfs(node);
    }
}
