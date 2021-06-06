package interview;

/**
 * @Author ws
 * @Date 2021/6/6 21:34
 */
public class 累加出整个范围所有的数至少还需几个数 {
    /**
     * 给定一个有序的正数数组arr和一个正数range，如果可以自由选择arr中的数字，想累加得 到 1~range 范围上所有的数，返回arr最少还缺几个数。
     * 【举例】
     * arr = {1,2,3,7}，range = 15
     * 想累加得到 1~15 范围上所有的数，arr 还缺 14 这个数，所以返回1 arr = {1,5,7}，range = 15
     * 想累加得到 1~15 范围上所有的数，arr 还缺 2 和 4，所以返回2
     */

    // arr请保证有序，且正数
    public static int minPatches(int[] arr, int K) {
        int patches = 0; // 缺多少个数字
        long range = 0; // 已经完成了1 ~ range的目标
        for (int i = 0; i != arr.length; i++) {
            // 1~range
            // 1 ~ arr[i]-1
            while (arr[i] > range + 1) { // arr[i] 1 ~ arr[i]-1
                range += range + 1; // range + 1 是缺的数字
                patches++;
                if (range >= K) {
                    return patches;
                }
            }
            // 用到了数组中的数
            range += arr[i];
            if (range >= K) {
                return patches;
            }
        }
        // 数组遍历完了,没数加了,后面全是缺的
        while (K >= range + 1) {
            range += range + 1;
            patches++;
        }
        return patches;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 7};
        int i = minPatches(arr, 15);
        System.out.println(i);
    }
}
