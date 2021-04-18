package violentRecursionToDynamicProgramming;

/**
 * @Author ws
 * @Date 2021/4/18 21:19
 * @Version 1.0
 */
public class RobotWalk {
    /**
     * 假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2
     * 开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
     * 如果机器人来到1位置，那么下一步只能往右来到2位置；
     * 如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
     * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
     * 规定机器人必须走 K 步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
     * 给定四个参数 N、M、K、P，返回方法数。
     */


    /**
     * @param N       总位置数
     * @param K       走多少步
     * @param current 当前位置
     * @param to      目标位置
     * @return 有多少种走法
     */
    public static int getNum(int N, int K, int current, int to) {
        if (N < 2 || K < 1 || current < 1 || current > N || to < 1 || to > N) {
            return 0;
        }
        return walk(N, K, current, to);
    }

    /**
     * @param N       总位置数
     * @param rest    还剩多少步
     * @param current 当前位置
     * @param to      目标位置
     * @return
     */
    private static int walk(int N, int rest, int current, int to) {
        // 如果步数消耗完,且正好走到了目标位置
        if (rest == 0) {
            return current == to ? 1 : 0;
        }
        // 当前在最左边的位置,下一步必然往右走
        if (current == 1) {
            return walk(N, rest - 1, 2, to);
        }
        // 当前在最右边的位置,下一步必然向左走
        if (current == N) {
            return walk(N, rest - 1, N - 1, to);
        }
        // 向左和向右是不同的走法,所以要都加上
        return walk(N, rest - 1, current + 1, to) + walk(N, rest - 1, current - 1, to);
    }



    // 动态规划
    public static int getNumByCache(int N, int K, int current, int to) {
        if (N < 2 || K < 1 || current < 1 || current > N || to < 1 || to > N) {
            return 0;
        }
        // 因为只有rest和current两个参数会一直变化,所以动态规划只需要创建二维数组去缓存结果,避免重复计算
        int[][] cache = new int[N][K];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < K; j++) {
                cache[i][j] = -1;
            }
        }
        return walkByCache(N, K, current, to, cache);
    }

    private static int walkByCache(int N, int rest, int current, int to, int[][] cache) {
        // 已经缓存过的,就是已经计算过的,我们直接拿缓存,避免重复计算
        if (cache[current][rest] != -1) {
            return cache[current][rest];
        }
        // 如果步数消耗完,且正好走到了目标位置
        if (rest == 0) {
            cache[current][rest] = current == to ? 1 : 0;
            return cache[current][rest];
        }
        // 当前在最左边的位置,下一步必然往右走
        if (current == 1) {
            cache[current][rest] = walkByCache(N, rest - 1, 2, to, cache);
            return cache[current][rest];
        }
        // 当前在最右边的位置,下一步必然向左走
        if (current == N) {
            cache[current][rest] = walkByCache(N, rest - 1, N - 1, to, cache);
            return cache[current][rest];
        }
        // 向左和向右是不同的走法,所以要都加上
        cache[current][rest] = walkByCache(N, rest - 1, current + 1, to, cache) + walkByCache(N, rest - 1, current - 1, to, cache);
        return cache[current][rest];
    }

}
