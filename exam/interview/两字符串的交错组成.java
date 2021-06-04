package interview;

/**
 * @Author ws
 * @Date 2021/6/4 17:52
 */
public class 两字符串的交错组成 {
    /**
     * 给定三个字符串str1、str2和aim，如果aim包含且仅包含来自str1和str2的所有字符，
     * 而且在aim中属于str1的字符之间保持原来在str1中的顺序，属于str2的字符之间保持 原来在str2中的顺序，
     * 那么称aim是str1和str2的交错组成。实现一个函数，判断aim是 否是str1和str2交错组成
     * 【举例】 str1="AB"，str2="12"。那么"AB12"、"A1B2"、"A12B"、"1A2B"和"1AB2"等都是 str1 和 str2 的 交错组成
     */

    public static boolean isCross(String str1,String str2,String aim){
        if (str1==null||str2==null||aim==null||str1.length()+str2.length()!=aim.length()){
            return false;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        char[] aims = aim.toCharArray();
        // dp[i][j]表示chars1[i]和chars[j]是否能组成aims[i+j]
        boolean[][] dp = new boolean[chars1.length + 1][chars2.length + 1];
        dp[0][0]=true;
        for (int i = 1; i <= chars1.length; i++) {
            if (chars1[i-1]!=aims[i-1]){
                break;
            }
            dp[i][0]=true;
        }
        for (int i = 1; i <= chars2.length; i++) {
            if (chars2[i-1]!=aims[i-1]){
                break;
            }
            dp[0][i]=true;
        }
        for (int i = 1; i <= chars1.length; i++) {
            for (int j = 1; j <= chars2.length; j++) {
                // chars1[i-1]==aims[i+j-1]&&dp[i-1][j]==true
                // 如果交错串以chars1[i-1]结尾，并且以chars1[i-2]结尾或者以chars2[j-1]结尾的组合串是交错串
                if ((chars1[i-1]==aims[i+j-1]&&dp[i-1][j]==true)||(chars2[j-1]==aims[i+j-1]&&dp[i][j-1]==true)){
                    dp[i][j]=true;
                }
            }
        }
        return dp[chars1.length][chars2.length];
    }

    public static void main(String[] args) {
        String str1="AB";
        String str2="12";
        String aim="1AB2";
        boolean cross = isCross(str1, str2, aim);
        System.out.println(cross);
    }
}
