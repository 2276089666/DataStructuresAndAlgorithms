package linkedList.exam;

/**
 * @Author ws
 * @Date 2021/4/8 14:32
 * @Version 1.0
 */
public class LinkedListPartition {
    // 单链节点
    public static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 将单向链表按某值划分成左边小、中间相等、右边大的形式
     */

    // 将链表的数据拷贝到数组中,然后进行荷兰国旗partition操作
    public static Node listPartition1(Node head, int data) {
        if (head == null) {
            return null;
        }
        int count = 0;
        Node current = head;
        // 看链表有多少个节点我们创建多大的数组
        while (current != null) {
            count++;
            current = current.next;
        }
        Node[] nodes = new Node[count];
        current = head;
        // 拷贝节点
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = current;
            current = current.next;
        }
        partition(nodes, data);
        int i;
        // 将数组中的节点连起来
        for (i = 1; i != nodes.length; i++) {
            nodes[i - 1].next = nodes[i];
        }
        nodes[i - 1].next = null;
        return nodes[0];
    }

    private static void partition(Node[] nodes, int data) {
        if (nodes.length == 1) {
            return;
        }
        int less = -1;
        int more = nodes.length;
        int current = 0;
        while (current < more) {
            if (nodes[current].data < data) {
                swap(nodes, current++, ++less);
            } else if (nodes[current].data > data) {
                swap(nodes, current, --more);
            } else {
                current++;
            }
        }
    }

    public static void swap(Node[] nodeArr, int a, int b) {
        Node tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }


    // 每个区间的起始我们都分配一个指针
    public static Node listPartition2(Node head, int data) {
        Node sH = null; // small head
        Node sT = null; // small tail
        Node eH = null; // equal head
        Node eT = null; // equal tail
        Node mH = null; // big head
        Node mT = null; // big tail
        while (head != null) {
            // 记录下一个指针,方便我们寻找
            Node next = head.next;
            head.next = null;
            if (head.data < data) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    // 将新的节点连接到之前的小于区的尾指针,并移动尾指针
                    sT.next = head;
                    sT = head;
                }
            } else if (head.data == data) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            } else {
                if (mH == null) {
                    mH = head;
                    mT = head;
                } else {
                    mT.next = head;
                    mT = head;
                }
            }
            head = next;
        }

        if (sT != null) {
            sT.next = eH;
            // 看等于区域是否为空
            eT = eT == null ? sT : eT;
        }
        if (eT != null) {
            // 连接大于区域
            eT.next = mH;
        }

        return sH != null ? sH : (eH != null ? eH : mH);
    }

}
