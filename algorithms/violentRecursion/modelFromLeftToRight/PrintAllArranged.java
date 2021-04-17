package violentRecursion.modelFromLeftToRight;


import java.util.ArrayList;
import java.util.List;

/**
 * @Author ws
 * @Date 2021/4/17 20:11
 * @Version 1.0
 */
public class PrintAllArranged {
    /**
     * 打印一个字符串的全部排列
     * abc
     * abc acb
     * bac bca
     * cab cba
     */

    public static List<String> printAllArranged(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        char[] chars = str.toCharArray();
        ArrayList<String> result = new ArrayList<>();
        process(chars, 0, result);
        for (String s : result) {
            System.out.println(s);
        }
        return result;
    }

    private static void process(char[] str, int index, ArrayList<String> result) {
        if (index == str.length) {
            result.add(String.valueOf(str));
        }
        for (int i = index; i < str.length; i++) {
            swap(str, i, index);
            process(str, index + 1, result);
            // 恢复现场
            swap(str, i, index);
        }
    }

    public static void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }

    /**
     * 字符串排列去重
     *
     * @param str
     * @return
     */
    public static ArrayList<String> permutationNoRepeat(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return res;
        }
        char[] chs = str.toCharArray();
        process2(chs, 0, res);
        for (String re : res) {
            System.out.println(re);
        }
        return res;
    }

    public static void process2(char[] str, int i, ArrayList<String> res) {
        if (i == str.length) {
            res.add(String.valueOf(str));
        }
        boolean[] visit = new boolean[26]; // visit[0 1 .. 25]
        // 分支限界
        for (int j = i; j < str.length; j++) {
            if (!visit[str[j] - 'a']) {
                visit[str[j] - 'a'] = true;
                swap(str, i, j);
                process2(str, i + 1, res);
                swap(str, i, j);
            }
        }
    }


    public static void main(String[] args) {
        printAllArranged("abb");
        System.out.println("去重后");
        permutationNoRepeat("abb");
    }
}
