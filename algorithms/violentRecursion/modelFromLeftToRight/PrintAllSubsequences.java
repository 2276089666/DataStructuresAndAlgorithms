package violentRecursion.modelFromLeftToRight;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Author ws
 * @Date 2021/4/17 19:08
 * @Version 1.0
 */
public class PrintAllSubsequences {
    /**
     * 打印一个字符串的全部子序列
     * abcdc
     * a ab abc abcd abcdc
     * b bc bcd bcdc
     * c cd cdc
     * d dc
     * c
     */
    public static List<String> printAllSubsequences(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        char[] chars = str.toCharArray();
        String path = "";
        ArrayList<String> result = new ArrayList<>();
        process(chars, 0, path, result);
        for (String s : result) {
            System.out.println(s);
        }
        return result;
    }

    /**
     * @param str    字符数组
     * @param index  当前讨论的字符数组下标
     * @param path   当前字符串
     * @param result 收集结果的集合
     */
    private static void process(char[] str, int index, String path, ArrayList<String> result) {
        if (index == str.length) {
            result.add(path);
            return;
        }
        // 不要当前index下的str[]字符
        String no = path;
        process(str, index + 1, no, result);
        // 要index的字符
        String yes = path + str[index];
        process(str, index + 1, yes, result);
    }


    /**
     * 利用HashSet去重
     */
    public static List<String> subsNoRepeat(String s) {
        char[] str = s.toCharArray();
        String path = "";
        HashSet<String> set = new HashSet<>();
        process2(str, 0, set, path);
        List<String> ans = new ArrayList<>();
        for (String cur : set) {
            System.out.println(cur);
            ans.add(cur);
        }
        return ans;
    }

    public static void process2(char[] str, int index, HashSet<String> set, String path) {
        if (index == str.length) {
            set.add(path);
            return;
        }
        String no = path;
        process2(str, index + 1, set, no);
        String yes = path + str[index];
        process2(str, index + 1, set, yes);
    }


    public static void main(String[] args) {
        printAllSubsequences("abb");
        System.out.println("去重后:");
        subsNoRepeat("abb");
    }
}
