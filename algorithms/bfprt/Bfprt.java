package bfprt;

import java.util.Arrays;

import static sort.Swap.swap2;


/**
 * @Author ws
 * @Date 2021/5/5 19:41
 * @Version 1.0
 */
public class Bfprt {
    /**
     * 在无序数组中求第K小的数
     * <p>
     * 1）改写快排的方法
     * <p>
     * 2）bfprt算法
     */

    // 使用快速排序
    public static int getNum(int[] arr, int k) {
        int[] newArray = Arrays.copyOf(arr, arr.length);
        return process(arr, 0, newArray.length - 1, k - 1);
    }

    private static int process(int[] arr, int L, int R, int k) {
        if (L == R) {
            return arr[L];
        }
        // 快排的基准值
        int pivot = arr[(L + (int) Math.random() * (R - L + 1))];
        int[] result = partition(arr, L, R, pivot);
        if (k >= result[0] && k <= result[1]) {
            return arr[result[0]];
        } else if (k < result[0]) {
            return process(arr, L, result[0] - 1, k);
        } else {
            return process(arr, result[1] + 1, R, k);
        }
    }

    private static int[] partition(int[] arr, int l, int r, int pivot) {
        int left = l - 1;
        int right = r + 1;
        int current = l;
        while (current < right) {
            if (arr[current] < pivot) {
                swap2(arr, current++, ++left);
            } else if (arr[current] > pivot) {
                swap2(arr, current, --right);
            } else {
                current++;
            }
        }
        return new int[]{left + 1, right - 1};
    }


    // 使用bfprt去挑选我们那个基准值,保证最后的时间复杂度不像快速排序是用数学期望去求的
    public static int getNum2(int[] array, int k) {
        int[] arr = Arrays.copyOf(array,array.length);
        return bfprt(arr, 0, arr.length - 1, k - 1);
    }

    public static int bfprt(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];
        }
        int pivot = medianOfMedians(arr, L, R);
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return bfprt(arr, L, range[0] - 1, index);
        } else {
            return bfprt(arr, range[1] + 1, R, index);
        }
    }

    // 核心
    public static int medianOfMedians(int[] arr, int L, int R) {
        int size = R - L + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        // 中位数数组
        int[] mArr = new int[size / 5 + offset];
        for (int team = 0; team < mArr.length; team++) {
            // 间隔
            int teamFirst = L + team * 5;
            mArr[team] = getMedian(arr, teamFirst, Math.min(R, teamFirst + 4));
        }
        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    // 先排序,再拿到数组的中位数
    public static int getMedian(int[] arr, int L, int R) {
        insertionSort(arr, L, R);
        // 拿到中位数
        return arr[(L + R) / 2];
    }

    public static void insertionSort(int[] arr, int L, int R) {
        for (int i = L + 1; i <= R; i++) {
            for (int j = i; j >= L && arr[j-1] > arr[j]; j--) {
                swap2(arr, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, -5, 5, 6, 4, 7, 9};
        int num = getNum(arr, 4);
        System.out.println(num);
        int num2 = getNum2(arr, 4);
        System.out.println(num2);
    }
}
