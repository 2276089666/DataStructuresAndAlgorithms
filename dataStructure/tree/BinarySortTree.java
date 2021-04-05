package tree;


import java.util.Map;
import java.util.TreeMap;

/**
 * @Author ws
 * @Date 2021/3/20 13:19
 * @Version 1.0
 */
public class BinarySortTree {
    private Node root;

    private class Node <T extends Comparable>{
        private T data;
        private Node left;
        private Node right;

        public Node(T data) {
            this.data = data;
        }
    }

    /**
     * 插入方法
     * @param key 树的节点的值
     * @param <T>
     */
    public <T extends Comparable> void  insertBST (T key){
        Node p=root;
        Node previous=null;
        while (p!=null){
            previous=p;
            //Comparable接口的compareTo方法
            if (p.data.compareTo(key)>0){
                p=p.left;
            }else if (p.data.compareTo(key)<0){
                p=p.right;
            }else return;
        }

        if (root==null){
            root= new Node<>(key);
        }else if (previous.data.compareTo(key)>0){
            previous.left=new Node(key);
        }else {
            previous.right=new Node(key);
        }
    }

    /**
     * 实现了Comparable的测试类
     */
    public static class People implements Comparable<People>{

        private int age;

        public People(int age) {
            this.age = age;
        }

        @Override
        public int compareTo(People people) {
           return  (this.age < people.age) ? -1 : ((this.age == people.age) ? 0 : 1);
        }

        @Override
        public String toString() {
            return "People{" +
                    "age=" + age +
                    '}';
        }
    }

    public static void main(String[] args) {
        BinarySortTree binarySortTree = new BinarySortTree();
        binarySortTree.insertBST(new People(5));
        binarySortTree.insertBST(new People(6));
        binarySortTree.insertBST(new People(7));
        binarySortTree.insertBST(new People(4));
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
        peopleStringTreeMap.put(people5,"55555555");
        People people4 = new People(4);
        peopleStringTreeMap.put(people4,"44444444");
        People people7 = new People(7);
        peopleStringTreeMap.put(people7,"77777777");
        People people0 = new People(0);
        peopleStringTreeMap.put(people0,"00000000");
        People people_1 = new People(-1);
        peopleStringTreeMap.put(people_1,"-1111111");

        People people1 = peopleStringTreeMap.firstKey();
        System.out.println("第一个:"+people1);

        People people2 = peopleStringTreeMap.lastKey();
        System.out.println("最后一个"+people2);

        People floorKey = peopleStringTreeMap.floorKey(new People(6));
        System.out.println("小于等于6的最近的一个key"+floorKey);

        People ceilingKey = peopleStringTreeMap.ceilingKey(new People(6));
        System.out.println("大于等于6的最近的一个key"+ceilingKey);

    }
}
