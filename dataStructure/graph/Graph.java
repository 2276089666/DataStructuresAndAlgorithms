package graph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @Author ws
 * @Date 2021/4/15 21:29
 * @Version 1.0
 */
public class Graph {
    public HashMap<Integer, Node> nodes;  //Integer 节点的值 Node节点
    public HashSet<Edge> edges;

    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }

    /**
     * 图的边
     */
    public static class Edge {
       public int weight;
       public Node from;
       public Node to;

        public Edge(int weight, Node from, Node to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }

    /**
     * 图的节点
     */
    public static class Node {
        public int value;
        public int in; // 节点的入度
        public int out; // 节点的出度
        public List<Node> nextList; // 该节点出度节点
        public List<Edge> edges;// 该节点出度边

        public Node(int value) {
            this.value = value;
            this.in = 0;
            this.out = 0;
            nextList = new ArrayList<>();
            edges = new ArrayList<>();
        }
    }
}
