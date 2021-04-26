package fibonacciModel;

/**
 * @Author ws
 * @Date 2021/4/26 22:04
 * @Version 1.0
 */
public class ClimbTheStairs {
    /**
     * 一个人可以一次往上迈1个台阶，也可以迈2个台阶
     * <p>
     * 返回这个人迈上N级台阶的方法数
     */

    public static int getMethods1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return getMethods1(n - 1) + getMethods1(n - 2);
    }

    public static int getMethods2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] result = FibonacciSequenceModel.matrixPower(base, n - 2);
        return 2 * result[0][0] + result[1][0];
    }

    public static void main(String[] args) {
        int methods = getMethods1(3);
        System.out.println(methods);
        int methods2 = getMethods2(3);
        System.out.println(methods2);
    }
}
