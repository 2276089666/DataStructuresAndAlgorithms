package tree.exam;

/**
 * @Author ws
 * @Date 2021/4/10 22:21
 * @Version 1.0
 */
public class JudgingTheBalanceTree {
    /**
     * 树的递归套路
     * 1）假设以X节点为头，假设可以向X左树和X右树要任何信息
     * 2）在上一步的假设下，讨论以X为头节点的树，得到答案的可能性（最重要）
     * 3）列出所有可能性后，确定到底需要向左树和右树要什么样的信息          (info)
     * 4）把左树信息和右树信息求全集，就是任何一棵子树都需要返回的信息S
     * 5）递归函数都返回S，每一棵子树都这么要求
     * 6）写代码，在代码中考虑如何把左树的信息和右树信息整合出整棵树的信息
     */

    // 给定一棵二叉树的头节点head，返回这颗二叉树是不是平衡二叉树:1.左右子树深度相差小于2
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }


    public static class Info {
        int length;
        boolean balance;

        public Info(int length, boolean balance) {
            this.length = length;
            this.balance = balance;
        }
    }

    public static boolean isBalanceTree(Node head) {
        if (head == null) {
            return false;
        }
        Info info = process(head);
        return info.balance;
    }

    private static Info process(Node head) {
        if (head == null) {
            return new Info(0,true);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        // 当前树的高度等于最高子树加自己的高度1
        int length=Math.max(leftInfo.length,rightInfo.length)+1;
        boolean balance=true;
        // 任何条件触发就不平衡
        if (leftInfo.balance==false||rightInfo.balance==false||Math.abs(leftInfo.length-rightInfo.length)>1){
            balance=false;
        }
        return new Info(length,balance);
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(-222222222);
        head.right = new Node(3);
        head.left.left = new Node(Integer.MIN_VALUE);
        head.right.left = new Node(55555555);
        head.right.right = new Node(66);
        head.left.left.right = new Node(777);
        boolean balanceTree = isBalanceTree(head);
        System.out.println(balanceTree);
    }
}
