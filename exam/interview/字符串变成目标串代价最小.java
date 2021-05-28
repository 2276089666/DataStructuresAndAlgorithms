package interview;

/**
 * @Author ws
 * @Date 2021/5/27 20:36
 */
public class 字符串变成目标串代价最小 {
    /**
     * 给定两个字符串str1和str2，再给定三个整数ic、dc和rc，分别代表插入、删 除和替换一个字符的代价，返回将str1编辑成str2的最小代价。
     * 【举例】
     * str1="abc"，str2="adc"，ic=5，dc=3，rc=2 从"abc"编辑成"adc"，把'b'替换成'd'是代价最小的，所以返回2
     * str1="abc"，str2="adc"，ic=5，dc=3，rc=100 从"abc"编辑成"adc"，先删除'b'，然后插入'd'是代价最小的，所以返回8
     * str1="abc"，str2="abc"，ic=5，dc=3，rc=2 不用编辑了，本来就是一样的字符串，所以返回0
     */


    /**
     * @param str1 给定串
     * @param str2 目标串
     * @param ic   插入代价
     * @param dc   删除代价
     * @param rc   替换代价
     * @return
     */
    public static int getMinCost(String str1, String str2, int ic, int dc, int rc) {
        if (str1 == null || str2 == null) {
            return -1;
        }
        // 把s1变成s2
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int row = s1.length + 1;
        int col = s2.length + 1;
        int[][] dp = new int[row][col];
        // 目标串为空串
        for (int i = 1; i < col; i++) {
            dp[0][i] += dc;
        }
        // 给定串为空串
        for (int i = 1; i < row; i++) {
            dp[i][0] += ic;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (s1[j-1]==s2[i-1]){
                    dp[i][j]=dp[i-1][j-1];
                }else {
                    dp[i][j]=dp[i-1][j-1]+rc;
                }
                dp[i][j]=Math.min(dp[i][j],dp[i-1][j]+ic);
                dp[i][j]=Math.min(dp[i][j],dp[i][j-1]+dc);
            }
        }
        return dp[row-1][col-1];
    }


    public static void main(String[] args) {
        String a="abc";
        String b="adc";
        int minCost = getMinCost(a, b, 5, 3, 2);
        System.out.println(minCost);
        int minCost1 = getMinCost(a, b, 5, 3, 100);
        System.out.println(minCost1);
        int minCost2 = getMinCost(a, "abc", 5, 3, 2);
        System.out.println(minCost2);
    }
}
