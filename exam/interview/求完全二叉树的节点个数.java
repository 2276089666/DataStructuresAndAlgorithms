package interview;

/**
 * @Author ws
 * @Date 2021/5/28 10:34
 */
public class 求完全二叉树的节点个数 {
    /**
     * 求完全二叉树节点的个数
     * 要求时间复杂度低于O(N)
     */


    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 请保证head为头的树，是完全二叉树
    public static int nodeNum(Node head) {
        if (head == null) {
            return -1;
        }
        return bs(head, 1, mostLeftLevel(head, 1));
    }

    // node在第level层，h是总的深度（h永远不变，全局变量
    // 以node为头的完全二叉树，节点个数是多少
    public static int bs(Node node, int Level, int h) {
        if (Level == h) {
            return 1;
        }
        if (mostLeftLevel(node.right, Level + 1) == h) {
            // 以node为头的子树，子树的左子树节点个数确定，为2^（h-level）
            return (1 << (h - Level)) + bs(node.right, Level + 1, h);
        } else {
            // 以node为头的子树，子树的右子树节点个数确定，为2^（h-level-1）
            return (1 << (h - Level - 1)) + bs(node.left, Level + 1, h);
        }
    }

    // 如果node在第level层，
    // 求以node为头的子树，最大深度是多少
    // node为头的子树，一定是完全二叉树
    public static int mostLeftLevel(Node node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        System.out.println(nodeNum(head));

    }
}
