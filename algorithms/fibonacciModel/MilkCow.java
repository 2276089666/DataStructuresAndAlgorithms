package fibonacciModel;

/**
 * @Author ws
 * @Date 2021/4/26 22:23
 * @Version 1.0
 */
public class MilkCow {
    /**
     * 第一年农场有1只成熟的母牛A，往后的每年：
     *
     * 1）每一只成熟的母牛都会生一只母牛
     *
     * 2）每一只新出生的母牛都在出生的第三年成熟
     *
     * 3）每一只母牛永远不会死
     *
     * 返回N年后牛的数量
     */

    // 暴力递归
    public static int getNum1(int N){
        if (N<1){
            return 0;
        }
        if (N==1||N==2||N==3){
            return N;
        }
        // getNum(N-3)新生的牛,getNum(N-1)上一年活下来的牛
        return getNum1(N-3)+getNum1(N-1);
    }

    // 利用矩阵
    public static int getNum2(int N){
        if (N<1){
            return 0;
        }
        if (N==1||N==2||N==3){
            return N;
        }
        // 利用消元可以推出来
        int[][] base={{ 1, 1, 0 }, { 0, 0, 1 }, { 1, 0, 0 }};
        int[][] result = FibonacciSequenceModel.matrixPower(base, N - 3);
        // |F(N),F(N-1),F(N-2)|=|F(3),F(2),F(1)|*|base|
        return 3*result[0][0]+2*result[1][0]+result[0][0];
    }

    public static void main(String[] args) {
        int num = getNum1(5);
        System.out.println(num);
        int num2 = getNum2(5);
        System.out.println(num2);
    }
}
