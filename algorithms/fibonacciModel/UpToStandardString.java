package fibonacciModel;

/**
 * @Author ws
 * @Date 2021/4/26 22:40
 * @Version 1.0
 */
public class UpToStandardString {
    /**
     * 给定一个数N，想象只由0和1两种字符，组成的所有长度为N的字符串
     *
     * 如果某个字符串,任何0字符的左边都有1紧挨着,认为这个字符串达标
     *
     * 返回有多少达标的字符串
     */

    public static int getNums1(int N){
        if (N<1){
            return 0;
        }
        if (N==1||N==2){
            return N;
        }
        return getNums1(N-1)+getNums1(N-2);
    }

    public static int getNums2(int N){
        if (N<1){
            return 0;
        }
        if (N==1||N==2){
            return N;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] result = FibonacciSequenceModel.matrixPower(base, N - 2);
        return 2 * result[0][0] + result[1][0];
    }


    public static void main(String[] args) {
        int nums = getNums1(3);
        System.out.println(nums);
        int nums2 = getNums2(3);
        System.out.println(nums2);
    }
}
