package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

/**
 * @Author ws
 * @Date 2021/3/20 13:19
 * @Version 1.0
 */
public class BinarySortTree {
    private Node root;

    private class Node<T extends Comparable> {
        private T data;
        private Node left;
        private Node right;

        public Node(T data) {
            this.data = data;
        }
    }

    /**
     * 插入方法
     *
     * @param key 树的节点的值
     * @param <T>
     */
    public <T extends Comparable> void insertBST(T key) {
        Node p = root;
        Node previous = null;
        while (p != null) {
            previous = p;
            //Comparable接口的compareTo方法
            if (p.data.compareTo(key) > 0) {
                p = p.left;
            } else if (p.data.compareTo(key) < 0) {
                p = p.right;
            } else return;
        }

        if (root == null) {
            root = new Node<>(key);
        } else if (previous.data.compareTo(key) > 0) {
            previous.left = new Node(key);
        } else {
            previous.right = new Node(key);
        }
    }

    /**
     * 实现了Comparable的测试类
     */
    public static class People implements Comparable<People> {

        private int age;

        public People(int age) {
            this.age = age;
        }

        @Override
        public int compareTo(People people) {
            return (this.age < people.age) ? -1 : ((this.age == people.age) ? 0 : 1);
        }

        @Override
        public String toString() {
            return "People{" +
                    "age=" + age +
                    '}';
        }
    }

    /**
     * 先序递归遍历
     *
     * @param head
     */
    public static void pre(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.data);
        pre(head.left);
        pre(head.right);
    }

    /**
     * 中序递归遍历
     *
     * @param head
     */
    public static void in(Node head) {
        if (head == null) {
            return;
        }
        in(head.left);
        System.out.println(head.data);
        in(head.right);
    }

    /**
     * 后序递归遍历
     *
     * @param head
     */
    public static void pos(Node head) {
        if (head == null) {
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.println(head.data);
    }

    /**
     * 非递归的先序遍历
     *
     * @param head
     */
    public static void preWithNoRecursion(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> nodes = new Stack<>();
        nodes.push(head);
        while (!nodes.isEmpty()) {
            head = nodes.pop();
            System.out.println(head.data);
            if (head.right != null) {
                nodes.push(head.right);
            }
            if (head.left != null) {
                nodes.push(head.left);
            }
        }
    }

    /**
     * 非递归的后序遍历
     *
     * @param head
     */
    public static void posWithNoRecursion(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> nodes = new Stack<>();
        Stack<Node> helpStack = new Stack<>();
        nodes.push(head);
        while (!nodes.isEmpty()) {
            head = nodes.pop();
            helpStack.push(head);
            if (head.left != null) {
                nodes.push(head.left);
            }
            if (head.right != null) {
                nodes.push(head.right);
            }
        }
        while (!helpStack.isEmpty()) {
            System.out.println(helpStack.pop().data);
        }
    }

    /**
     * 更牛的后序遍历
     *
     * @param head
     */
    public static void posWithNoRecursion2(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> nodes = new Stack<>();
        Node sign = null;
        nodes.push(head);
        while (!nodes.isEmpty()) {
            // 移动节点
            sign = nodes.peek();
            if (sign.left != null && head != sign.left && head != sign.right) {
                nodes.push(sign.left);
            } else if (sign.right != null && head != sign.right) {
                nodes.push(sign.right);
            } else {
                System.out.println(nodes.pop().data);
                head = sign;
            }
        }
    }

    /**
     * 非递归的中序遍历
     *
     * @param head
     */
    public static void inWithNoRecursion(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> nodes = new Stack<>();
        while (!nodes.isEmpty() || head != null) {
            if (head != null) {
                nodes.push(head);
                head = head.left;
            } else {
                head = nodes.pop();
                System.out.println(head.data);
                head = head.right;
            }
        }
    }

    // 二叉树的广度优先遍历,使用队列实现
    public static void breadthFirstTraversal(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(head);
        while (!nodes.isEmpty()) {
            Node poll = nodes.poll();
            System.out.println(poll.data);
            if (poll.left != null) {
                nodes.add(poll.left);
            }
            if (poll.right != null) {
                nodes.add(poll.right);
            }
        }
    }

    public static void main(String[] args) {
        BinarySortTree binarySortTree = new BinarySortTree();
        binarySortTree.insertBST(new People(5));
        binarySortTree.insertBST(new People(6));
        binarySortTree.insertBST(new People(7));
        binarySortTree.insertBST(new People(4));
        System.out.println("递归先序遍历");
        pre(binarySortTree.root);
        System.out.println("非递归先序遍历:");
        preWithNoRecursion(binarySortTree.root);
        System.out.println();
        System.out.println("递归中序遍历");
        in(binarySortTree.root);
        System.out.println("非递归中序遍历");
        inWithNoRecursion(binarySortTree.root);
        System.out.println();
        System.out.println("递归后序遍历");
        pos(binarySortTree.root);
        System.out.println("非递归后序遍历");
        posWithNoRecursion(binarySortTree.root);
        System.out.println();
        posWithNoRecursion2(binarySortTree.root);

        System.out.println("广度优先遍历二叉树");
        breadthFirstTraversal(binarySortTree.root);
        System.out.println();
//        binarySortTree.insertBST(new Integer(3));
//        binarySortTree.insertBST(new Integer(4));
//        binarySortTree.insertBST(new Integer(2));


        /**
         * comparator the comparator that will be used to order this map.
         * If {@code null}, the {@linkplain Comparable natural
         * ordering} of the keys will be used.
         */
        TreeMap<People, String> peopleStringTreeMap = new TreeMap<>();
        People people5 = new People(5);
        peopleStringTreeMap.put(people5, "55555555");
        People people4 = new People(4);
        peopleStringTreeMap.put(people4, "44444444");
        People people7 = new People(7);
        peopleStringTreeMap.put(people7, "77777777");
        People people0 = new People(0);
        peopleStringTreeMap.put(people0, "00000000");
        People people_1 = new People(-1);
        peopleStringTreeMap.put(people_1, "-1111111");

        People people1 = peopleStringTreeMap.firstKey();
        System.out.println("第一个:" + people1);

        People people2 = peopleStringTreeMap.lastKey();
        System.out.println("最后一个" + people2);

        People floorKey = peopleStringTreeMap.floorKey(new People(6));
        System.out.println("小于等于6的最近的一个key" + floorKey);

        People ceilingKey = peopleStringTreeMap.ceilingKey(new People(6));
        System.out.println("大于等于6的最近的一个key" + ceilingKey);

    }
}
