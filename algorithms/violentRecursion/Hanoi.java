package violentRecursion;

/**
 * @Author ws
 * @Date 2021/4/17 15:25
 * @Version 1.0
 */
public class Hanoi {
    /**
     * 打印n层汉诺塔从最左边移动到最右边的全部过程
     */

    /**
     * @param N 圆盘的层数
     */
    public static void printHanoi(int N) {
        if (N > 0) {
            recursionPrint(N, "left", "mid", "right");
        }
    }

    /**
     * @param n    层数
     * @param from 起始盘
     * @param help 帮助盘
     * @param to   目标盘
     */
    private static void recursionPrint(int n, String from, String help, String to) {
        if (n == 1) {
            System.out.println("move 1 from " + from + " to " + to);
        } else {
            // 将1~N-1层从from移到help
            recursionPrint(n - 1, from, to, help);
            // 将第N最底下层从form移到to
            System.out.println("move " + n + " from " + from + " to " + to);
            // 将1~N-1层从help移到to
            recursionPrint(n - 1, help, from, to);
        }
    }

    public static void main(String[] args) {
        printHanoi(3);
    }
}
