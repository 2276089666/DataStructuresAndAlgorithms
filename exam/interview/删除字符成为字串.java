package interview;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @Author ws
 * @Date 2021/5/27 22:31
 */
public class 删除字符成为字串 {
    /**
     * 给定两个字符串s1和s2，问s2最少删除多少字符可以成为s1的子串？
     * 比如 s1 = "abcde"，s2 = "axbc"
     * 返回1。s2删掉'x'就是s1的子串了。
     */

    // 在s2长度不是很长时，我们生成的子序列集合也不大 2^（s2.length）-1 ，此方案可行
    public static int getDelCount(String s1,String s2){
        if (s1==null||s2==null){
            return -1;
        }
        char[] str2 = s2.toCharArray();
        ArrayList<String> list = new ArrayList<>();
        // 拿s2的所有子序列
        getSubsequence(str2,"",list,0);
        // 按长度大到小排序
        list.sort(new LenComp());
        for (String str : list) {
            // 利用kmp判断
            if (s1.indexOf(str)!=-1){
                return s2.length()-str.length();
            }
        }
        return s2.length();
    }

    private static void getSubsequence(char[] str2, String path, ArrayList<String> list, int index) {
        if (index==str2.length){
            list.add(path);
            return;
        }
        getSubsequence(str2,path+str2[index],list,index+1);
        getSubsequence(str2,path,list,index+1);
    }

    public static class LenComp implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o2.length() - o1.length();
        }

    }

    public static void main(String[] args) {
        String s1="abcde";
        String s2="axbc";
        int delCount = getDelCount(s1, s2);
        System.out.println(delCount);
    }
}
