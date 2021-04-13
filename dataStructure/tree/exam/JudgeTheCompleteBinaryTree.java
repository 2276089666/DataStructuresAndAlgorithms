package tree.exam;

/**
 * @Author ws
 * @Date 2021/4/13 14:11
 * @Version 1.0
 */
public class JudgeTheCompleteBinaryTree {
    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 给定一棵二叉树的头节点head，返回这颗二叉树中是不是完全二叉树
     */
    public static class Info {
        public boolean isFull;
        public boolean isCBT;
        public int height;

        public Info(boolean full, boolean cbt, int h) {
            isFull = full;
            isCBT = cbt;
            height = h;
        }
    }

    public static boolean isCompleteBinaryTree(Node head) {
        if (head == null) {
            return true;
        }
        Info info = isComplete(head);
        return info.isCBT;
    }

    private static Info isComplete(Node head) {
        if (head == null) {
            return new Info(true, true, 0);
        }
        Info leftInfo = isComplete(head.left);
        Info rightInfo = isComplete(head.right);
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isCBT = false;
        if (isFull) {
            isCBT = true;
        } else {
            // 左边不满,右边空
            if (leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
                isCBT = true;
            } else if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) { // 左边满,右边空
                isCBT = true;
            } else if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height) { // 左边满,右边不满
                isCBT = true;
            }
        }
        return new Info(isFull, isCBT, height);
    }

    public static void main(String[] args) {
        Node node = new Node(5);
        node.left=new Node(4);
        node.right=new Node(4);
        node.right.left=new Node(8);
        boolean completeBinaryTree = isCompleteBinaryTree(node);
        System.out.println(completeBinaryTree);
    }
}
