package slidingWindowAndMonotonicStack.monotonousStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author ws
 * @Date 2021/4/25 13:44
 * @Version 1.0
 */
public class MonotonousStack {
    /**
     * 一种特别设计的栈结构，为了解决如下的问题：
     *
     * 给定一个可能含有重复值的数组arr，i位置的数一定存在如下两个信息
     * 1）arr[i]的左侧离i最近并且小于(或者大于)arr[i]的数在哪？
     * 2）arr[i]的右侧离i最近并且小于(或者大于)arr[i]的数在哪？
     * 如果想得到arr中所有位置的两个信息，怎么能让得到信息的过程尽量快。
     */

    /**
     * 无重复值的单调栈
     *
     * @param arr
     * @return 二维数组的第几行代表arr的第几个数的左侧最近小于值和右侧最近小于值
     */
    public static int[][] getNearLessNoRepeat(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[][] result = new int[arr.length][2];
        // 后面的值比前面的值大直接压入,否则生成结果
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                Integer popIndex = stack.pop();
                // -1代表向左没有比自己小的,或者向右没有比自己小的
                int lessIndex = stack.isEmpty() ? -1 : stack.peek();
                result[popIndex][0] = lessIndex;
                result[popIndex][1] = i;
            }
            stack.push(i);
        }
        // 数组遍历完了,栈里面的数都是从栈底到栈顶,由小到大
        while (!stack.isEmpty()) {
            Integer popIndex = stack.pop();
            int lessIndex = stack.isEmpty() ? -1 : stack.peek();
            result[popIndex][0] = lessIndex;
            result[popIndex][1] = -1;
        }
        return result;
    }

    /**
     * 有重复值的单调栈
     * @param arr
     * @return
     */
    public static int[][] getNearLess(int[] arr){
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[][] result = new int[arr.length][2];
        // 重复值的下标在同一个ArrayList里面
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty()&&arr[stack.peek().get(0)]>arr[i]){
                List<Integer> popList = stack.pop();
                int lessIndex=stack.isEmpty()?-1:stack.peek().get(stack.peek().size()-1);
                for (Integer popIndex : popList) {
                    result[popIndex][0]=lessIndex;
                    result[popIndex][1]=i;
                }
            }
            // 相等的、比你小的
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(Integer.valueOf(i));
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> popIs = stack.pop();
            // 取位于下面位置的列表中，最晚加入的那个
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (Integer popi : popIs) {
                result[popi][0] = leftLessIndex;
                result[popi][1] = -1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 6, 3};
        int[][] nearLessNoRepeat = getNearLessNoRepeat(arr);
        for (int i = 0; i < nearLessNoRepeat.length; i++) {
            System.out.println("["+nearLessNoRepeat[i][0]+","+nearLessNoRepeat[i][1]+"]");
        }

        System.out.println();
        int[] arr2 = {1, 2, 4,6,4,2, 3};
        int[][] nearLess = getNearLess(arr2);
        for (int i = 0; i < nearLess.length; i++) {
            System.out.println("["+nearLess[i][0]+","+nearLess[i][1]+"]");
        }
    }
}
