package tree.exam;

/**
 * @Author ws
 * @Date 2021/4/13 13:22
 * @Version 1.0
 */
public class FullBinaryTreeJudgment {
    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 给定一棵二叉树的头节点head，返回这颗二叉树是不是满二叉树
     */

    public static class Info {
        int length;
        int nodesNum;

        public Info(int length, int nodesNum) {
            this.length = length;
            this.nodesNum = nodesNum;
        }
    }

    public static boolean isFullTree(Node head) {
        if (head == null) {
            return true;
        }
        Info info = isFull(head);
        return (1 << info.length) - 1 == info.nodesNum;
    }

    private static Info isFull(Node head) {
        if (head == null) {
            return new Info(0, 0);
        }
        Info leftInfo = isFull(head.left);
        Info rightInfo = isFull(head.right);
        int length = Math.max(leftInfo.length, rightInfo.length) + 1;
        int nodesNum = leftInfo.nodesNum + rightInfo.nodesNum + 1;
        return new Info(length, nodesNum);
    }


    public static void main(String[] args) {
        Node node = new Node(1);
        node.left = new Node(2);
        node.right = new Node(3);

        boolean fullTree = isFullTree(node);
        System.out.println(fullTree);

        Node node2 = new Node(1);
        node2.left = new Node(2);
        boolean fullTree1 = isFullTree(node2);
        System.out.println(fullTree1);
    }
}
