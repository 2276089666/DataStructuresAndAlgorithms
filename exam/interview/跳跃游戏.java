package interview;

/**
 * @Author ws
 * @Date 2021/6/1 19:03
 */
public class 跳跃游戏 {
    /**
     * 给出一组正整数arr，你从第0个数向最后一个数，
     * 每个数的值表示你从这个位置可以向右跳跃的最大长度
     * 计算如何以最少的跳跃次数跳到最后一个数。
     */

    public static int jump(int[] arr){
        if (arr==null||arr.length==0){
            return -1;
        }
        int jump=0;  // 跳了多少步
        int cur=0;   // 跳了多少步能达到的最远位置
        int next=0;  // 多跳一步能达到的最远位置
        for (int i = 0; i < arr.length; i++) {
            if (cur<i){
                jump++;
                cur=next;
            }
            next=Math.max(next,arr[i]+i);
        }
        return jump;
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 1, 1, 3, 1, 1, 5};
        int jump = jump(arr);
        System.out.println(jump);
    }
}
