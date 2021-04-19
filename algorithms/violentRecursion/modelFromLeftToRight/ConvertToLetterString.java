package violentRecursion.modelFromLeftToRight;

/**
 * @Author ws
 * @Date 2021/4/17 22:24
 * @Version 1.0
 */
public class ConvertToLetterString {
    /**
     * 规定1和A对应、2和B对应、3和C对应...
     * 那么一个数字字符串比如"111”就可以转化为:
     * "AAA"、"KA"和"AK"
     * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
     */

    public static int getResultNum(String str) {
        if (str == null || "".equals(str)) {
            return -1;
        }
        char[] chars = str.toCharArray();
        return getResult(chars, 0);
    }

    private static int getResult(char[] chars, int index) {
        if (chars.length == index) {
            return 1;
        }
        // 0没有对应的大写字母
        if (chars[index] == '0') {
            return 0;
        }
        if (chars[index] == '1') {
            int result = getResult(chars, index + 1); // index自己作为单独的部分，后续有多少种方法
            if (index + 1 < chars.length) {
                result += getResult(chars, index + 2); // (index和index+1)作为单独的部分，后续有多少种方法
            }
            return result;
        }
        if (chars[index] == '2') {
            int result = getResult(chars, index + 1); // index自己作为单独的部分，后续有多少种方法
            // (index和index+1)作为单独的部分并且没有超过26，后续有多少种方法
            if (index + 1 < chars.length && (chars[index + 1] >= '0' && chars[index] <= '6')) {
                result += getResult(chars, index + 2);
            }
            return result;
        }
        // chars[i] == '3' ~ '9'
        return getResult(chars, index + 1);
    }

    /**
     * 利用上面的暴力递归改动态规划
     * @param str
     * @return
     */
    public static int dpWay(String str) {
        if (str == null || "".equals(str)) {
            return -1;
        }
        char[] chars = str.toCharArray();
        // 前面的暴力递归只有一个参数index在变化,所以创建一维数组就ok了
        int N = chars.length;
        // 上面的暴力递归chars.length == index说明index达到了length,我们的数组就应该N+1,不然下标越界
        int[] dp = new int[N + 1];
        dp[N] = 1;
        // 发现上面的递归依赖,这个一维表从右往左填
        for (int i = N-1; i >=0 ; i--) {
            // 0没有对应的大写字母
            if (chars[i] == '0') {
                dp[i]=0;
            }
            if (chars[i] == '1') {
                dp[i] = dp[i+1];
                if (i + 1 < chars.length) {
                    dp[i] += dp[i+2] ;
                }
            }
            if (chars[i] == '2') {
                dp[i] = dp[i+1];
                // (index和index+1)作为单独的部分并且没有超过26，后续有多少种方法
                if (i + 1 < chars.length && (chars[i + 1] >= '0' && chars[i] <= '6')) {
                    dp[i] +=dp[i+2];
                }
            }
        }
        // 递归主函数返回的下标
        return dp[0];
    }

    public static void main(String[] args) {
        int resultNum = getResultNum("11111");
        int i = dpWay("11111");
        System.out.println(resultNum);
        System.out.println(i);
    }
}
