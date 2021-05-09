package byMeter;

/**
 * @Author ws
 * @Date 2021/5/9 14:52
 * @Version 1.0
 */
public class SumOfConsecutivePositiveNumbers {
    /**
     * 定义一种数：可以表示成若干（数量>1）连续正数和的数
     * 比如:
     * 5 = 2+3，5就是这样的数
     * 12 = 3+4+5，12就是这样的数
     * 1不是这样的数，因为要求数量大于1个、连续正数和
     * 2 = 1 + 1，2也不是，因为等号右边不是连续正数
     * 给定一个参数N，返回是不是可以表示成若干连续正数和的数
     */

    // 暴力尝试从1开始累加
    public static boolean getResult(int n) {
        for (int i = 1; i <= n; i++) {
            int sum = i;
            for (int j = i + 1; j <= n; j++) {
                // 最小得i得向后跳一个
                if (sum + j > n) {
                    break;
                } else if (sum + j == n) {
                    return true;
                } else {
                    sum += j;
                }
            }
        }
        return false;
    }

    // 优化
    public static boolean getResult2(int n) {
        if (n==1){
            return false;
        }
        // 2得多少次方在二进制时只有最高位为1,我们减1就变成除了最高位为0其他位都为1,再和原数与操作,结果必为0
        return ((n-1)&n)==0?false:true;
    }


    public static void main(String[] args) {
        // 打表发现,除了1其他得在2得几次方时为false
        for (int i = 1; i < 50; i++) {
            boolean result = getResult(i);
            System.out.println(result);
        }

        boolean result = getResult2(5);
        boolean result2 = getResult2(8);
        System.out.println(result);
        System.out.println(result2);
    }
}
