package stackAndQueue;

/**
 * @Author ws
 * @Date 2021/4/4 20:59
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        MyStack<Integer> integerMyStack = new MyStack<>();
        integerMyStack.push(5);
        integerMyStack.push(4);
        integerMyStack.push(6);
        Integer pop = integerMyStack.pop();
        System.out.println(pop);

        MyQueue<Integer> integerMyQueue = new MyQueue<>();
        integerMyQueue.push(5);
        integerMyQueue.push(4);
        integerMyQueue.push(6);
        Integer pool = integerMyQueue.pool();
        System.out.println(pool);
    }
}
