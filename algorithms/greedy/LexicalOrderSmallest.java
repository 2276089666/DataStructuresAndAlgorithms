package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

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

    // 暴力递归，把所有的情况列出来
    public static String lowestString1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        ArrayList<String> all = new ArrayList<>();
        HashSet<Integer> use = new HashSet<>();
        process(strs, use, "", all);
        String lowest = all.get(0);
        for (int i = 1; i < all.size(); i++) {
            if (all.get(i).compareTo(lowest) < 0) {
                lowest = all.get(i);
            }
        }
        return lowest;
    }

    public static void process(String[] strs, HashSet<Integer> use, String path, ArrayList<String> all) {
        if (use.size() == strs.length) {
            all.add(path);
        } else {
            for (int i = 0; i < strs.length; i++) {
                if (!use.contains(i)) {
                    use.add(i);
                    process(strs, use, path + strs[i], all);
                    use.remove(i);
                }
            }
        }
    }


    public static void main(String[] args) {
        String[] strs =new String[]{"ac","b","ba"};
        String string = lowestString(strs);
        System.out.println(string);

        String s = lowestString1(strs);
        System.out.println(s);
    }
}
