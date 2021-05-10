package arraySkills;

/**
 * @Author ws
 * @Date 2021/5/10 15:54
 * @Version 1.0
 */
public class LongestSumSubArrayLengthInPositiveArray {
    /**
     * 主要技巧：利用单调性优化
     * <p>
     * 给定一个正整数组成的无序数组arr，给定一个正整数值K
     * 找到arr的所有子数组里，哪个子数组的累加和等于K，并且是长度最大的
     * 返回其长度
     */

    // 利用窗口,一步步得到最大长度
    public static int getMaxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return 0;
        }
        // 窗口的两个边界,如果为-1,arr[L++]在某些情况会下标越界
        int L = 0;
        int R = 0;
        int sum = arr[0];
        int length=0;
        while (R < arr.length) {
            if (sum<k){
                R++;
                // 在这里利用R++的sum单调递增的单调性,提前结束循环避免不必要的计算
                if (R==arr.length){
                    break;
                }
                sum+=arr[R];
            }else if (sum==k){
                length=Math.max(length,R-L+1);
                sum-=arr[L++];
            }else {
                sum-=arr[L++];
            }
        }
        return length;
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 4, 6, 1, 1, 1, 1, 1, 1,1};
        int maxLength = getMaxLength(arr, 6);
        System.out.println(maxLength);
    }
}
