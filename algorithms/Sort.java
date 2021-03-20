/**
 * @Author ws
 * @Date 2021/3/20 15:15
 * @Version 1.0
 */
public class Sort {

    /**
     * 两值交换
     *
     * @param arr 需要交换的数组
     * @param i   下标
     * @param j   下标
     */
    public static void swap(int[] arr, int i, int j) {
        // 异或操作 相同为0,不同为1
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    /**
     * 冒泡排序
     *
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
        return arr;
    }

    /**
     * 插入排序
     *
     * @param arr
     * @return
     */
    public static int[] insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insert = arr[i];
            // 插入值的前一个值的下标
            int index = i - 1;
            // 如果插入的数比前一个小,移动
            while (index >= 0 && insert < arr[index]) {
                // 移动数组
                arr[index + 1] = arr[index];
                index--;
            }
            // 将值插入到比insert小的值的后面
            arr[index + 1] = insert;
        }
        return arr;
    }

    /**
     * 快速排序
     * @param arr
     * @param low
     * @param high
     * @return
     */
    public static int[] quickSort(int[] arr, int low, int high) {
        // 从前向后比较的索引
        int start = low;
        // 从后向前比较的索引
        int end = high;
        // 基准值
        int key = arr[low];
        while (end > start) {
            while (end > start && arr[end] >= key)
                end--;
            // 从后向前比较,找到比基准值小的,并交换
            if (arr[end] < key) {
                swap(arr, start, end);
            }
            while (end > start && arr[start] <= key)
                start++;
            // 从前向后比较,找到比基准值大的,并交换
            if (arr[start] > key) {
                swap(arr, start, end);
            }

        }

        // 递归左边序列
        if (start > low) {
            quickSort(arr, low, start - 1);
        }
        // 递归右边序列
        if (end < high) {
            quickSort(arr, end + 1, high);
        }
        return arr;
    }

    public static void main(String[] args) {

        int[] arr = {1, 4, -6, 8, 5, 12};
        int[] sort = bubbleSort(arr);
        int[] arr2 = {1, 4, -6, 8, 5, 12};
        int[] sort2 = insertSort(arr2);
        int[] arr3 = {1, 4, -6, 8, 5, 7};
        int[] sort3 = quickSort(arr3, 0, arr3.length - 1);

    }
}
