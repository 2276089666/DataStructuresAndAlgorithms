package interview;

/**
 * @Author ws
 * @Date 2021/5/22 18:16
 */
public class 走二维矩阵求最小路径和问题 {
    /**
     * 给定一个二维数组matrix，其中每个数都是正数，要求从左上到右下
     * 每一步只能向右或者向下，沿途经过的数字要累加起来
     * 最后请返回最小的路径和
     *
     * 动态规划的空间压缩技巧！
     */

    public static int getMin(int[][] matrix){
        int m=matrix.length;
        int n=matrix[0].length;
        int[][] dp = new int[m][n];
        dp[0][0]=matrix[0][0];
        // 发现每个值都依赖左上两个位置,最左边一列可以只依靠上的位置就可以求
        for (int i = 1; i < m; i++) {
            dp[i][0]=dp[i-1][0]+matrix[i][0];
        }
        // 同上
        for (int i = 1; i < n; i++) {
            dp[0][i]=dp[0][i-1]+matrix[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 记住还要最小
                dp[i][j]=Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
            }
        }
        return dp[m-1][n-1];
    }


    // 压缩矩阵
    public static int getMin2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int[] dp = new int[m[0].length];
        int N = m.length;
        int M = m[0].length;
        dp[0] = m[0][0];
        // 第一行直接赋值
        for(int col = 1; col <M; col++) {
            dp[col] = dp[col-1] + m[0][col];
        }
        for(int row = 1; row < N; row++) {
            // 下一行的最左边直接赋值
            dp[0] = dp[0] + m[row][0];
            for(int col = 1;col <M; col++ ) {
                dp[col] = Math.min(dp[col-1], dp[col]) + m[row][col];
            }
        }
        return dp[M-1];
    }

    public static void main(String[] args) {
        int[][] m = {
                { 1, 3, 5, 9 },
                { 8, 1, 3, 4 },
                { 5, 0, 6, 1 },
                { 8, 8, 4, 0 } };
        int min = getMin(m);     // 1->3->1->0->6->1->0
        System.out.println(min);
        int min2 = getMin2(m);
        System.out.println(min2);
    }
}
