package tree.exam;

/**
 * @Author ws
 * @Date 2021/4/13 14:36
 * @Version 1.0
 */
public class LowestCommonAncestorParents {
    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 给定一棵二叉树的头节点head，和另外两个节点a和b。
     * 返回a和b的最低公共祖先
     */

    public static class Info {
        Node ans; //最低公共祖先
        boolean findA;
        boolean findB;

        public Info(Node ans, boolean findA, boolean findB) {
            this.ans = ans;
            this.findA = findA;
            this.findB = findB;
        }
    }

    public static Node getLowestCommonAncestorParents(Node head, Node a, Node b) {
        if (head == null || a == null || b == null) {
            return null;
        }
        Info info = getLowestParents(head, a, b);
        return info.ans;
    }

    private static Info getLowestParents(Node head, Node a, Node b) {
        if (head == null) {
            return new Info(null, false, false);
        }
        Info leftInfo = getLowestParents(head.left, a, b);
        Info rightInfo = getLowestParents(head.right, a, b);
        boolean findA = head == a || leftInfo.findA || rightInfo.findA;
        boolean findB = head == b || leftInfo.findB || rightInfo.findB;
        Node ans = null;
        if (leftInfo.ans != null) {
            ans = leftInfo.ans;
        }
        if (rightInfo.ans != null) {
            ans = rightInfo.ans;
        }
        if (ans == null) {
            if (findA && findB) {
                ans = head;
            }
        }
        return new Info(ans, findA, findB);
    }

    public static void main(String[] args) {
        Node head= new Node(1);
        head.left=new Node(0);
        head.right=new Node(5);
        head.left.left=new Node(7);
        head.left.right=new Node(6);
        Node lowestCommonAncestorParents = getLowestCommonAncestorParents(head, head.left.left, head.left.right);
        System.out.println(lowestCommonAncestorParents.data);
    }
}
