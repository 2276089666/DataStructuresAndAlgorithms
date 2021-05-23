package interview;

import sort.Swap;

import java.util.Arrays;

/**
 * @Author ws
 * @Date 2021/5/22 12:30
 */
public class 利用bfprt思想找出第k小值求解问题 {
    /**
     * 长度为N的数组arr，一定可以组成N^2个数值对。
     * 例如arr = [3,1,2]，
     * 数值对有(3,3) (3,1) (3,2) (1,3) (1,1) (1,2) (2,3) (2,1) (2,2)，
     * 也就是任意两个数都有数值对，而且自己和自己也算数值对。
     * 数值对怎么排序？规定，第一维数据从小到大，第一维数据一样的，第二维数组也从小到大。所以上面的数值对排序的结果为：
     * (1,1)(1,2)(1,3)(2,1)(2,2)(2,3)(3,1)(3,2)(3,3)
     * <p>
     * 给定一个数组arr，和整数k，返回第k小的数值对。
     */

    // 第一个数在排序后发现可以用((k - 1) / arr.length)求
    // 第二个数在排序后发现可以用 ((rest - 1) / eqKNum) 求
    // O(N) 利用改进快排求第k小
    public static int[] getKMin(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0 || k > arr.length << 1) {
            return null;
        }
        int[] copyArr = Arrays.copyOf(arr, arr.length);
        // 第一个数 ((k - 1) / arr.length)
        // 快排求第k小
        int kMin = process(copyArr, 0, copyArr.length - 1, ((k - 1) / arr.length));
        int lessKNum = 0;
        int eqKNum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < kMin) {
                lessKNum++;
            }
            if (arr[i] == kMin) {
                eqKNum++;
            }
        }
        int rest = k - (lessKNum * arr.length);
        // 第二个数  ((rest - 1) / eqKNum)
        int result2 = process(arr, 0, copyArr.length - 1, ((rest - 1) / eqKNum));
        return new int[]{kMin, result2};
    }

    private static int process(int[] copyArr, int L, int R, int k) {
        if (L==R){
            return copyArr[L];
        }
        int base = copyArr[L + (int) Math.random() * (R - L + 1)];
        int[] eqArr = partition(copyArr, L, R, base);
        if (k >= eqArr[0] && k <= eqArr[1]) {
            return copyArr[eqArr[0]];
        } else if (k > eqArr[1]) {
            return process(copyArr, eqArr[1] + 1, R, k);
        } else {
            return process(copyArr, L, eqArr[0] - 1, k);
        }
    }

    private static int[] partition(int[] copyArr, int l, int r, int base) {
        int L = l - 1;
        int R = r + 1;
        int current = l;
        while (current < R) {
            if (copyArr[current] < base) {
                Swap.swap2(copyArr, current++, ++L);
            } else if (copyArr[current] > base) {
                Swap.swap2(copyArr, current, --R);
            } else {
                current++;
            }
        }
        return new int[]{L + 1, R - 1};
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 3, 2};
        int[] kMin = getKMin(arr, 8);
        System.out.print("[" + kMin[0] + "," + kMin[1] + "]");

    }
}
