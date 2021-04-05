package sort;

import java.util.NoSuchElementException;
import java.util.PriorityQueue;

/**
 * @Author ws
 * @Date 2021/4/5 14:59
 * @Version 1.0
 */
public class HeapSort {
    /**
     * 堆排序(不稳定)
     *  O(N logN)   额外空间复杂度O(1)
     * 基于完全二叉树
     * 左孩子 2*i+1
     * 右孩子 2*i+2
     * 父节点 (i-1)/2
     *
     * @param arr
     */
    public static void heapSort(int[] arr) {
        if (arr == null && arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {   // N
            // 创建大根堆
            heapInsert(arr, i);                  // logN
        }

        // TODO: 2021/4/5 与创建大根堆得操作为2选1
        // O(N) ,如果用户一下把所有得数据都给我们我们就直接把整个数组当成大根堆,直接从下向上来堆得下沉,树得最下一层N/2个节点不用下沉,最后这个操作得时间复杂度为O(N)
//        for (int i = arr.length-1; i >=0 ; i--) {
//            heapify(arr,i,arr.length);
//        }

        // 排序
        int size = arr.length - 1;
        do {
            // 第一个节点和最后一个节点交换,size--是最后一个是当前循环最大的不用参与比较
            Swap.swap2(arr, 0, size--);
            // 堆化
            heapify(arr, 0, size);                         // logN
        } while (size > 0);                                   // N
    }

    /**
     * 大根堆插入(每棵子树得最大值一定是头节点)
     *
     * @param arr
     * @param i
     */
    private static void heapInsert(int[] arr, int i) {
        int index = i;
        // 如果子节点比父节点大,我们交换位置,当index=0时  (index-1)/2 =0 计算机就是这么算的
        while (arr[index] > arr[(index - 1) / 2]) {
            Swap.swap2(arr, index, (index - 1) / 2);
            // 继续向上找父亲节点
            index = (index - 1) / 2;
        }
    }

    /**
     * 堆的下沉
     *
     * @param arr
     * @param i
     * @param size
     */
    private static void heapify(int[] arr, int i, int size) {
        int index = i;
        // 左孩子
        int left = index * 2 + 1;
        while (left <= size) {
            // 返回左右孩子那个大的节点的下标
            int large = left + 1 <= size && arr[left + 1] > arr[left] ? left + 1 : left;
            // 找到父节点和他儿子节点那个大的节点的下标
            large = arr[large] > arr[index] ? large : index;
            // 如果父节点大,本次不下沉
            if (large == index) {
                break;
            }
            // 值小的节点下沉,形成大根堆
            Swap.swap2(arr, index, large);

            index = large;
            left = index * 2 + 1;
        }
    }

    public static void main(String[] args) {
        // 优先级队列就是我们得小根堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(5);
        priorityQueue.add(4);
        priorityQueue.add(0);
        priorityQueue.add(7);
        priorityQueue.add(-1);
        while (!priorityQueue.isEmpty()){
            System.out.println(priorityQueue.poll());
        }
    }

}
