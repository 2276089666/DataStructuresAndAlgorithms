package interview;

/**
 * @Author ws
 * @Date 2021/5/27 14:53
 */
public class 子数组最大累加和问题 {
    /**
     * 给定一个数组arr，返回子数组的最大累加和。
     */
    public static int getMaxInArr(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int max = Integer.MIN_VALUE;
        int current = 0;
        for (int i = 0; i < arr.length; i++) {
            current += arr[i];
            max = Math.max(max, current);
            current = current < 0 ? 0 : current;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr1 = { -2, -3, -5, 40, -10, -10, 100, 1 };
        System.out.println(getMaxInArr(arr1));

        int[] arr2 = { -2, -3, -5, 0, 1, 2, -1 };
        System.out.println(getMaxInArr(arr2));

        int[] arr3 = { -2, -3, -5, -1 };
        System.out.println(getMaxInArr(arr3));
    }
}
