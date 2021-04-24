package slidingWindowAndMonotonicStack.slidingWindow;

import java.util.LinkedList;

/**
 * @Author ws
 * @Date 2021/4/24 20:36
 * @Version 1.0
 */
public class SlidingWindowMaxArray {
    /**
     * 想用滑动窗口，要想办法把具体的问题转化为滑动窗口的处理流程
     * 想用滑动窗口最值的更新结构，就看看处理流程下，是否需要最值这个信息
     */

    /**
     * 假设一个固定大小为W的窗口，依次划过arr，
     * 返回每一次滑出状况的最大值
     * 例如，arr = [4,3,5,4,3,3,6,7], W = 3
     * 返回：[5,5,5,4,6,7]
     */

    public static int[] getMaxArray(int[] arr, int W) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        // 存储数组的下标,下标对应的值应该:从左到右是从大到小的
        LinkedList<Integer> list = new LinkedList<>();
        int index=0;
        int[] result = new int[arr.length - W + 1];
        for (int i = 0; i < arr.length; i++) {
            // 当前arr[i]的值比双向队列的最右边的大
            while (!list.isEmpty() && arr[list.peekLast()] <= arr[i]) {
                list.pollLast();
            }
            list.addLast(i);
            // 右移时,窗口下标的间隔大于W,双向队列的最大值要弹出
            if (list.peekFirst() == i - W) {
                list.pollFirst();
            }
            if (i>=W-1){
                result[index++]=arr[list.peekFirst()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
        int W=3;
        int[] maxArray = getMaxArray(arr, W);
        for (int i = 0; i < maxArray.length; i++) {
            System.out.println(maxArray[i]);
        }
    }
}
