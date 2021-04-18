package violentRecursion;


/**
 * @Author ws
 * @Date 2021/4/18 19:12
 * @Version 1.0
 */
public class NQueens {
    /**
     * N皇后问题是指在N*N的棋盘上要摆N个皇后，
     * 要求任何两个皇后不同行、不同列， 也不在同一条斜线上
     * 给定一个整数n，返回n皇后的摆法有多少种。  n=1，返回1
     * n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0
     * n=8，返回92
     */

    public static int getNums(int N) {
        if (N < 1) {
            return 0;
        }
        int[] record = new int[N];   // 记录0~N行的第几列
        return process(record, N, 0);
    }

    // 潜台词：record[0..i-1]的皇后，任何两个皇后一定都不共行、不共列，不共斜线
    // 目前来到了第i行
    // record[0..i-1]表示之前的行，放了的皇后位置
    // n代表整体一共有多少行
    // 返回值是，摆完所有的皇后，合理的摆法有多少种
    private static int process(int[] record, int N, int i) {
        // 完成一种摆法
        if (i == N) {
            return 1;
        }
        int result = 0;
        // 第i行逐个列遍历
        for (int j = 0; j < N; j++) {
            // 如果第i行的某个列符合要求,记录i行的j列已经放了东西,递归看后面的行
            if (isValid(record, i, j)) {
                record[i] = j;
                result += process(record, N, i + 1);
            }
        }
        return result;
    }

    private static boolean isValid(int[] record, int row, int column) {
        for (int i = 0; i < row; i++) {
            //record[i]==column 之前的行的列和当前行的列冲突
            //Math.abs(record[i]-column)==Math.abs(row-i) 如果处于同一条斜线上,两个点可以组成一个正方形,宽和高必定相等
            if (record[i] == column || Math.abs(record[i] - column) == Math.abs(row - i)) {
                return false;
            }
        }
        return true;
    }

    // 请不要超过32皇后问题
    public static int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    // colLim 列的限制，1的位置不能放皇后，0的位置可以
    // leftDiaLim 左斜线的限制，1的位置不能放皇后，0的位置可以
    // rightDiaLim 右斜线的限制，1的位置不能放皇后，0的位置可以
    public static int process2(int limit,
                               int colLim,
                               int leftDiaLim,
                               int rightDiaLim) {
        if (colLim == limit) { // base case
            return 1;
        }
        // 所有候选皇后的位置，都在pos上
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process2(limit,
                    colLim | mostRightOne,
                    (leftDiaLim | mostRightOne) << 1,
                    (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int nums = getNums(1);
        System.out.println(nums);
    }
}
