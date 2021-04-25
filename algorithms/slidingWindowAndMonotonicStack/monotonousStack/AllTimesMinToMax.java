package slidingWindowAndMonotonicStack.monotonousStack;

import java.util.Stack;

/**
 * @Author ws
 * @Date 2021/4/25 15:08
 * @Version 1.0
 */
public class AllTimesMinToMax {
    /**
     * 想用单调栈，要想办法把具体的问题转化为单调栈所解决的原问题
     */

    /**
     * 给定一个只包含正数的数组arr，arr中任何一个子数组sub，
     * 一定都可以算出(sub累加和 )* (sub中的最小值)是什么，
     * 那么所有子数组中，这个值最大是多少？
     */

    // 暴力枚举
    public static int getArrayMax(int[] arr){
        if (arr==null||arr.length==0){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, minNum * sum);
            }
        }
        return max;
    }

    // 使用单调栈实现
    public static int getArrayMax2(int[] arr){
        int size = arr.length;
        int[] sums = new int[size];
        sums[0] = arr[0];
        // 计算出每个子数组的累加和
        for (int i = 1; i < size; i++) {
            sums[i] = sums[i - 1] + arr[i];
        }
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < size; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int j = stack.pop();
                // 算出以j为下标的最小值的子数组的结果
                // sums[i - 1] - sums[stack.peek()]减掉栈底比arr[j]小的数的累加和,因为我们此时是把arr[j]看作最小
                max = Math.max(max, (stack.isEmpty() ? sums[i - 1] : (sums[i - 1] - sums[stack.peek()])) * arr[j]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            max = Math.max(max, (stack.isEmpty() ? sums[size - 1] : (sums[size - 1] - sums[stack.peek()])) * arr[j]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 6, 3};
        int arrayMax = getArrayMax(arr);
        int arrayMax2 = getArrayMax2(arr);
        System.out.println(arrayMax);
        System.out.println(arrayMax2);
    }
}
