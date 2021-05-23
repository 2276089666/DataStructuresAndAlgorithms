package interview;

/**
 * @Author ws
 * @Date 2021/5/22 15:56
 */
public class 背包问题 {
    /**
     * 背包容量为w
     * 一共有n袋零食, 第i袋零食体积为v[i]
     * 总体积不超过背包容量的情况下，
     * 一共有多少种零食放法？(总体积为0也算一种放法)。
     */

    // 暴力递归
    public static int getWays(int[] arr, int w) {
        if (arr == null || arr.length == 0 || w <= 0) {
            return -1;
        }
        return process(arr, 0, w);
    }

    private static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        // rest>=0 并且所有的零食都尝试过
        if (index == arr.length) {
            return 1;
        }
        int current = process(arr, index + 1, rest - arr[index]);
        int noCurrent = process(arr, index + 1, rest);
        return noCurrent + (current == -1 ? 0 : current);
    }

    // 动态规划
    public static int getWays2(int[] arr, int w) {
        if (arr == null || arr.length == 0 || w <= 0) {
            return -1;
        }
        int[][] dp = new int[arr.length + 1][w + 1];
        for (int i = 0; i < dp[0].length; i++) {
            dp[dp.length-1][i] = 1;
        }
        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = dp[i + 1][j] + (j - arr[i] >= 0 ? dp[i + 1][j - arr[i]] : 0);
            }
        }
        return dp[0][w];
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 7};
        int ways = getWays(arr, 9);
        System.out.println(ways);
        int ways2 = getWays2(arr, 9);
        System.out.println(ways2);
    }
}
