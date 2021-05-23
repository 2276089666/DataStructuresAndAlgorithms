package interview;

/**
 * @Author ws
 * @Date 2021/5/22 18:55
 */
public class 最长公共子序列 {
    /**
     * 请注意区分子串和子序列的不同，给定两个字符串str1和str2，
     * 求两个字符的最长公共子序列
     */

    public static int getCommonSubsequenceOfTwoStrings(String a,String b){
        if (a==null||b==null||"".equals(a)||"".equals(b)){
            return 0;
        }
        char[] array1 = a.toCharArray();
        char[] array2 = b.toCharArray();
        return lcse(array1,array2);
    }

    // 动态规划
    // 利用二维数组行代表str1的index,列代表str2的index,如果str1的3对于str2的2相等，我们的dp[2][1]=1
    public static int lcse(char[] str1, char[] str2) {
        int[][] dp = new int[str1.length][str2.length];
        // baseCase
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < str1.length; i++) {
            // 串的前面部分某个字符已经相等，后面的这行或这列必定为1
            dp[i][0] = str1[i] == str2[0] ? 1 : 0;
        }
        for (int j = 1; j < str2.length; j++) {
            dp[0][j] = str1[0] == str2[j] ? 1 : 0;
        }
        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                // 相等的部分以str1结尾或者以str2结尾
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                // 相等的部分不仅以str1结尾并且也以str2结尾
                if (str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[str1.length - 1][str2.length - 1];
    }


    public static void main(String[] args) {
        String a="1890238kklasd";
        String b="asdpoqp897231d";
        int commonSubsequenceOfTwoStrings = getCommonSubsequenceOfTwoStrings(a, b);
        System.out.println(commonSubsequenceOfTwoStrings);
    }
}
