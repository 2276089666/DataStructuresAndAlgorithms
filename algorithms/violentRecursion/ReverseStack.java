package violentRecursion;

import java.util.Stack;

/**
 * @Author ws
 * @Date 2021/4/17 15:51
 * @Version 1.0
 */
public class ReverseStack {
    /**
     * 给你一个栈，请你逆序这个栈，
     * 不能申请额外的数据结构，
     * 只能使用递归函数。 如何实现?
     */

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = f(stack);
        reverse(stack);
        stack.push(i);
    }

    public static int f(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> test = new Stack<Integer>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverse(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }

    }
}
