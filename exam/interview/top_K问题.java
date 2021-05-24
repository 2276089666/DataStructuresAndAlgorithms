package interview;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @Author ws
 * @Date 2021/5/23 9:52
 */
public class top_K问题 {
    /**
     * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
     * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序
     * <p>
     * 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
     * 输出: ["i", "love"]
     * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
     * 注意，按字母顺序 "i" 在 "love" 之前。
     *  
     * 示例 2：
     * <p>
     * 输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
     * 输出: ["the", "is", "sunny", "day"]
     * 解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
     * 出现次数依次为 4, 3, 2 和 1 次。
     */

    public static class Node {
        String str;
        Integer times;

        public Node(String str, Integer times) {
            this.str = str;
            this.times = times;
        }
    }

    public static class TimesComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.times != o2.times ? o1.times - o2.times : o2.str.compareTo(o1.str);
        }
    }

    public static String[] getTopKString(String[] strs, int k) {
        if (strs == null || strs.length == 0 || k > strs.length || k < 1) {
            return null;
        }
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            if (!map.containsKey(strs[i])) {
                map.put(strs[i], 1);
            }
            map.put(strs[i], map.get(strs[i]) + 1);
        }
        // 用小根堆可以维护堆中的数据就是我们的结果,节省容量
        PriorityQueue<Node> heap = new PriorityQueue<>(new TimesComparator());
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            if (heap.size() < k) {
                heap.add(node);
            } else {
                if (heap.peek().times < node.times) {
                    heap.poll();
                    heap.add(node);
                }
            }
        }
        String[] result = new String[heap.size()];
        int i = heap.size() - 1;
        while (!heap.isEmpty()) {
            result[i--] = heap.poll().str;
        }
        return result;
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"the", "ba", "is", "sunny", "sunny", "the", "the", "the", "sunny", "is", "is", "ab"};
        String[] topKString = getTopKString(strs, 4);
        for (String s : topKString) {
            System.out.println(s + "\t");
        }
    }
}
