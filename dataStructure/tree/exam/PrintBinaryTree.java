package tree.exam;

/**
 * @Author ws
 * @Date 2021/4/10 18:25
 * @Version 1.0
 */
public class PrintBinaryTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
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
        Node head = new Node(1);
        head.left = new Node(-222222222);
        head.right = new Node(3);
        head.left.left = new Node(Integer.MIN_VALUE);
        head.right.left = new Node(55555555);
        head.right.right = new Node(66);
        head.left.left.right = new Node(777);
        printTree(head);



        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.left = new Node(5);
        head.right.right = new Node(6);
        head.left.left.right = new Node(7);
        printTree(head);
    }
}
