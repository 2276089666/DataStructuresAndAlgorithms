package interview;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author ws
 * @Date 2021/6/4 14:23
 */
public class 两数组的TopK {
    /**
     * 给定两个有序数组arr1和arr2，再给定一个正数K
     * 求两个数累加和最大的前K个，两个数必须分别来自arr1和arr2
     */

    public static class Node {
        int index1; // arr1的下标
        int index2; // arr2的下标
        int sum; // arr1[index1]+arr2[index2]

        public Node(int index1, int index2, int sum) {
            this.index1 = index1;
            this.index2 = index2;
            this.sum = sum;
        }
    }

    public static class MyComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o2.sum-o1.sum;
        }
    }

    // 一个样本做行一个样本做列的模型
    public static int[] getTopK(int[] arr1, int[] arr2,int k) {
        if (arr1==null||arr1.length==0||arr2==null||arr2.length==0||k<1){
            return null;
        }
        PriorityQueue<Node> heap = new PriorityQueue<>(new MyComparator());

        int[] res = new int[k];
        int resIndex=0;
        int index1=arr1.length-1;
        int index2=arr2.length-1;
        boolean[][] set = new boolean[index1+1][index2+1];  // 去重
        Node maxNode = new Node(index1, index2, arr1[index1] + arr2[index2]);
        heap.add(maxNode);
        set[index1][index2]=true;
        while (resIndex!=k){
            Node node = heap.poll();
            index1=node.index1;
            index2=node.index2;
            res[resIndex++]=node.sum;
            // 矩阵中，该节点的上面节点
            if (index1-1>=0&& !set[index1 - 1][index2]){
                heap.add(new Node(index1-1,index2,arr1[index1-1]+arr2[index2]));
                set[index1-1][index2]=true;
            }
            if (index2-1>=0&& !set[index1][index2 - 1]){
                heap.add(new Node(index1,index2-1,arr1[index1]+arr2[index2-1]));
                set[index1][index2-1]=true;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 5, 6, 13, 14};
        int[] arr2 = {1, 3, 3};
        int[] topK = getTopK(arr1, arr2, 3);
        for (int i : topK) {
            System.out.println(i);
        }
    }
}
