package interview;

/**
 * @Author ws
 * @Date 2021/5/19 13:19
 * @Version 1.0
 */
public class Exam_03 {
    /**
     * 括号有效配对是指：
     * 1）任何一个左括号都能找到和其正确配对的右括号
     * 2）任何一个右括号都能找到和其正确配对的左括号
     * 返回一个括号字符串中，最长的括号有效子串的长度
     */

    // 动态规划
    public static int getMaxLength(String str){
        if (str==null||str.length()==0){
            return -1;
        }
        char[] array = str.toCharArray();
        int[] dp = new int[array.length];
        int result=0;
        int pre=0;
        for (int i = 1; i < array.length; i++) {
            if (array[i]==')'){
                pre=i-dp[i-1]-1;
                if (pre>=0&&array[pre]=='('){
                    dp[i]=dp[i-1]+2+(pre>0?dp[pre-1]:0);
                }
            }
            result=Math.max(result,dp[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        String str="(())(()()()((()()))";
        int maxLength = getMaxLength(str);
        System.out.println(maxLength);
    }
}
