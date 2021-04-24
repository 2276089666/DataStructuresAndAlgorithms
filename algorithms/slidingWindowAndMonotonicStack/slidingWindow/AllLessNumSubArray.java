package slidingWindowAndMonotonicStack.slidingWindow;

import java.util.LinkedList;

/**
 * @Author ws
 * @Date 2021/4/24 21:33
 * @Version 1.0
 */
public class AllLessNumSubArray {
    /**
     * 想用滑动窗口，要想办法把具体的问题转化为滑动窗口的处理流程
     * 想用滑动窗口最值的更新结构，就看看处理流程下，是否需要最值这个信息
     */

    /**
     * 给定一个整型数组arr，和一个整数num
     * 某个arr中的子数组sub，如果想达标，必须满足：
     * sub中最大值 – sub中最小值 <= num，
     * 返回arr中达标子数组的数量
     */

    public static int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        LinkedList<Integer> min = new LinkedList<>();
        LinkedList<Integer> max = new LinkedList<>();
        int L = 0;
        int R = 0;
        int result = 0;
        // 按下标一个个向右尝试
        while (L < arr.length) {
            while (R < arr.length) {
                while (!max.isEmpty() && arr[max.peekLast()] <= arr[R]) {
                    max.pollLast();
                }
                max.addLast(R);
                while (!min.isEmpty() && arr[min.peekLast()] >= arr[R]) {
                    min.pollLast();
                }
                min.addLast(R);
                // 不符合要求的
                if (arr[max.peekFirst()] - arr[min.peekFirst()] > num) {
                    break;
                }
                R++;
            }
            // R为满足条件的下标加1
            result += R - L;
            if (min.peekFirst() == L) {
                min.pollFirst();
            }
            if (max.peekFirst() == L) {
                max.pollFirst();
            }
            L++;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 4, 6, 7};
        int num = 2;
        //[1,1] [5,5] [5,4] [5,6] [4,4] [4,6] [6,6] [6,7] [7,7]
        int num1 = getNum(arr, num);
        System.out.println(num1);
    }
}
