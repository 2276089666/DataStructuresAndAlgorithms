package linkedList.exam;

import java.util.Stack;

/**
 * @Author ws
 * @Date 2021/4/8 13:46
 * @Version 1.0
 */
public class PalindromeLinkedList {
    // 单链节点
    public static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 给定一个单链表的头节点head，请判断该链表是否为回文结构。
     * 12321为回文
     */

    // 使用堆栈思路
    public static boolean isPalindrome1(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Stack<Node> nodes = new Stack<>();
        Node current = head;
        while (current != null) {
            nodes.push(current);
            current = current.next;
        }
        while (head != null) {
            if (nodes.pop().data != head.data) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // 使用快慢指针,将后一半的节点压栈再比较
    public static boolean isPalindrome2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Stack<Node> nodes = new Stack<>();
        Node current = slow;
        while (current != null) {
            nodes.push(current);
            current = current.next;
        }
        while (!nodes.isEmpty() && head != slow) {
            if (head.data != nodes.pop().data) {
                return false;
            }
            head = head.next;
        }
        return true;
    }


}
