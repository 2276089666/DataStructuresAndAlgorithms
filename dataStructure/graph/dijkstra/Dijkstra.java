package graph.dijkstra;

import graph.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @Author ws
 * @Date 2021/4/17 11:52
 * @Version 1.0
 */
public class Dijkstra {
    /**
     * Dijkstra算法（贪心策略）
     *
     * 1）Dijkstra算法必须指定一个源点
     * 2）生成一个源点到各个点的最小距离表，一开始只有一条记录，即原点到自己的最小距离为0，源点到其他所有点的最小距离都为正无穷大
     * 3）从距离表中拿出没拿过记录里的最小记录，通过这个点发出的边，更新源点到各个点的最小距离表，不断重复这一步
     * 4）源点到所有的点记录如果都被拿过一遍，过程停止，最小距离表得到了
     */

    public static Map<Graph.Node,Integer> dijkstra1(Graph.Node from){
        if (from==null){
            return null;
        }
        HashMap<Graph.Node, Integer> map = new HashMap<>();
        map.put(from,0);
        HashSet<Graph.Node> set = new HashSet<>();   // 已经选择过的node
        Graph.Node minNode=getMinDistanceAndUnselectedNode(map,set);
        while (minNode!=null){
            Integer distance = map.get(minNode);
            for (Graph.Edge edge : minNode.edges) {
                Graph.Node toNode = edge.to;
                if (!set.contains(toNode)){
                    map.put(toNode,distance+edge.weight);
                }else {
                    map.put(toNode,Math.min(map.get(toNode),distance+edge.weight));
                }
            }
            set.add(minNode);
            minNode=getMinDistanceAndUnselectedNode(map,set);
        }
        return map;
    }

    // 找到没有被选择过的最小权重的边的下一个节点
    private static Graph.Node getMinDistanceAndUnselectedNode(HashMap<Graph.Node, Integer> map, HashSet<Graph.Node> set) {
        Graph.Node minNode=null;
        int maxValue=Integer.MAX_VALUE;
        for (Map.Entry<Graph.Node, Integer> entry : map.entrySet()) {
            Graph.Node node = entry.getKey();
            Integer distance = entry.getValue();
            if (!set.contains(node)&&distance<maxValue){
                minNode=node;
                maxValue=distance;
            }
        }
        return minNode;
    }


    /**
     * 将遍历map的操作我们改进为用小根堆
     */
    public static class NodeRecord {
        public Graph.Node node;
        public int distance;

        public NodeRecord(Graph.Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap {
        private Graph.Node[] nodes; // 实际的堆结构
        // key 某一个node， value 上面数组中的位置
        private HashMap<Graph.Node, Integer> heapIndexMap;
        // key 某一个节点， value 从源节点出发到该节点的目前最小距离
        private HashMap<Graph.Node, Integer> distanceMap;
        private int size; // 堆上有多少个点

        public NodeHeap(int size) {
            nodes = new Graph.Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // 有一个点叫node，现在发现了一个从源节点出发到达node的距离为distance
        // 判断要不要更新，如果需要的话，就更新
        public void addOrUpdateOrIgnore(Graph.Node node, int distance) {
            if (inHeap(node)) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                insertHeapify(node, heapIndexMap.get(node));
            }
            if (!isEntered(node)) {
                nodes[size] = node;
                heapIndexMap.put(node, size);
                distanceMap.put(node, distance);
                insertHeapify(node, size++);
            }
        }

        public NodeRecord pop() {
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);
            heapIndexMap.put(nodes[size - 1], -1);
            distanceMap.remove(nodes[size - 1]);
            // free C++同学还要把原本堆顶节点析构，对java同学不必
            nodes[size - 1] = null;
            heapify(0, --size);
            return nodeRecord;
        }

        private void insertHeapify(Graph.Node node, int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left])
                        ? left + 1
                        : left;
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
                if (smallest == index) {
                    break;
                }
                swap(smallest, index);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        private boolean isEntered(Graph.Node node) {
            return heapIndexMap.containsKey(node);
        }

        private boolean inHeap(Graph.Node node) {
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }

        private void swap(int index1, int index2) {
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            Graph.Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }
    }

    // 改进后的dijkstra算法
    // 从head出发，所有head能到达的节点，生成到达每个节点的最小路径记录并返回
    public static HashMap<Graph.Node, Integer> dijkstra2(Graph.Node head, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        HashMap<Graph.Node, Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            NodeRecord record = nodeHeap.pop();
            Graph.Node cur = record.node;
            int distance = record.distance;
            for (Graph.Edge edge : cur.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            result.put(cur, distance);
        }
        return result;
    }
}
