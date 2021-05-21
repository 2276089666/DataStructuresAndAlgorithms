package interview;

import java.util.Stack;

/**
 * @Author ws
 * @Date 2021/5/18 20:08
 * @Version 1.0
 */
public class 括号匹配 {
    /**
     * 括号有效配对是指：
     * 1）任何一个左括号都能找到和其正确配对的右括号
     * 2）任何一个右括号都能找到和其正确配对的左括号
     * 有效的：    (())  ()()   (()())  等
     * 无效的：     (()   )(     等
     * 问题一：怎么判断一个括号字符串有效？
     * 问题二：如果一个括号字符串无效，返回至少填几个字符能让其整体有效
     */

    // 问题一   使用栈去实现
    public static boolean getInEffect(String str){
        if (str==null||"".equals(str)){
            return false;
        }
        Stack<Character> stack = new Stack<>();
        char[] array = str.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (array[i]=='('){
                stack.push(array[i]);
            }
            if (array[i]==')'){
                if (!stack.isEmpty()){
                    stack.pop();
                }else {
                    return false;
                }

            }
        }
        return stack.isEmpty();
    }

    // 问题一  优化使用一个count变量
    public static boolean getInEffect2(String str){
        if (str==null||"".equals(str)){
            return false;
        }
        int count=0;
        char[] array = str.toCharArray();
        for (int i = 0; i < array.length; i++) {
           count+=array[i]=='('?1:-1;
           if (count<0){
               return false;
           }
        }
        return count==0;
    }

    // 问题二 还是用两个变量
    public static int getNum(String str){
        if (str==null||"".equals(str)){
            return -1;
        }
        int count=0;
        int need=0;
        char[] array = str.toCharArray();
        for (int i = 0; i < array.length; i++) {
            count+=array[i]=='('?1:-1;
            if (count<0){
                need++;
                count=0;
            }
        }
        return count+need;
    }

    public static void main(String[] args) {
        String str="(())()((()))";
        boolean inEffect = getInEffect(str);
        System.out.println(inEffect);
        boolean inEffect2 = getInEffect2(str);
        System.out.println(inEffect2);
        String str2="))((()())";
        int num = getNum(str2);
        System.out.println(num);
    }
}
