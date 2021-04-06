package sort;

/**
 * @Author ws
 * @Date 2021/4/5 14:51
 * @Version 1.0
 */
public class SelectSort {
    /**
     * 选择排序(不稳定)
     * 时间复杂度: O(N^2)
     * 空间复杂度: O(1)
     * @param arr
     */
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            // 与数组的复杂度无关,永远只交换N次
            Swap.swap(arr, i, minIndex);
        }
    }
}
