package stackAndQueue;

import java.util.Stack;

/**
 * @Author ws
 * @Date 2021/3/26 21:46
 * @Version 1.0
 */
public class P5 {
    public static class MyQueue{
        private Stack<Integer> pushStack;
        private Stack<Integer> popStack;
        public MyQueue(){
            this.pushStack=new Stack<>();
            this.popStack=new Stack<>();
        }
        public void add(int[] value){
            if (value==null){
                return;
            }
            for (int i = 0; i < value.length; i++) {
                this.pushStack.push(value[i]);
            }
            // 把pushStack栈的值弹出并压入到popStack栈
            pushToPop();
        }

        private void pushToPop() {
            // popStack必须为空
            if (this.popStack.isEmpty()){
                // pushStack必须一次把所有的值都给popStack
                while (!this.pushStack.isEmpty()){
                    this.popStack.push(this.pushStack.pop());
                }
            }
        }

        public Integer poll(){
            if (this.popStack.isEmpty()){
                throw new RuntimeException("Queue is empty!");
            }
            return this.popStack.pop();
        }

        public Integer peek(){
            if (this.popStack.isEmpty()){
                throw new RuntimeException("Queue is empty!");
            }
            return this.popStack.peek();
        }

    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.add(new int[]{1,8,7,4,0});
        Integer peek = myQueue.peek();
        System.out.println(peek);
        Integer poll = myQueue.poll();
        myQueue.poll();
        myQueue.poll();
        myQueue.poll();
        myQueue.poll();
        System.out.println(poll);
        myQueue.add(new int[]{100,110,99});
        Integer peek2 = myQueue.peek();
        System.out.println(peek2);
        Integer poll2 = myQueue.poll();
        System.out.println(poll2);
    }
}
