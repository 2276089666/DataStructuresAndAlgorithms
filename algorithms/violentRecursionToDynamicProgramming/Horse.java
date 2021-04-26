package violentRecursionToDynamicProgramming;

/**
 * @Author ws
 * @Date 2021/4/26 14:48
 * @Version 1.0
 */
public class Horse {
    /**
     * 请同学们自行搜索或者想象一个象棋的棋盘，
     * 然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
     * 那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
     * 给你三个 参数 x，y，k
     * 返回“马”从(0,0)位置出发，必须走k步
     * 最后落在(x,y)上的方法数有多少种?
     */

    /**
     * @param x
     * @param y
     * @param k 还剩多少步要走
     * @return
     */
    public static int getWay(int x, int y, int k) {
        // 不动
        if (k == 0) {
            return x == 0 && y == 0 ? 1 : 0;
        }
        // 越界,跳出了棋盘
        if (x < 0 || x > 8 || y < 0 || y > 9) {
            return 0;
        }
        // 我们发现以(x,y)为中心的点,周围只有8个点可以跳一步来到我们的(x,y)
        // 所以当前步数就是8个点走k-1步之和
        return getWay(x - 1, y - 2, k - 1) +
                getWay(x - 2, y - 1, k - 1) +
                getWay(x + 1, y - 2, k - 1) +
                getWay(x + 2, y - 1, k - 1) +
                getWay(x + 2, y + 1, k - 1) +
                getWay(x + 1, y + 2, k - 1) +
                getWay(x - 1, y + 2, k - 1) +
                getWay(x - 2, y + 1, k - 1);
    }

    // 动态规划
    public static int dpWay(int x, int y, int k) {
        // 发现三个参数都在变化,dp表变成三维
        int[][][] dp = new int[9][10][k + 1];
        // baseCase 1
        dp[0][0][0] = 1;
        // 当k==0时,出发点(0,0)外的所有位置都不行,也就是三维dp的第一层的其他dp[x][y][0]=0,初始化时已经赋值
        // 发现上一层的值依赖下一层,我们从下往上给dp赋值
        for (int level = 1; level <= k; level++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 10; j++) {
                    dp[i][j][level] = baseCase2(i - 1, j - 2, level - 1, dp) +
                            baseCase2(i - 2, j - 1, level - 1, dp) +
                            baseCase2(i + 1, j - 2, level - 1, dp) +
                            baseCase2(i + 2, j - 1, level - 1, dp) +
                            baseCase2(i + 2, j + 1, level - 1, dp) +
                            baseCase2(i + 1, j + 2, level - 1, dp) +
                            baseCase2(i - 1, j + 2, level - 1, dp) +
                            baseCase2(i - 2, j + 1, level - 1, dp);
                }
            }
        }
        return dp[x][y][k];
    }

    // 我们发现每个子过程都需要经过baseCase2的判断以防越界,为了避免冗余
    public static int baseCase2(int x, int y, int k, int[][][] dp) {
        // 越界,跳出了棋盘
        if (x < 0 || x > 8 || y < 0 || y > 9) {
            return 0;
        }
        return dp[x][y][k];
    }

    public static void main(String[] args) {
        int way = getWay(6, 8, 10);
        System.out.println(way);
        int way2 = dpWay(6, 8, 10);
        System.out.println(way2);
    }
}
