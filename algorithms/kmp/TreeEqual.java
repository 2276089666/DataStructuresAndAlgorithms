package kmp;

/**
 * @Author ws
 * @Date 2021/5/5 14:15
 * @Version 1.0
 */
public class TreeEqual {
    /**
     * 给定两棵二叉树的头节点head1和head2
     * <p>
     * 想知道head1中是否有某个子树的结构和head2完全一样
     */

    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // 暴力递归
    public static boolean getTreeEqual(Node head1, Node head2) {
        // 任何树都包含空
        if (head2 == null) {
            return true;
        }
        // 空树一定不包含非空树head2
        if (head1 == null) {
            return false;
        }
        if (isSameValueStructure(head1, head2)) {
            return true;
        }
        // head1当前节点不与head2的节点匹配,跳到head1的下一个节点
        return getTreeEqual(head1.left, head2) || getTreeEqual(head1.right, head2);
    }

    // 判断两棵树是否完全相等
    private static boolean isSameValueStructure(Node head1, Node head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }


    /**
     * 利用kmp解,我们将两颗树都先序序列化,并使用kmp去做字符串的最快匹配
     */

    public static boolean getTreeEqual2(Node head1, Node head2) {
        // 任何树都包含空
        if (head2 == null) {
            return true;
        }
        // 空树一定不包含非空树head2
        if (head1 == null) {
            return false;
        }
        String path="";
        String head1String = preSerializeBinaryTree(head1, path);
        String head2String = preSerializeBinaryTree(head2, path);
        int index = KMP.getIndex(head1String, head2String);
        if (index==-1){
            return false;
        }else {
            return true;
        }
    }

    private static String preSerializeBinaryTree(Node head, String path) {
        if (head == null) {
            return "_#";
        }
        // 每个节点之间的分隔符,首节点我们也加分隔符,避免   4030   和  30    这种二叉树被序列化后,利用kmp字符串匹配,会得到错误结果
        //                                          /\        /\
        //                                         5  6      5  6
        String split = "_";
        path = split + head.value;
        return path+ preSerializeBinaryTree(head.left, path)+preSerializeBinaryTree(head.right, path);
    }

    public static void main(String[] args) {
        Node head1 = new Node(4030);
        head1.left = new Node(5);
        head1.left.left = new Node(3);
        head1.left.right = new Node(2);
        head1.right = new Node(7);
        head1.right.right = new Node(8);

        Node head2 = new Node(30);
        head2.left = new Node(5);
        head2.left = new Node(3);

        boolean treeEqual = getTreeEqual(head1, head2);
        System.out.println(treeEqual);

        String serializeBinaryTree = preSerializeBinaryTree(head1, "");
        System.out.println(serializeBinaryTree);
        boolean treeEqual2 = getTreeEqual2(head1, head2);
        System.out.println(treeEqual2);
    }
}
