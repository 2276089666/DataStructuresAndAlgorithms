package sort;

/**
 * @Author ws
 * @Date 2021/4/5 14:58
 * @Version 1.0
 */
public class RadixSort {
    /**
     * 基数排序(数组值不能有负数,如果必须要,我们把正数和负数分开排序再合并),桶排序思想的一种
     * O(N*log(10,Max))
     *
     * @param arr
     * @return
     */
    public static void radixSort(int[] arr) {
        int maxbits = maxbits(arr);
        // 10的maxDigit+1次方,数组最大位数的数据上限
        double max = Math.pow(10, maxbits);
        // 代表位数对应的数:1,10,100~
        int n = 1;
        // 保存每一位的排序后的结果用于下一位的排序输入
        int k = 0;
        //  二维数组用于保存每次排序后的结果,将当前位上的排序结果相同的数字放在同一个桶里面
        int[][] bucket = new int[10][arr.length];
        // 用于保存每次排序后的结果,将当前位上排序结果相同的数字放在同一个桶里
        int[] order = new int[arr.length];
        while (n < max) {
            for (int i = 0; i < arr.length; i++) {
                // 个位上的数字,或者十位上的数字~
                int digit = (arr[i] / n) % 10;
                // 将数据放到桶里面
                bucket[digit][order[digit]] = arr[i];
                order[digit]++;
            }
            for (int i = 0; i < arr.length; i++) {
                // 桶里面如果有数据,从上到下遍历这个桶,并将数据保存到原数组
                if (order[i] != 0) {
                    for (int j = 0; j < order[i]; j++) {
                        arr[k] = bucket[i][j];
                        k++;
                    }
                }
                order[i] = 0;
            }
            // 从个位移到十位再到百位~
            n *= 10;
            k = 0; // 下一轮保存位排序结果做准备
        }
    }

    /**
     * 计算一个数组中的最大数的位数
     * @param arr
     * @return
     */
    public static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }

}
