package arraySkills;

/**
 * @Author ws
 * @Date 2021/5/10 21:32
 * @Version 1.0
 */
public class LongestLessSumSubArrayLength {
    /**
     * 假设答案法+淘汰可能性（很难，以后还会见到）
     *
     * 给定一个整数组成的无序数组arr，值可能正、可能负、可能0
     * 给定一个整数值K
     * 找到arr的所有子数组里，哪个子数组的累加和<=K，并且是长度最大的
     * 返回其长度
     */


    // O(N)
    public static int maxLengthAwesome(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 记录以该下标向右累加和的最小值
        int[] minSums = new int[arr.length];
        // 最小值的最右边界
        int[] minSumEnds = new int[arr.length];

        // 最后一个下标的数的值是固定的
        minSums[arr.length - 1] = arr[arr.length - 1];
        minSumEnds[arr.length - 1] = arr.length - 1;

        // 依次赋值两个数组
        for (int i = arr.length - 2; i >= 0; i--) {
            if (minSums[i + 1] < 0) {
                minSums[i] = arr[i] + minSums[i + 1];
                minSumEnds[i] = minSumEnds[i + 1];
            } else {
                minSums[i] = arr[i];
                minSumEnds[i] = i;
            }
        }
        int end = 0;
        int sum = 0;
        int res = 0;
        // i是窗口的最左的位置，end扩出来的最右有效块儿的最后一个位置的，再下一个位置
        // end也是下一块儿的开始位置
        // 窗口：[i~end)
        for (int i = 0; i < arr.length; i++) {
            // while循环结束之后：
            // 1) 如果以i开头的情况下，累加和<=k的最长子数组是arr[i..end-1]，看看这个子数组长度能不能更新res；
            // 2) 如果以i开头的情况下，累加和<=k的最长子数组比arr[i..end-1]短，更新还是不更新res都不会影响最终结果；
            while (end < arr.length && sum + minSums[end] <= k) {
                sum += minSums[end];
                end = minSumEnds[end] + 1;
            }
            res = Math.max(res, end - i);
            if (end > i) { // 窗口内还有数 [i~end) [4,4)
                sum -= arr[i];
            } else { // 窗口内已经没有数了，说明从i开头的所有子数组累加和都不可能<=k
                end = i + 1;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] arr = {1, 5, 4, -7, 0, 5, 0, -2, 6, 2};
        int i = maxLengthAwesome(arr, 8);
        System.out.println(i);
    }
}
