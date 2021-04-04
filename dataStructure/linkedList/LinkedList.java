package linkedList;

/**
 * @Author ws
 * @Date 2021/3/20 10:02
 * @Version 1.0
 */
public class LinkedList {

    // 单链节点
    public static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    // 单链表
    public static class SingleLinkedList<T> {
        private Integer length;
        private Node<T> head;

        public SingleLinkedList() {
            length = 0;
            head = null;
        }

        /**
         * 倒插法
         * 依次插入 1 2 3 4
         * null <- 1 <- 2 <- 3 <- 4
         * 4为head
         *
         * @param data
         * @param <T>
         * @return
         */
        public <T> void addHead(T data) {
            Node newNode = new Node(data);
            if (length == 0) {
                head = newNode;
            } else {
                // 新节点的next指向前一个
                newNode.next = (Node<T>) head;
                // 移动头节点到最新节点,保证下次插入的节点能插到链表的末尾
                head = newNode;
            }
            length++;
        }


        /**
         * 删除操作,有可能删除头部,所以我们得返回值还为Node
         *
         * @param t   删除所有等于t得值
         * @param <T>
         * @return
         */
        public <T> Node<T> delete(Node<T> head, T t) {
            // 如果删除得正好是和头节点相等得值,但是   null <- 3 <- 1 <- 2 <- 3 <- 3 <- 3 删除3,我们直接将头节点移到2得位置,已经删除了一部分
            while (head != null) {
                if (!head.data.equals(t)) {
                    break;
                }
                head = head.next;
                length--;
            }

            Node<T> current = head;
            Node<T> pre = head;
            // 遍历删除
            while (current != null) {
                if (current.data.equals(t)) {
                    pre.next = current.next;
                    length--;
                } else {
                    pre = current;
                }
                current = current.next;
            }
            return head;
        }

        /**
         * 的节点
         *
         * @param t
         * @param <T>
         * @return
         */
        public <T> Node<T> find(T t) {
            Node current = head;
            while (current != null) {
                if (current.data.equals(t)) {
                    return current;
                } else {
                    current = current.next;
                }
            }
            return null;
        }

        /**
         * 遍历显示所有节点的值
         */
        public void display() {
            if (head == null) {
                return;
            }
            Node current = head;
            // TODO: 2021/3/20 疑问:这里到底是深拷贝还是浅拷贝?
            Integer tempSize = length;
            while (tempSize > 0) {
                System.out.print(current.data + " -> ");
                if (tempSize == 1) {
                    System.out.print("null");
                    break;
                }
                current = current.next;
                tempSize--;
            }
        }

        public <T> void reverseLinkedList(Node<T> head) {
            Node next = null;
            Node pre = null;
            while (head != null) {
                next = head.next;

                head.next = pre;
                // 当为双向链表时,加上这个
                // head.pre=next
                pre = head;

                head = next;
            }
            this.head = pre;
        }

    }


    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addHead(1);
        singleLinkedList.addHead(2);
        singleLinkedList.addHead(3);
        singleLinkedList.addHead(3);
        singleLinkedList.addHead(2);
        // 反转链表
        singleLinkedList.reverseLinkedList(singleLinkedList.head);
        singleLinkedList.head = singleLinkedList.delete(singleLinkedList.head, 1);
        singleLinkedList.head = singleLinkedList.delete(singleLinkedList.head, 2);
        Node node1 = singleLinkedList.find(3);
        if (node1 != null) {
            System.out.println(node1.data);
        }
        singleLinkedList.display();
        System.out.println();
        System.out.println(singleLinkedList.length);
    }
}
