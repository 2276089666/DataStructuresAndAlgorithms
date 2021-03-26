package stackAndQueue;

import java.util.Stack;

/**
 * @Author ws
 * @Date 2021/3/26 16:27
 * @Version 1.0
 */
public class P1 {
    public static class MyStack{
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;
        public MyStack (){
            this.stackData=new Stack();
            this.stackMin=new Stack();
        }
        public void push(Integer newNum){
            if (newNum==null){
                return;
            }
            this.stackData.push(newNum);
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(newNum);
            }else {
                if (this.stackMin.peek()>newNum){
                    this.stackMin.push(newNum);
                }
            }
        }

        public Integer pop(){
            if (this.stackData.isEmpty()){
                throw new RuntimeException("Your stack is empty!");
            }
            int value=this.stackData.pop();
            if (this.stackMin.peek()==value){
                this.stackMin.pop();
            }
            return value;
        }

        public Integer getMin(){
            if (this.stackMin.isEmpty()){
                throw new RuntimeException("Your stack is empty!");
            }
            return this.stackMin.peek();
        }
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(8);
        myStack.push(45);
        myStack.push(-5);
        myStack.push(5);
        Integer min = myStack.getMin();
        System.out.println(min);
        myStack.pop();
        myStack.pop();
        Integer min1 = myStack.getMin();
        System.out.println(min1);
    }
}
