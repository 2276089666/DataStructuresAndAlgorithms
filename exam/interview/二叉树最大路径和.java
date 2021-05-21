package interview;

/**
 * @Author ws
 * @Date 2021/5/20 9:40
 */
public class 二叉树最大路径和 {
    /**
     * 给定一个二叉树的头节点head，路径的规定有以下三种不同的规定：
     * <p>
     * 1）路径必须是头节点出发，到叶节点为止，返回最大路径和
     * <p>
     * 2）路径可以从任何节点出发，但必须往下走到达任何节点，返回最大路径和
     */

    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    static int MAX_SUM;

    // 规定1
    public static int getMaxPath(Node head) {
        if (head == null) {
            return 0;
        }
        MAX_SUM = Integer.MIN_VALUE;
        max(head, 0);
        return MAX_SUM;
    }

    private static void max(Node node, int preSum) {
        if (node.left == null && node.right == null) {
            MAX_SUM = Math.max(MAX_SUM, preSum + node.data);
        }
        if (node.left != null) {
            max(node.left, preSum + node.data);
        }
        if (node.right != null) {
            max(node.right, preSum + node.data);
        }
    }

    // 规定2
    public static int getMaxSum2(Node head){
        if (head==null){
            return 0;
        }
        MAX_SUM=Integer.MIN_VALUE;
        max2(head,0);
        return MAX_SUM;
    }

    private static int max2(Node node, int preSum) {
        int nextMax=0;
        if (node.left != null) {
            nextMax=max2(node.left,preSum);
        }

        if (node.right != null) {
            nextMax=Math.max(max2(node.right,preSum),nextMax);
        }
        int res=node.data+nextMax;
        MAX_SUM = Math.max(MAX_SUM, res);
        return res;
    }


    public static void main(String[] args) {
        Node head = new Node(-1);
        head.left = new Node(3);
        head.left.left = new Node(4);
        head.left.left.right=new Node(-1);
        head.left.right = new Node(-7);
        head.right = new Node(3);
        int maxPath = getMaxPath(head);
        System.out.println(maxPath);
        int maxSum2 = getMaxSum2(head);
        System.out.println(maxSum2);
    }
}
