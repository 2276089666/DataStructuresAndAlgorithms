package stackAndQueue.common;

/**
 * @Author ws
 * @Date 2021/4/4 18:35
 * @Version 1.0
 */
public class DoubleEndsQueue<T> {

    public class Node<T> {
        public T value;
        public Node<T> last;
        public Node<T> next;

        public Node(T data) {
            value = data;
        }
    }

    public Node<T> head;
    public Node<T> tail;

    // 从头部添加
    public void addFromHead(T value) {
        Node<T> current = new Node<T>(value);
        if (head == null) {
            head = current;
            tail = current;
        } else {
            //                 tail                            head                   current
            //  null  <--next-- (Node) --last--> <--next-- (Node) --last-->  <--next-- current
            // 连节点得后指针
            current.next = head;
            // 连头节点得前指针
            head.last = current;
            // 移动头
            head = current;
        }
    }

    // 从尾部添加
    public void addFromBottom(T value){
        Node<T> current = new Node<>(value);
        if (head==null){
            head=current;
            tail=current;
        }else {
            //    current                     tail                         head
            // current --last-->   <--next-- (Node) --last--> <--next-- (Node) --last--> null
            current.last=tail;
            tail.next=current;
            tail=current;
        }
    }

    // 从头弹出
    public T popFromHead(){
        if (head==null){
            return null;
        }
        Node<T> current=head;
        if (head==tail){
            head=null;
            tail=null;
        }else {
            head=head.next;
            // 之前头节点的尾指针置空,让gc回收
            current.next=null;
            head.last=null;
        }
        return current.value;
    }

    // 从尾弹出
    public T popFromBottom(){
        if (head==null){
            return null;
        }
        Node<T> current=tail;
        if (head==tail){
            head=null;
            tail=null;
        }else {
            tail=tail.last;
            current.last=null;
            tail.next=null;
        }
        return current.value;
    }

    public boolean isEmpty() {
        return head == null;
    }
}
