package stackAndQueue;

import stackAndQueue.common.DoubleEndsQueue;

/**
 * @Author ws
 * @Date 2021/4/4 20:56
 * @Version 1.0
 */
public class MyQueue<T> {
    private DoubleEndsQueue<T> queue;

    public MyQueue() {
        this.queue=new DoubleEndsQueue<>();
    }

    public void push(T value){
        queue.addFromHead(value);
    }

    public T pool(){
        return queue.popFromBottom();
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }
}
