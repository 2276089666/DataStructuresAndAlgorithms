package interview;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * @Author ws
 * @Date 2021/5/22 14:18
 */
public class 不超过自身能力最大报酬 {
    /**
     * 每种工作有难度和报酬，规定如下
     * class Job {
     * public int money;// 该工作的报酬
     * public int hard; // 该工作的难度
     * }
     * 给定一个Job类型的数组jobarr，表示所有岗位，每个岗位都可以提供任意份工作
     * 选工作的标准是在难度不超过自身能力值的情况下，选择报酬最高的岗位
     * 给定一个int类型的数组arr，表示所有人的能力
     * 返回int类型的数组，表示每个人按照标准选工作后所能获得的最高报酬
     */

    public static class Job {
        public int money;
        public int hard;

        public Job(int money, int hard) {
            this.money = money;
            this.hard = hard;
        }
    }

    public static class JobComparator implements Comparator<Job> {
        @Override
        public int compare(Job o1, Job o2) {
            return o1.hard != o2.hard ? (o1.hard - o2.hard) : (o2.money - o1.money);
        }
    }

    public static int[] getMoneys(Job[] job, int[] ability) {
        Arrays.sort(job, new JobComparator());
        // 难度为key的工作，最优钱数是多少，有序表
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(job[0].hard, job[0].money);
        Job pre = job[0]; // pre 之前组的组长
        for (int i = 1; i < job.length; i++) {
            // 已经排过序,去掉hard相同的但是money下降的
            // 去掉hard上升的但是money下降的
            if (job[i].hard != pre.hard && job[i].money > pre.money) {
                pre = job[i];
                map.put(pre.hard, pre.money);
            }
        }
        int[] ans = new int[ability.length];
        for (int i = 0; i < ability.length; i++) {
            // <= num 离num最近的key
            Integer key = map.floorKey(ability[i]);
            ans[i] = key != null ? map.get(key) : 0;
        }
        return ans;
    }

    public static void main(String[] args) {
        Job[] jobs = {new Job(3, 2), new Job(4, 3), new Job(5, 5), new Job(2, 3)};
        int[] moneys = getMoneys(jobs, new int[]{5, 2, 3, 2});
        for (int money : moneys) {
            System.out.print(money+",");
        }
    }
}
