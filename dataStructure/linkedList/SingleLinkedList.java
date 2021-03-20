package linkedList;

/**
 * @Author ws
 * @Date 2021/3/20 10:02
 * @Version 1.0
 */
public class SingleLinkedList {
    private Integer length;
    private Node head;

    public SingleLinkedList() {
        length = 0;
        head = null;
    }

    private class Node<T> {
        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
        }
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
    public <T> T addHead(T data) {
        Node newNode = new Node(data);
        if (length == 0) {
            head = newNode;
        } else {
            // 新节点的next指向前一个
            newNode.next = head;
            // 移动头节点到最新节点,保证下次插入的节点能插到链表的末尾
            head = newNode;
        }
        length++;
        return data;
    }


    /**
     * 删除操作
     *
     * @param t
     * @param <T>
     * @return
     */
    public <T> Boolean delete(T t) {
        if (length == 0) {
            return false;
        }
        Node current = head;
        // 之前的节点
        Node previous = head;
        while (current.data != t) {
            if (current.next == null) {
                return false;
            } else {
                // 记录当前的节点的前一个节点,删除的时候用
                previous = current;
                current = current.next;
            }
        }
        // 如果删除的是头节点,只需要移动头节点就行
        if (current == head) {
            head = current.next;
            length--;
        } else {
            previous.next = current.next;
            length--;
        }
        return true;
    }

    /**
     * 按照节点的值来查找我们链表中的节点
     * @param t
     * @param <T>
     * @return
     */
    public <T> Node find(T t) {
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

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        Integer integer = singleLinkedList.addHead(1);
        Integer integer1 = singleLinkedList.addHead(2);
        Integer integer2 = singleLinkedList.addHead(3);
        Boolean delete = singleLinkedList.delete("2");
        Node node = singleLinkedList.find(1);
        System.out.println(node.data);
        singleLinkedList.display();
        System.out.println();
        System.out.println(singleLinkedList.length);
    }
}
