package tree.exam;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author ws
 * @Date 2021/4/10 16:20
 * @Version 1.0
 */
public class SerializationOfBinaryTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 二叉树的序列化和反序列化
     */

    // 先序序列化
    public static Queue<String> preSerial(Node head){
        Queue<String> queue = new LinkedList<>();
        res(queue,head);
        return queue;
    }

    private static void res(Queue<String> queue, Node head) {
        if (head==null){
            queue.add(null);
        }else {
            queue.add(String.valueOf(head.value));
            res(queue,head.left);
            res(queue,head.right);
        }
    }

    // 先序反序列化
    public static Node buildByPreQueue(Queue<String> queue){
        if (queue.isEmpty()){
            return null;
        }
        return preb(queue);
    }

    private static Node preb(Queue<String> queue) {
        String poll = queue.poll();
        if (poll==null){
            return null;
        }
        Node node = new Node(Integer.valueOf(poll));
        node.left=preb(queue);
        node.right=preb(queue);
        return node;
    }


}
