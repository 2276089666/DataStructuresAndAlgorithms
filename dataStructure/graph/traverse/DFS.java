package graph.traverse;

import graph.Graph;

import java.util.*;

import static graph.GraphGenerator.createGraph;

/**
 * @Author ws
 * @Date 2021/4/16 13:52
 * @Version 1.0
 */
public class DFS {
    public static void dfs(Graph.Node node){
        if (node==null){
            return;
        }
        Stack<Graph.Node> stack = new Stack<>();
        Set<Graph.Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()){
            Graph.Node current = stack.pop();
            if (!current.nextList.isEmpty()){
                for (Graph.Node nodeNext : current.nextList) {
                    if (!set.contains(nodeNext)){
                        // stack里面保存的就是当前深度遍历的节点路径
                        stack.add(current);
                        stack.add(nodeNext);
                        set.add(nodeNext);
                        System.out.println(nodeNext.value);
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix=new int[][]{{2,3,55},{3,4,66},{2,5,77},{5,4,88}};
        Graph graph = createGraph(matrix);
        Graph.Node node = graph.nodes.get(2);
        dfs(node);
    }
}
