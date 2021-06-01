package interview;


/**
 * @Author ws
 * @Date 2021/6/1 16:30
 */
public class 最大子数组异或和 {
    /**
     * 一个数组的异或和是指数组中所有的数异或在一起的结果
     * 给定一个数组arr，求最大子数组异或和。
     */

    // 使用预处理数组将O(N^3)的时间复杂降到O(N^2)
    public static int getMaxXOR(int[] arr){
        if (arr==null||arr.length==0){
            return -1;
        }
        int[] help = new int[arr.length];
        help[0]=arr[0];
        for (int i = 1; i < arr.length; i++) {
            help[i]=help[i-1]^arr[i];
        }
        int max= Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <= i; j++) {
                // help[j-1]^help[i]由于相同得数异或为0,(0~j-1)^(0~i)故可以得到j到i的异或和,中间相同的部分是0消去了
                max=Math.max(max,j==0?help[i]:help[j-1]^help[i]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] ints = {1, 2, 3};
        int maxXOR = getMaxXOR(ints);
        System.out.println(maxXOR); // max=1^2
    }
}
