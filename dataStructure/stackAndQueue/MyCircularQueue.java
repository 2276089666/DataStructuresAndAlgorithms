package stackAndQueue;

/**
 * @Author ws
 * @Date 2021/4/4 21:20
 * @Version 1.0
 */
public class MyCircularQueue {
    private int[] arr;
    private int rear;
    private int front;
    private int size;
    private final int limit;

    public MyCircularQueue(int l) {
        arr = new int[l];
        rear = 0;
        front = 0;
        size = 0;
        limit = l;
    }

    public void push(int value) {
        if (size == limit) {
            throw new RuntimeException("栈满了，不能再加了");
        }
        size++;
        arr[rear] = value;
        rear = nextIndex(rear);
    }

    public int pop() {
        if (size == 0) {
            throw new RuntimeException("栈空了，不能再拿了");
        }
        size--;
        int ans = arr[front];
        front = nextIndex(front);
        return ans;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 实现队首队尾指针循环
    private int nextIndex(int i) {
        return i < limit - 1 ? i + 1 : 0;
    }

    public static void main(String[] args) {
        MyCircularQueue myCircularQueue = new MyCircularQueue(5);
        myCircularQueue.push(5);
        myCircularQueue.push(6);
        myCircularQueue.push(7);
        myCircularQueue.push(8);
        myCircularQueue.push(9);
        int pop = myCircularQueue.pop();
        System.out.println(pop);
        myCircularQueue.push(10);
    }
}
