package byMeter;

/**
 * @Author ws
 * @Date 2021/5/9 13:53
 * @Version 1.0
 */
public class EatGrass {
    /**
     * 打表找规律
     * 1）某个面试题，输入参数类型简单，并且只有一个实际参数
     * 2）要求的返回值类型也简单，并且只有一个
     * 3）用暴力方法，把输入参数对应的返回值，打印出来看看，进而优化code
     * <p>
     * <p>
     * <p>
     * 给定一个正整数N，表示有N份青草统一堆放在仓库里
     * 有一只牛和一只羊，牛先吃，羊后吃，它俩轮流吃草
     * 不管是牛还是羊，每一轮能吃的草量必须是：
     * 1，4，16，64…(4的某次方)
     * 谁最先把草吃完，谁获胜
     * 假设牛和羊都绝顶聪明，都想赢，都会做出理性的决定
     * 根据唯一的参数N，返回谁会赢
     */

    // 尝试暴力解
    public static String getWinner(int n) {
        if (n < 0) {
            return "参数异常";
        }
        return process(n);
    }

    private static String process(int n) {
        // 0  1  2  3  4
        // 后 先 后  先 先
        // baseCase
        if (n < 5) {
            return (n == 0 || n == 2) ? "后手赢" : "先手赢";
        }
        int count=1;  // 吃的草数
        while (count<=n){
            if (process(n-count).equals("后手赢")){
                return "先手赢";
            }
            // 避免count越界,比整型值还大,导致value变为负数,成为死循环
            if (count>n/4){
                break;
            }
            count=count*4;
        }
        return "后手赢";
    }

    // 利用打表发现的规律优化
    public static String getWinner2(int n){
        if (n < 0) {
            return "参数异常";
        }
        int x=n%5;
        return  (x==0||x==2)?"后手赢":"先手赢";
    }

    public static void main(String[] args) {
        // 打表发现,每5个一循环
        for (int i = 0; i < 50; i++) {
            String process = process(i);
            System.out.println(process);
        }

        String winner2 = getWinner2(48);
        System.out.println("method2:\t"+winner2);
        String winner22 = getWinner2(49);
        System.out.println("method2:\t"+winner22);
    }
}
