package fibonacciModel;

/**
 * @Author ws
 * @Date 2021/4/26 20:25
 * @Version 1.0
 */
public class FibonacciSequenceModel {
    /**
     * 求斐波那契数列矩阵乘法的方法
     * 1）斐波那契数列的线性求解（O(N)）的方式非常好理解
     *
     * 2）同时利用线性代数，也可以改写出另一种表示
     *
     *  | F(N) , F(N-1) | = | F(2), F(1) |  *  某个二阶矩阵的N-2次方
     *
     * 3）求出这个二阶矩阵，进而最快求出这个二阶矩阵的N-2次方
     *
     *
     */
    /**
     * 类似斐波那契数列的递归优化
     * 如果某个递归，除了初始项之外，具有如下的形式
     * <p>
     * F(N) = C1 * F(N) + C2 * F(N-1) + … + Ck * F(N-k) ( C1…Ck 和k都是常数)
     * <p>
     * 并且这个递归的表达式是严格的、不随条件转移的
     * <p>
     * 那么都存在类似斐波那契数列的优化，时间复杂度都能优化成O(logN)
     */

    // 暴力递归  n为数列元素个数
    public static int method1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return method1(n - 1) + method1(n - 2);
    }

    // 线性求解
    public static int method2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int result = 0;
        int pre = 1;
        int prePre = 1;
        for (int i = 3; i <= n; i++) {
            result = pre + prePre;
            prePre = pre;
            pre = result;
        }
        return result;
    }

    // 利用线性代数优化  |F(N),F(N-1)|=|F(2),F(1)| * |1,1|^(N-2)
    //                                          |1,0|
    public static int method3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        // [ 1 ,1 ]
        // [ 1, 0 ]
        int[][] base = { { 1, 1 }, { 1, 0 } };
        int[][] res = matrixPower(base, n - 2);
        // 利用线代的知识我们发现,F(N)=F(2)*res[0][0]+F(1)*res[1][0]
        return res[0][0] + res[1][0];
    }

    /**
     *  求矩阵m的p次方
     * @param m   矩阵
     * @param p   矩阵的p次方
     * @return
     */
    public static int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        // res = 矩阵中的1
        int[][] tmp = m;// 矩阵1次方
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = muliMatrix(res, tmp);
            }
            tmp = muliMatrix(tmp, tmp);
        }
        return res;
    }

    // 计算两个矩阵的乘积
    public static int[][] muliMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int i = method1(10);
        System.out.println(i);
        int i2 = method2(10);
        System.out.println(i2);
        int i3 = method3(10);
        System.out.println(i3);
    }
}
