package greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author ws
 * @Date 2021/4/13 15:28
 * @Version 1.0
 */


public class LexicalOrderSmallest {
    /**
     * 给定一个由字符串组成的数组strs，
     * 必须把所有的字符串拼接起来，
     * 返回所有可能的拼接结果中，字典序最小的结果
     */
    public static String lowestString(String[] strs){
        if (strs.length==0||strs==null){
            return null;
        }
        Arrays.sort(strs,new MyComparator());
        String res="";
        for (int i = 0; i < strs.length; i++) {
            res+=strs[i];
        }
        return res;
    }

    public static class  MyComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return (o1+o2).compareTo(o2+o1);
        }
    }

    public static void main(String[] args) {
        String[] strs =new String[]{"ac","b","ba"};
        String string = lowestString(strs);
        System.out.println(string);
    }
}
