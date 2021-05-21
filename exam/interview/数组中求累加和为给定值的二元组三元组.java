package interview;


import java.util.ArrayList;
import java.util.List;

/**
 * @Author ws
 * @Date 2021/5/21 19:48
 */
public class 数组中求累加和为给定值的二元组三元组 {
    /**
     * 给定一个有序数组arr，给定一个正数aim
     * <p>
     * 1）返回累加和为aim的，所有不同二元组
     * <p>
     * 2）返回累加和为aim的，所有不同三元组
     */

    // 看到有序数组尝试双指针,窗口,单调栈
    // 二元组
    public static List<int[]> getArray(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return null;
        }
        int L = 0;
        int R = arr.length - 1;
        ArrayList<int[]> list = new ArrayList<>();
        while (L < R) {
            if (arr[L] + arr[R] < aim) {
                L++;
            } else if (arr[L] + arr[R] == aim) {
                // 去重
                if (L == 0 || R == arr.length - 1 || arr[L - 1] != arr[L] && arr[R] != arr[R + 1]) {
                    list.add(new int[]{arr[L], arr[R]});
                }
                L++;
                R--;
            } else {
                R--;
            }
        }
        return list;
    }


    // 三元组 固定三元组的开头,按二元组的方式去找,然后换开头
    public static List<int[]> getArray2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return null;
        }
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            // 去重
            if (i>0&&arr[i]==arr[i-1]) continue;
            int L = i + 1;
            int R = arr.length - 1;
            while (L < R) {
                if (arr[i] + arr[L] + arr[R] < aim) {
                    L++;
                } else if (arr[i] + arr[L] + arr[R] == aim) {
                    // 去重
                    if (L == 0 || R == arr.length - 1 || arr[L - 1] != arr[L] && arr[R] != arr[R + 1]) {
                        list.add(new int[]{arr[i], arr[L], arr[R]});
                    }
                    L++;
                    R--;
                } else {
                    R--;
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 3, 4, 5, 5, 6, 6, 7, 8, 8, 9, 12};
        List<int[]> array = getArray(arr, 9);
        for (int[] ints : array) {
            System.out.println("[" + ints[0] + "," + ints[1] + "]");
        }

        System.out.println();
        int[] arr2 = new int[]{1, 1, 2, 2, 3, 4, 4, 6, 6, 8, 8, 9, 10, 12, 13};
        List<int[]> array2 = getArray2(arr2, 14);
        for (int[] ints : array2) {
            System.out.println("[" + ints[0] + "," + ints[1] + "," + ints[2] + "]");
        }
    }
}
