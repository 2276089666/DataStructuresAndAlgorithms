package tree;


/**
 * @Author ws
 * @Date 2021/3/20 13:19
 * @Version 1.0
 */
public class BinarySortTree {
    private Node root;

    // <? super T>实现了Comparable接口的子类也可以
    private class Node <T extends Comparable<? super T>>{
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
    public <T extends Comparable<? super T>> void  insertBST (T key){
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
    }
}
