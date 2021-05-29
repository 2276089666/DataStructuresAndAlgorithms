package interview;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author ws
 * @Date 2021/5/28 16:29
 */
public class LRU最不经常使用内存替换算法 {
    /**
     * LRU  get和set时间复杂度都是O(1)
     */

    public static class Node<K, V> {
        K key;
        V value;
        Node<K, V> last;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static class LinkedList<K, V> {
        Node<K, V> head;
        Node<K, V> tail;

        public LinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insert(Node<K, V> node) {
            if (node == null) {
                return;
            }
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.last = tail;
                tail = node;
            }
        }

        public void moveNodeToTail(Node<K, V> node) {
            if (node == null) {
                return;
            }
            if (node == head) {
                head = head.next;
                head.last = null;
            } else {
                node.next.last = node.last;
                node.last.next = node.next;
            }
            tail.next = node;
            node.last = tail;
            node.next = null;
            tail = node;
        }

        // 带返回值，方便我们删除map里面记录的节点
        public Node<K, V> deleteHead() {
            if (head == null) {
                return null;
            }
            Node<K, V> res = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.last = null;
                res.next = null;
            }
            return res;
        }
    }

    public static class LRU<K, V> {
       private Map<K, Node<K, V>> map;
       private LinkedList<K, V> list;
       private final int size;

        public LRU(int size) {
            if (size<1){
                throw new RuntimeException("should be more than 0.");
            }
            this.size = size;
            map=new HashMap<>();
            list=new LinkedList<>();
        }

        public void set(K key,V value){
            if (map.containsKey(key)){
                Node<K, V> node = map.get(key);
                list.moveNodeToTail(node);
                node.value=value;
            }else {
                Node<K, V> node = new Node<>(key, value);
                if (map.size()==size){
                    Node<K, V> deleteHead = list.deleteHead();
                    map.remove(deleteHead.key);
                }
                map.put(key,node);
                list.insert(node);
            }
        }

        public V get(K key) {
            if (map.containsKey(key)) {
                Node<K, V> res = map.get(key);
                list.moveNodeToTail(res);
                return res.value;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        LRU<Integer, String> lru = new LRU<>(3);
        lru.set(1,"asd");
        lru.set(2,"asda");
        lru.set(4,"as");
        lru.set(5,"asd");
        System.out.println(lru.list.head.value);
        String s = lru.get(2);
        System.out.println(lru.list.head.value);
        System.out.println(lru.list.tail.value);
    }
}
