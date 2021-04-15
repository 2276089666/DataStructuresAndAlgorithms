package greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author ws
 * @Date 2021/4/14 14:41
 * @Version 1.0
 */
public class MaximumProfit {
    /**
     * 输入: 正数数组costs、正数数组profits、正数K、正数M
     * costs[i]表示i号项目的花费
     * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
     * K表示你只能串行的最多做k个项目
     * M表示你初始的资金
     * 说明: 每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
     * 输出：你最后获得的最大钱数。
     */

    public static class Program{
        int cost;
        int profit;

        public Program(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    public static int getMaximumProfit(int[] costs,int[] profits,int K,int M){
        PriorityQueue<Program> costHeap = new PriorityQueue<>(new MyCostComparator());
        PriorityQueue<Program> profitHeap = new PriorityQueue<>(new MyProfitComparator());
        for (int i = 0; i < costs.length; i++) {
            costHeap.add(new Program(costs[i],profits[i]));
        }
        for (int i = 0; i < K; i++) {
            while (!costHeap.isEmpty()&&costHeap.peek().cost<=M){
                profitHeap.add(costHeap.poll());
            }
            // 启动资金太小了,做不了任何项目直接返回
            if (profitHeap.isEmpty()){
                return M;
            }
            M+=profitHeap.poll().profit;
        }
        return M;
    }

    public static class MyCostComparator implements Comparator<Program>{

        @Override
        public int compare(Program o1, Program o2) {
            return o1.cost-o2.cost;
        }
    }

    public static class MyProfitComparator implements Comparator<Program>{

        @Override
        public int compare(Program o1, Program o2) {
            return o2.profit-o1.profit;
        }
    }

    public static void main(String[] args) {
        int maximumProfit = getMaximumProfit(new int[]{1, 2, 3}, new int[]{1, 2, 3}, 2, 2);
        System.out.println(maximumProfit);
    }
}
