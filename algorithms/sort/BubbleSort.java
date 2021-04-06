package sort;

/**
 * @Author ws
 * @Date 2021/4/5 14:48
 * @Version 1.0
 */
public class BubbleSort {
    /**
     * 冒泡排序(稳定)
     * 时间复杂度: O(N^2)
     * 额外空间复杂度: O(1)
     * @param arr
     * @return
     */
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    Swap.swap(arr, j, j + 1);
                }
            }
        }
    }

}
