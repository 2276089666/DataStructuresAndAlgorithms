package interview;

/**
 * @Author ws
 * @Date 2021/5/27 16:29
 */
public class 二叉树变双向链表 {
    /**
     * 双向链表节点结构和二叉树节点结构是一样的，如果你把last认为是left，next认为是right的话。
     * 给定一个搜索二叉树的头节点head，请转化成一条有序的双向链表，并返回链表的头节点。
     */

    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    // 二叉树的递归套路，返回链表的头尾指针
    public static class Info {
        Node last;
        Node next;

        public Info(Node last, Node next) {
            this.last = last;
            this.next = next;
        }
    }

    public static Node treeToLinkedList(Node head) {
        if (head == null) {
            return null;
        }
        Info info = process(head);
        return info.last;
    }

    private static Info process(Node head) {
        if (head == null) {
            return new Info(null, null);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        if (leftInfo.next != null) {
            leftInfo.next.right = head;
        }
        if (rightInfo.last != null) {
            rightInfo.last.left = head;
        }
        head.left = leftInfo.next;
        head.right = rightInfo.last;
        return new Info(leftInfo.last != null ? leftInfo.last : head, rightInfo.next != null ? rightInfo.next : head);
    }


    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(4);
        head.right = new Node(3);
        Node first = treeToLinkedList(head);
    }
}
