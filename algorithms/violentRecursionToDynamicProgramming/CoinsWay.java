package violentRecursionToDynamicProgramming;

/**
 * @Author ws
 * @Date 2021/4/20 14:20
 * @Version 1.0
 */
public class CoinsWay {
    /**
     * 给定数组arr，arr中所有的值都为正数且不重复
     * 每个值代表一种面值的货币，每种面值的货币可以使用任意张
     * 再给定一个整数 aim，代表要找的钱数
     * 求组成 aim 的方法数
     *
     * [1,5,10,20,50,10]
     *  aim=5000
     */

    // 使用暴力递归去尝试
    public static int getCoinsWay(int[] arr,int aim){
        if (arr==null||arr.length==0||aim<=0){
            return 0;
        }
        return getWay(arr,aim,0);
    }

    private static int getWay(int[] arr, int rest, int index) {
        if (index==arr.length){
            return rest==0?1:0;
        }
        int result=0;
        // i为当前面值的钱的张数
        for (int i = 0; i*arr[index] <=rest ; i++) {
            result+=getWay(arr,rest-i*arr[index],index+1);
        }
        return result;
    }

    // 记忆化搜索
    protected static int getCoinsWay2(int[] arr,int aim){
        if (arr==null||arr.length==0||aim<=0){
            return 0;
        }
        int rest=aim+1;
        int index=arr.length+1;
        int[][] cache = new int[index][rest];
        for (int i = 0; i < index; i++) {
            for (int j = 0; j < rest; j++) {
                cache[i][j]=-1;
            }
        }
        return getWay2(arr,aim,0,cache);
    }

    private static int getWay2(int[] arr, int rest, int index, int[][] cache) {
        if (cache[index][rest] != -1) {
            return cache[index][rest];
        }
        if (index==arr.length){
            cache[index][rest]=rest==0?1:0;
            return cache[index][rest];
        }
        int result=0;
        // i为当前面值的钱的张数
        for (int i = 0; i*arr[index] <=rest ; i++) {
            result+=getWay(arr,rest-i*arr[index],index+1);
        }
        cache[index][rest]=result;
        return cache[index][rest];
    }


     // 粗略版动态规划,和记忆化搜索等效
    public static int getCoinsWay3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        // baseCase
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) { // 大顺序 从下往上
            for (int rest = 0; rest <= aim; rest++) {

                int result=0;
                for (int i = 0; i*arr[index] <=rest ; i++) {
                    result+=dp[index+1][rest-i*arr[index]];
                }
                dp[index][rest]=result;

            }
        }
        return dp[0][aim];
    }

    // 发现上面两种都存在枚举行为
    public static int getCoinsWay4(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) { // 大顺序 从下往上
            for (int rest = 0; rest <= aim; rest++) {
                // 在此我们省去遍历,此处省略枚举行为,就像大的阶乘依赖小的阶乘
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 10, 20, 50, 100};
        int aim=1000;
        int coinsWay = getCoinsWay(arr, aim);
        System.out.println(coinsWay);
        int coinsWay2 = getCoinsWay2(arr, aim);
        System.out.println(coinsWay2);
        int coinsWay3 = getCoinsWay3(arr, aim);
        System.out.println(coinsWay3);
        int coinsWay4 = getCoinsWay4(arr, aim);
        System.out.println(coinsWay4);
    }
}
