package interview;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author ws
 * @Date 2021/5/24 16:48
 */
public class 已知先序中序求后序 {
    /**
     * 已知一棵二叉树中没有重复节点，并且给定了这棵树的中序遍历数组和先序遍历 数组，返回后序遍历数组。
     * 比如给定:
     * int[] pre = { 1, 2, 4, 5, 3, 6, 7 };
     * int[] in = { 4, 2, 5, 1, 6, 3, 7 }; 返回:
     * {4,5,2,6,7,3,1}
     */

    public static int[] preAndInTOPos(int[] pre, int[] in) {
        if (pre.length != in.length) {
            return null;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        int[] pos = new int[pre.length];
        process(pre, 0, pre.length - 1, in, 0, in.length - 1, pos, 0, pos.length - 1, map);
        return pos;
    }

    /**
     * @param pre
     * @param preFrom 先序数组的起点位置
     * @param preTo   先序数组的终点位置
     * @param in
     * @param inFrom
     * @param inTo
     * @param pos
     * @param posFrom
     * @param posTo
     * @param map     存放中序数组各个数字的下标
     */
    private static void process(int[] pre, int preFrom, int preTo, int[] in, int inFrom, int inTo, int[] pos, int posFrom, int posTo, Map<Integer, Integer> map) {
        if (preFrom > preTo) return;
        if (posFrom == preTo) {
            pos[posFrom] = pre[preFrom];
            return;
        }
        pos[posTo] = pre[preFrom];
        Integer mid = map.get(pre[preFrom]);
        int leftSize = mid - inFrom;
        // 题目: 先序:1 2 4 5 3 6 7
        //      中序:4 2 5 1 6 3 7
        // 先序的2 4 5 和中序的4 2 5  生成后序的[0,1,2]这三个下标位置的
        process(pre, preFrom + 1, preFrom+leftSize, in, inFrom, inFrom+leftSize-1, pos, posFrom, posFrom+leftSize-1, map);
        // 先序的 3 6 7和中序的 6 3 7生成后序的[3,4,5]这三个下标位置的
        process(pre, preFrom+leftSize+1, preTo, in, inFrom+leftSize+1, inTo, pos, posFrom+leftSize, posTo - 1, map);
    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 5, 3, 6, 7};
        int[] in = {4, 2, 5, 1, 6, 3, 7};
        int[] pos = preAndInTOPos(pre, in);
        for (int po : pos) {
            System.out.printf(po+"\t");
        }
    }
}
