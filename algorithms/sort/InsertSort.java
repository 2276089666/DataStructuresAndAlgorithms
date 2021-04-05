package sort;

/**
 * @Author ws
 * @Date 2021/4/5 14:52
 * @Version 1.0
 */
public class InsertSort {
    /**
     * 插入排序,间隔为1(稳定)
     * O(N^2)
     *
     * @param arr
     * @return
     */
    public static int[] insertSort(int[] arr) {
//        for (int i = 1; i < arr.length; i++) {
//            int insert = arr[i];
//            // 插入值的前一个值的下标
//            int index = i - 1;
//            // 如果插入的数比前一个小,移动
//            while (index >= 0 && insert < arr[index]) {
//                // 移动数组
//                arr[index + 1] = arr[index];
//                index--;
//            }
//            // 将值插入到比insert小的值的后面
//            arr[index + 1] = insert;
//        }
//        return arr;

        shellInsertSort(arr, 1);
        return arr;
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
