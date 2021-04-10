package tree.exam;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author ws
 * @Date 2021/4/10 15:14
 * @Version 1.0
 */
public class TreeMaxWidth {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 求二叉树最宽的层有多少个节点
     */

    // 使用map记录每个节点所在的层数,当下一层节点被弹出时,结算上一层的节点数量
    public static int maxWidthUseMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        HashMap<Node, Integer> map = new HashMap<>();
        int level = 1;
        int currentLevelNodesNum = 0;
        int max = 0;
        queue.add(head);
        map.put(head, level);
        while (!queue.isEmpty()) {
            head = queue.poll();
            int currentLevel = map.get(head);
            if (head.left != null) {
                queue.add(head.left);
                map.put(head.left, currentLevel + 1);
            }
            if (head.right != null) {
                queue.add(head.right);
                map.put(head.right, currentLevel + 1);
            }
            if (currentLevel == level) {
                currentLevelNodesNum++;
            } else {
                max = Math.max(max, currentLevelNodesNum);
                level++;
                currentLevelNodesNum = 1;
            }
        }
        max = Math.max(max, currentLevelNodesNum);
        return max;
    }

    // 不断更新下一层的最右节点,判断节点是否是某层的最右节点,如果是就结算
    public static int maxWidthNoMap(Node head){
        if (head==null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        // 当前层的最右节点
        Node currentLevelLastNode=head;
        // 下一层的最右节点
        Node nextLevelLastNode=null;
        int max=0;
        int nodesNum=0;
        while (!queue.isEmpty()){
            Node cur=queue.poll();
            if (currentLevelLastNode.left!=null){
                queue.add(currentLevelLastNode.left);
                // 不断更新下一层的最右节点
                nextLevelLastNode=currentLevelLastNode.left;
            }
            if (currentLevelLastNode.right!=null){
                queue.add(currentLevelLastNode.right);
                // 不断更新下一层的最右节点
                nextLevelLastNode=currentLevelLastNode.right;
            }
            nodesNum++;
            // 本层已经遍历完,跳到下一层
            if (cur==currentLevelLastNode){
                max=Math.max(max,nodesNum);
                // 最右节点来到下一层
                currentLevelLastNode=nextLevelLastNode;
                nodesNum=0;
            }
        }
        return max;
    }
}
