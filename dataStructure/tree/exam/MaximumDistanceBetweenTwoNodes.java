package tree.exam;

/**
 * @Author ws
 * @Date 2021/4/11 12:59
 * @Version 1.0
 */
public class MaximumDistanceBetweenTwoNodes {
    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 给定一棵二叉树的头节点head，任何两个节点之间都存在距离，
     * 返回整棵二叉树的最大距离
     */

    public static class Info {
        int length;
        int maxDistance;

        public Info(int length, int maxDistance) {
            this.length = length;
            this.maxDistance = maxDistance;
        }
    }

    public static int getMaxDistance(Node head) {
        Info info = getDistance(head);
        return info.maxDistance;
    }

    private static Info getDistance(Node head) {
        if (head == null) {
            return new Info(0, 0);
        }
        Info leftInfo = getDistance(head.left);
        Info rightInfo = getDistance(head.right);
        int length = Math.max(leftInfo.length, rightInfo.length) + 1;
        int maxDistance = Math.max(leftInfo.length + rightInfo.length + 1, Math.max(leftInfo.maxDistance, rightInfo.maxDistance));
        return new Info(length,maxDistance);
    }


}
