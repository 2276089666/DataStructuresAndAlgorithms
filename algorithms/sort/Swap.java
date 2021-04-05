package sort;

/**
 * @Author ws
 * @Date 2021/4/5 14:49
 * @Version 1.0
 */
public class Swap {
    /**
     * 两值交换
     * 异或:相同两数异或为0,任何数与0异或还是任何数
     *
     * @param arr 需要交换的数组
     * @param i   下标
     * @param j   下标
     */
    public static void swap(int[] arr, int i, int j) {
        // 内存位置相同,arr[1]^arr[1]=0,会导致arr[1]=0
        // 但是 arr[]{1,2,1}   arr[0]^arr[2]不会出错
        if (i == j) {
            return;
        }
        // 异或操作 相同为0,不同为1
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j]; // arr[j] = arr[i]^arr[j]^arr[j] = arr[i]^0 = arr[i]
        arr[i] = arr[i] ^ arr[j]; // arr[i] = arr[i]^arr[j]^arr[i] = arr[j]^0 = arr[j]
    }


    public static void swap2(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
