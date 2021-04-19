package violentRecursion.modelFromLeftToRight;

/**
 * @Author ws
 * @Date 2021/4/18 12:38
 * @Version 1.0
 */
public class MaximumValueOfBackpack {
    /**
     * 给定两个长度都为N的数组weights和values，
     * weights[i]和values[i]分别代表 i号物品的重量和价值。
     * 给定一个正数bag，表示一个载重bag的袋子，
     * 你装的物品不能超过这个重量。
     * 返回你能装下最多的价值是多少
     */

    public static int getMaximumValueOf(int[] weight, int[] values, int bag) {
        return process(weight, values, bag, 0, 0);
    }

    /**
     * @param weight
     * @param values
     * @param bag        背包载重
     * @param index      从左往右尝试的下标
     * @param hasUsedBag 已经使用了的载重
     * @return
     */
    private static int process(int[] weight, int[] values, int bag, int index, int hasUsedBag) {
        // 重量超了 baseCase
        if (hasUsedBag > bag) {
            return -1;
        }
        // baseCase
        if (index == weight.length) {
            return 0;
        }

        // 当前index下标的weight和values不要，看后面的要不要
        int p1 = process(weight, values, bag, index + 1, hasUsedBag);
        // 当前index下标的weight和values要，看后面的要不要
        int p2Next = process(weight, values, bag, index + 1, hasUsedBag + weight[index]);
        int p2 = -1;
        if (p2Next != -1) {
            p2 = values[index] + p2Next;
        }
        return Math.max(p1, p2);
    }

    /**
     * 第二种从左往右的尝试模型
     */
    public static int getMaximumValueOf2(int[] weight, int[] values, int bag) {
        return process2(weight, values, bag, 0);
    }

    /**
     * @param weight
     * @param values
     * @param rest   背包还剩多少载重
     * @param index
     * @return
     */
    private static int process2(int[] weight, int[] values, int rest, int index) {
        // baseCase
        if (rest < 0) {
            return 0;
        }
        // baseCase
        if (index == weight.length) {
            return 0;
        }
        // index的重量不要
        int p1 = process2(weight, values, rest, index + 1);
        // index的重量要
        int p2 = -1;
        if (rest >= weight[index]) {
            p2 = values[index] + process2(weight, values, rest - weight[index], index + 1);
        }
        return Math.max(p1, p2);
    }

    /**
     * 动态规划版本
     * @param weight
     * @param values
     * @param bag
     * @return
     */
    public static int dpWay(int[] weight, int[] values, int bag){
        // 发现rest和index两个参数为可变,我们创建一个二维数组变为动态规划的结果缓存,避免重复计算
        int N = weight.length;
        // 上面的暴力递归index == weight.length,说明index达到了length所以我们的N+1,不然下标越界
        int[][] dp = new int[N+1][bag+1];
        // 从上面的暴力递归的第二个baseCase,我们可以推断出该二维动态规划数组是从下往上填的,上面的行依赖下面的行
        // 默认dp[N][]  最后一行全为0
        for (int index = N-1; index >=0 ; index--) {
            for (int rest = 1; rest <bag+1 ; rest++) {
                dp[index][rest]=dp[index+1][rest];
                if (rest>=weight[index]){
                    dp[index][rest] = Math.max(dp[index][rest], values[index] + dp[index + 1][rest - weight[index]]);
                }
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weight = {1, 2, 3};
        int[] values = {1, 2, 3};
        int bag=4;
        int maximumValueOf = getMaximumValueOf(weight, values, bag);
        int maximumValueOf2 = getMaximumValueOf2(weight, values, bag);
        System.out.println(maximumValueOf);
        System.out.println(maximumValueOf2);
        int i = dpWay(weight, values, bag);
        System.out.println(i);
    }
}
