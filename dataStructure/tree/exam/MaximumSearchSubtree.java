package tree.exam;

/**
 * @Author ws
 * @Date 2021/4/11 13:33
 * @Version 1.0
 */
public class MaximumSearchSubtree {
    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 给定一棵二叉树的头节点head，
     * 返回这颗二叉树中最大的二叉搜索子树的大小
     */

    public static class Info {
        boolean isSearch;
        int num;
        int max; // 左树的最大值
        int min; // 右树的最小值

        public Info(boolean isSearch, int num, int max, int min) {
            this.isSearch = isSearch;
            this.num = num;
            this.max = max;
            this.min = min;
        }
    }

    public static int getSearchSubtreeNum(Node head) {
        Info info = getSearch(head);
        return info.num;
    }

    //二叉搜索子树 节点必须大于左侧子树的最大值,小于右侧子树的最小值
    private static Info getSearch(Node head) {
        if (head == null) {
            // 返回null时后面用的时候就得判断
            return null;
        }
        Info leftInfo = getSearch(head.left);
        Info rightInfo = getSearch(head.right);
        // 叶子节点时
        int max = head.data;
        int min = head.data;
        int num = 0;

        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            // 左侧子树有右孩子
            min = Math.min(leftInfo.min, min);
            num = Math.max(num, leftInfo.num);
        }

        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
            num = Math.max(num, rightInfo.num);
        }

        boolean isSearch = false;
        if ((leftInfo == null ? true : (leftInfo.isSearch && leftInfo.max < head.data))
                && (rightInfo == null ? true : (rightInfo.isSearch && rightInfo.min > head.data))) {
            isSearch = true;
            num = (leftInfo == null ? 0 : leftInfo.num)
                    + (rightInfo == null ? 0 : rightInfo.num) + 1;
        }
        return new Info(isSearch, num, max, min);
    }



    public static void main(String[] args) {

        Node node = new Node(3);
        node.left = new Node(4);
        node.left.left = new Node(3);
        node.left.right = new Node(7);
        node.right = new Node(5);
        int i = getSearchSubtreeNum(node);
        System.out.println(i);
    }
}
