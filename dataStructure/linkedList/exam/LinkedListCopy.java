package linkedList.exam;

import java.util.HashMap;

/**
 * @Author ws
 * @Date 2021/4/8 20:36
 * @Version 1.0
 */
public class LinkedListCopy {
    public static class Node {
        Node next;
        Node rand;
        int data;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 一种特殊的单链表节点类描述如下
     * class Node {
     * int value;
     * Node next;
     * Node rand;
     * Node(int val) { value = val; }
     * }
     * rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null。
     * 给定一个由Node节点类型组成的无环单链表的头节点 head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点。
     * 【要求】
     * 时间复杂度O(N)，额外空间复杂度O(1)
     */

    // 使用哈希表实现
    public Node CopyLinkedList(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node current = head;
        // 拷贝一份原样数据到map中
        while (current != null) {
            map.put(current, new Node(current.data));
            current = current.next;
        }

        current = head;
        while (current != null) {
            // current 老
            // map.get(current) 新
            Node newCurrent = map.get(current);
            Node oldNext = current.next;
            Node newNext = map.get(oldNext);
            newCurrent.next = newNext;

            Node oldRand = current.rand;
            Node newRand = map.get(oldRand);
            newCurrent.rand = newRand;

            current = current.next;
        }
        return map.get(head);
    }

    // 在之前的链表的两个节点之间插入我们新复制的节点,然后将新老链表分离,返回我们新链表的头指针
    public Node CopyLinkedList2(Node head) {
        if (head == null) {
            return null;
        }
        Node current = head;
        while (current != null) {
            Node newNode = new Node(current.data);
            // 之前的节点
            Node next = current.next;

            // 连接节点
            current.next = newNode;
            newNode.next = next;

            // 移动节点
            current = next;
        }
        // 设置rand指针
        current = head;
        while (current != null) {
            Node next = current.next.next;

            Node copyNode = current.next;
            copyNode.rand = current.rand != null ? current.rand.next : null;

            current = next;
        }
        // 分离
        Node res = head.next;
        current = head;
        // split
        while (current != null) {
            Node next = current.next.next;
            Node curCopy = current.next;
            current.next = next;
            curCopy.next = next != null ? next.next : null;
            current = next;
        }
        return res;
    }
}
