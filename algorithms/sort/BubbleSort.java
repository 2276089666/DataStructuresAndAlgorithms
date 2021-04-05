package sort;

/**
 * @Author ws
 * @Date 2021/4/5 14:48
 * @Version 1.0
 */
public class BubbleSort {
    /**
     * 冒泡排序(稳定)
     * O(N^2)
     *
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    Swap.swap(arr, j, j + 1);
                }
            }
        }
        return arr;
    }

}
