package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @Author ws
 * @Date 2021/4/15 19:10
 * @Version 1.0
 */
public class UnionFind {

    /**
     * 并查集
     * 1.有若干个样本a、b、c、d…类型假设是V
     * 2.在并查集中一开始认为每个样本都在单独的集合里
     * 3.用户可以在任何时候调用如下两个方法：
     *      boolean isSameSet(V x, V y) : 查询样本x和样本y是否属于一个集合
     *      void union(V x, V y) : 把x和y各自所在集合的所有样本合并成一个集合
     * 4.isSameSet和union方法的代价越低越好
     */

    /**
     * 1）节点往上找代表点的过程，把沿途的链变成扁平的
     * <p>
     * 2）小集合挂在大集合的下面
     * <p>
     * 3）如果方法调用很频繁，那么单次调用的代价为O(1)，两个方法都如此
     */

    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }

    public static class UnionSet<V> {
        public HashMap<V, Node<V>> nodes = new HashMap<>();
        public HashMap<Node<V>, Node<V>> parents = new HashMap<>();
        public HashMap<Node<V>, Integer> sizeMap = new HashMap<>();

        public UnionSet(List<V> values) {
            for (V value : values) {
                Node<V> node = new Node<>(value);
                nodes.put(value, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> path = new Stack<>();
            while (cur != parents.get(cur)) {
                path.push(cur);
                cur = parents.get(cur);
            }
            // cur头节点
            while (!path.isEmpty()) {
                parents.put(path.pop(), cur);
            }
            return cur;
        }

        public boolean isSameSet(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return false;
            }
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        public void union(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return;
            }
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                if (aSetSize >= bSetSize) {
                    parents.put(bHead, aHead);
                    sizeMap.put(aHead, aSetSize + bSetSize);
                    sizeMap.remove(bHead);
                } else {
                    parents.put(aHead, bHead);
                    sizeMap.put(bHead, aSetSize + bSetSize);
                    sizeMap.remove(aHead);
                }
            }
        }
        public int getSize(){
            return sizeMap.size();
        }
    }

    public static class User {
        public String a;
        public String b;
        public String c;

        public User(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    // 如果两个User的a字段一样，或者b字段一样，或者c字段一样，就认为是同一个user
    // 请合并Users，并返回用户的数量
    public static int mergerUsers(List<User> users) {
        UnionSet<User> userUnionSet = new UnionSet<>(users);
        HashMap<String, User> AMap = new HashMap<>();
        HashMap<String, User> BMap = new HashMap<>();
        HashMap<String, User> CMap = new HashMap<>();
        for (User user : users) {
            if (AMap.containsKey(user.a)) {
                userUnionSet.union(user, AMap.get(user.a));
            } else {
                AMap.put(user.a, user);
            }
            if (BMap.containsKey(user.b)) {
                userUnionSet.union(user, BMap.get(user.b));
            } else {
                BMap.put(user.b, user);
            }
            if (CMap.containsKey(user.c)) {
                userUnionSet.union(user, CMap.get(user.c));
            } else {
                CMap.put(user.c, user);
            }
        }
        return userUnionSet.getSize();
    }

    public static void main(String[] args) {
        User user = new User("1", "10", "13");
        User user1 = new User("2", "10", "37");
        User user2 = new User("400", "500", "37");
        User user3 = new User("22", "w2", "546");
        ArrayList<User> list = new ArrayList<>();
        list.add(user);
        list.add(user1);
        list.add(user2);
        list.add(user3);

        int i = mergerUsers(list);
        System.out.println("总用户："+i);
    }
}
