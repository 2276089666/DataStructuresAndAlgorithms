package tree.exam;

/**
 * @Author ws
 * @Date 2021/4/10 19:29
 * @Version 1.0
 */
public class SuccessorNode {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 给你二叉树中的某个节点，返回该节点的后继节点 (中序遍历的情况下)
     */
    public static Node getSuccessorNode(Node head) {
        if (head == null) {
            return null;
        }
        if (head.right != null) {
            // 有右子树,需要不断往下找其左子树
            return getLeftMost(head.right);
        } else { // 无右子树
            Node parent = head.parent;
            // 该节点是父节点的右子树,要一直向上找到某个子树为另外一个子树的左子树
            while (parent != null && parent.right == head) {
                head = parent;
                parent = head.parent;
            }
            return parent;
        }
    }

    private static Node getLeftMost(Node right) {
        while (right.left != null) {
            right = right.left;
        }
        return right;
    }


    public static void printTree(Node head){
        if (head==null){
            return;
        }
        printInOrder(head,0,"H",17);
        System.out.println();
    }

    //  右中左的顺序
    private static void printInOrder(Node head, int level, String to, int len) {
        if (head==null){
            return;
        }
        printInOrder(head.right,level+1,"v",len);

        // 打印
        String val=to+head.value+to;
        int valueLen=val.length();
        // 左边长度
        int leftLen=(len-valueLen)/2;
        // 右边长度
        int rightLen=len-leftLen-valueLen;
        // 每个节点该打印的
        val=getSpace(leftLen)+val+getSpace(rightLen);
        System.out.println(getSpace(level*len)+val);

        printInOrder(head.left,level+1,"^",len);
    }

    private static String getSpace(int len) {
        StringBuffer stringBuffer = new StringBuffer("");
        String space=" ";
        for (int i = 0; i < len; i++) {
            stringBuffer.append(space);
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        printTree(head);

        Node test = head.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
    }
}
