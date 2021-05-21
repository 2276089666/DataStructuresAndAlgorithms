package interview;

/**
 * @Author ws
 * @Date 2021/5/20 13:49
 */
public class 均匀打包问题 {
    /**
     * 有n个打包机器从左到右一字排开，上方有一个自动装置会抓取一批放物品到每个打 包机上，放到每个机器上的这些物品数量有多有少，
     * 由于物品数量不相同，需要工人将每个机器上的物品进行移动从而到达物品数量相等才能打包。每个物品重量太大、 每次只能搬一个物品进行移动，
     * 为了省力，只在相邻的机器上移动。请计算在搬动最小轮数的前提下，使每个机器上的物品数量相等。如果不能使每个机器上的物品相同， 返回-1。
     * 例如[1,0,5]表示有3个机器，每个机器上分别有1、0、5个物品，经过这些轮后:
     * 第一轮:1    0 <- 5 => 1 1 4 第二轮:1 <- 1 <- 4 => 2 1 3 第三轮:2    1 <- 3 => 2 2 2
     * 移动了3轮，每个机器上的物品相等，所以返回3
     * 例如[2,2,3]表示有3个机器，每个机器上分别有2、2、3个物品， 这些物品不管怎么移动，都不能使三个机器上物品数量相等，返回-1
     */

    public static int getNum(int[] arr){
        int total=0;
        for (int i = 0; i < arr.length; i++) {
            total+=arr[i];
        }
        if (total%arr.length!=0){
            return -1;
        }
        int avg = total / arr.length;
        int leftSum = 0;
        int ans = 0;
        // 每个位置都求各自的
        for (int i = 0; i < arr.length; i++) {
            // i号机器，是中间机器，左(0~i-1) i 右(i+1~N-1)
            // 负 需要输入     正需要输出
            int leftRest = leftSum - i * avg; // a-b
            // 负 需要输入     正需要输出
            // c - d
            int rightRest =  (total - leftSum - arr[i]) -  (arr.length - i - 1) * avg;
            // arr[i]太大,要向两边匀
            if (leftRest < 0 && rightRest < 0) {
                ans = Math.max(ans, Math.abs(leftRest) + Math.abs(rightRest));
            } else {
                ans = Math.max(ans, Math.max(Math.abs(leftRest), Math.abs(rightRest)));
            }
            leftSum += arr[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 3, 1, 0, 1}; // [4,2,3,1,1,1] [3,2,3,1,1,2] [2,2,3,1,2,2] [2,2,2,2,2,2]
        int num = getNum(arr);
        System.out.println(num);
    }
}
