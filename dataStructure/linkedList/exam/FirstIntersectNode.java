package linkedList.exam;

/**
 * @Author ws
 * @Date 2021/4/9 14:55
 * @Version 1.0
 */
public class FirstIntersectNode {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 给定两个可能有环也可能无环的单链表，头节点head1和head2。请实现一个函数，如果两个链表相交，请返回相交的
     * 第一个节点。如果不相交，返回null
     * 【要求】
     * 如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度 请达到O(1)。
     */

    public static Node getIntersectNode(Node firstHead, Node secondNode) {
        if (firstHead == null || secondNode == null) {
            return null;
        }
        // 看链表是否有环,有就返回入环节点,无则返回空;
        Node loop1 = getLoopNode(firstHead);
        Node loop2 = getLoopNode(secondNode);
        // 都无环的情况下
        if (loop1 == null && loop2 == null) {
            return noLoop(firstHead, secondNode);
        }
        // 一个有环和一个无环必不相交

        // 两个都有环
        if (loop1 != null && loop2 != null) {
            return bothLoop(firstHead, loop1, secondNode, loop2);
        }
        return null;
    }

    /**
     * 两个链表都有环的境况下,如果入环节点相同我们还是让长链表先走两链表之差步,然后两链表同时走,第一个相等的节点就是
     *                       入环节点不同,我们随便遍历一个链表直到某个节点与另一个链表的入环节点相同,就是我们要找的节点
     * @param head1
     * @param loop1
     * @param head2
     * @param loop2
     * @return
     */
    private static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        // 入环节点相同,有两种结构   \/    \/
        //                       |     O
        //                       O
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            // 入环节点不同   \ /
            //               O
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    /**
     * 让短链表先走两链表之差的步数,然后再一起走,直到两链表的节点为同一个节点,就是第一个相交的节点
     * 由于链表只有一个next指针,相交必定是 \/  不可能是 |
     *                               |          /\
     * @param head1
     * @param head2
     * @return
     */
    private static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) {
            return null;
        }
        // n  :  链表1长度减去链表2长度的值
        cur1 = n > 0 ? head1 : head2; // 谁长，谁的头变成cur1
        cur2 = cur1 == head1 ? head2 : head1; // 谁短，谁的头变成cur2
        n = Math.abs(n);
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /**
     * 查看该链表是否有环 (使用快慢指针,先快慢指针同时走,直到第一次相遇的时候,快指针回到头节点和慢指针接着同时走,直到两指针相遇)
     * 也可以用set,先把一个链表存到set中,再遍历另一个链表,如果set中有与另一个链表相同的节点,那就我们要找的节点
     * @param head
     * @return
     */
    private static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        // n1 慢  n2 快
        Node n1 = head.next; // n1 -> slow
        Node n2 = head.next.next; // n2 -> fast
        while (n1 != n2) {
            if (n2.next == null || n2.next.next == null) {
                return null;
            }
            n2 = n2.next.next;
            n1 = n1.next;
        }
        n2 = head; // n2 -> walk again from head
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }
}
