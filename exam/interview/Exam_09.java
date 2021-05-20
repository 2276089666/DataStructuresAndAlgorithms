package interview;

/**
 * @Author ws
 * @Date 2021/5/20 16:06
 */
public class Exam_09 {
    /**
     * 给定一个数组arr长度为N，你可以把任意长度大于0且小于N的前缀作为左部分，剩下的 作为右部分。
     *
     * 但是每种划分下都有左部分的最大值和右部分的最大值，请返回最大的，左部分最大值减去右部分最大值的绝对值。
     */

    // 暴力枚举每个位置
    public static int maxABS1(int[] arr) {
        int res = Integer.MIN_VALUE;
        int maxLeft = 0;
        int maxRight = 0;
        for (int i = 0; i != arr.length - 1; i++) {
            maxLeft = Integer.MIN_VALUE;
            for (int j = 0; j != i + 1; j++) {
                maxLeft = Math.max(arr[j], maxLeft);
            }
            maxRight = Integer.MIN_VALUE;
            for (int j = i + 1; j != arr.length; j++) {
                maxRight = Math.max(arr[j], maxRight);
            }
            res = Math.max(Math.abs(maxLeft - maxRight), res);
        }
        return res;
    }

    // 发现要想没有全局最大值的那部分中找到的最大值最小,另部分只要一个值
    public static int maxABS2(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        return max - Math.min(arr[0], arr[arr.length - 1]);
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 4, 9, -2, 3, 5};
        int i = maxABS1(arr);
        int i1 = maxABS2(arr);
        // 分界线在1和5之间
        System.out.println(i);
        System.out.println(i1);
    }
}
