package interview;

import java.util.*;

/**
 * @Author ws
 * @Date 2021/5/28 20:52
 */
public class 宽度和深度遍历的最短变换路径 {
    /**
     * 给定两个字符串，记为start和to，再给定一个字符串列表list，list中一定包含to list中没有重复字符串，所有的字符串都是小写的。
     * 规定: start每次只能改变一个字符，最终的目标是彻底变成to，但是每次变成的新字符串必须在list 中存在。
     * 请返回所有最短的变换路径。
     * 【举例】
     * start="abc",end="cab",list={"cab","acc","cbc","ccc","cac","cbb","aab","abb"}
     * 转换路径的方法有很多种，但所有最短的转换路径如下:
     * abc -> abb -> aab -> cab
     * abc -> abb -> cbb -> cab
     * abc -> cbc -> cac -> cab
     * abc -> cbc -> cbb -> cab
     */

    public static List<List<String>> findMinPath(String start, String end, List<String> list) {
        if (start.equals(end) || list.size() == 0) {
            return null;
        }
        list.add(start);
        // 拿到每个字符串的邻居
        Map<String, List<String>> neighbors = getNeighbors(list);
        // 每个list中的字符串和start的距离，默认邻居间的distance为1，每隔一个字符串distance+1
        Map<String, Integer> distances = getDistances(start, neighbors);
        ArrayList<List<String>> result = new ArrayList<>();
        // 方便
        LinkedList<String> path = new LinkedList<>();
        getShortestPaths(start,end,neighbors,distances,result,path);
        return result;
    }

    // 深度优先遍历
    private static void getShortestPaths(String cur, String end, Map<String, List<String>> neighbors, Map<String, Integer> distances, ArrayList<List<String>> result, LinkedList<String> path) {
        path.add(cur);
        if (end.equals(cur)){
            result.add(new LinkedList<String>(path));
        }else {
            for (String s : neighbors.get(cur)) {
                if (distances.get(s)==distances.get(cur)+1){
                    getShortestPaths(s,end,neighbors,distances,result,path);
                }
            }
        }
        path.pollLast();
    }

    // 利用广度优先遍历求
    private static Map<String, Integer> getDistances(String start, Map<String, List<String>> neighbors) {
        Queue<String> queue = new LinkedList<>();
        HashSet<String> set = new HashSet<>();
        HashMap<String, Integer> distances = new HashMap<>();
        queue.add(start);
        set.add(start);
        distances.put(start, 0);
        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (!neighbors.get(current).isEmpty()){
                for (String s : neighbors.get(current)) {
                    if (!set.contains(s)){
                        distances.put(s,distances.get(current)+1);
                        queue.add(s);
                        set.add(s);
                    }
                }
            }
        }
        return distances;
    }

    private static Map<String, List<String>> getNeighbors(List<String> list) {
        // 把list变为set
        HashSet<String> set = new HashSet<>(list);
        HashMap<String, List<String>> result = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            result.put(list.get(i), getNeighbor(list.get(i), set));
        }
        return result;
    }

    private static List<String> getNeighbor(String s, HashSet<String> set) {
        char[] str = s.toCharArray();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < str.length; i++) {
            for (char cur = 'a'; cur < 'z'; cur++) {
                if (str[i] != cur) {
                    char temp = str[i];
                    str[i] = cur;
                    if (set.contains(String.valueOf(str))) {
                        list.add(String.valueOf(str));
                    }
                    // s恢复原样
                    str[i] = temp;
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        String start = "abc";
        String end = "cab";
        String[] test = { "abc", "cab", "acc", "cbc", "ccc", "cac", "cbb",
                "aab", "abb" };
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < test.length; i++) {
            list.add(test[i]);
        }
        List<List<String>> res = findMinPath(start, end, list);
        for (List<String> obj : res) {
            for (String str : obj) {
                System.out.print(str + " -> ");
            }
            System.out.println();
        }
    }
}
