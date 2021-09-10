package sort;

/**
 * @Author ws
 * @Date 2021/4/5 14:55
 * @Version 1.0
 */
public class ShellSort {
    /**
     * 希尔排序(不稳定)
     *
     * @param arr
     * @return
     */
    public static void shellSort(int[] arr) {
        // 决定间隔
        for (int dk = arr.length; dk > 0; dk = dk / 2) {
            shellInsertSort(arr, dk);
        }
    }

    /**
     * 以dk为间隔的插入排序
     *
     * @param arr
     * @param dk
     */
    private static void shellInsertSort(int[] arr, int dk) {
        // 根据间隔dk,将大数组分为若干数组
        for (int i = dk; i < arr.length; i++) {
            // 如果当前下标的值比它下标小dk的值小我们就交换,插入排序,间隔为dk
            // j >= dk避免后面的比较造成数组下标越界
            for (int j = i; j >= dk && arr[j] < arr[j - dk]; j = j - dk) {
                // 两值交换
                Swap.swap(arr, j, j - dk);
            }
        }
    }
}
