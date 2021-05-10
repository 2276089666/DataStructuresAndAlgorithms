package arraySkills;


import java.util.HashMap;

/**
 * @Author ws
 * @Date 2021/5/10 17:00
 * @Version 1.0
 */
public class LongestSumSubArrayLength {
    /**
     * 利用预处理结构优化
     *
     * 给定一个整数组成的无序数组arr，值可能正、可能负、可能0
     * 给定一个整数值K
     * 找到arr的所有子数组里，哪个子数组的累加和等于K，并且是长度最大的
     * 返回其长度
     */

    // 以下标0开头的子数组,一个个递增,暴力枚举                O(N^2)
    public static int getMaxLength(int[] arr,int k){
        if (arr==null||arr.length==0){
            return 0;
        }
        int length=0;

        for (int i = 0; i < arr.length; i++) {
            int sum=0;
            for (int j = i; j < arr.length; j++) {
                sum+=arr[j];
                if (sum==k){
                    length= Math.max(length,j-i+1);
                }
            }
        }
        return length;
    }

    // 我们以某个下标结尾的字串一个个试探
    // 发现最先达到sum[i]-k的另外一部分就是最长length             O(N)
    public static int getMaxLength2(int[] arr,int k){
        if (arr==null||arr.length==0){
            return 0;
        }
        // k:sum v:i
        HashMap<Integer, Integer> map = new HashMap<>();
        int length=0;
        int sum=0;
        map.put(0,-1);  //
        for (int i = 0; i < arr.length; i++) {
            sum+=arr[i];
            // 找到与k互补等于sum的值的下标,例如 sum=10 k=8 我们只要找到和最先为2的下标,我们就找到了最后和为8的长度,即最长
            if (map.containsKey(sum-k)){
                length=Math.max(length,i-map.get(sum-k));
            }
            // 如果不同下标出现相同的sum,用前面最先找到的
            if (!map.containsKey(sum)){
                map.put(sum,i);
            }
        }
        return length;
    }


    public static void main(String[] args) {
        int[] arr = {1, 6, 4, 4, 0, 3,-3, 8, 2, 2, 2};
        int maxLength = getMaxLength(arr, 10);
        System.out.println(maxLength);
        int maxLength2 = getMaxLength2(arr, 10);
        System.out.println(maxLength2);
    }
}
