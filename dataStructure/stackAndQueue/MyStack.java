package stackAndQueue;

import stackAndQueue.common.DoubleEndsQueue;

/**
 * @Author ws
 * @Date 2021/4/4 20:39
 * @Version 1.0
 */
public class MyStack<T> {
   private DoubleEndsQueue<T> queue;

    public MyStack() {
        this.queue=new DoubleEndsQueue<>();
    }

    public void push(T value){
        queue.addFromHead(value);
    }
    public T pop(){
       return queue.popFromHead();
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }
}
