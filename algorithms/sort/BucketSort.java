package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

/**
 * @Author ws
 * @Date 2021/4/5 14:57
 * @Version 1.0
 */
public class BucketSort {
    /**
     * 桶排序(不能有负数)
     * O(N)
     * @param arr
     */
    public static void bucketSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        // 创建最大数对应大小的数组
        int[] bucket = new int[max + 1];
        // bucket数组记录对应数值的个数,因为数组的下标不能有负数,所以不能有负值参与排序
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }
        int i = 0;
        for (int j = 0; j < bucket.length; j++) {
            while (bucket[j]-- > 0) {
                // 给原数组重新赋值
                arr[i++] = j;
            }
        }
    }

    /**
     * 桶排序(可以有负数)
     *
     * @param arr
     * @return
     */
    public static int[] bucketSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return null;
        }
        // max初始值为int的最小值,能正确的利用max函数找到数组的最大值
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        // 找到数组的最大值,和最小值
        for (int i = 0; i < arr.length; i++) {
            max = max(max, arr[i]);
            min = min(min, arr[i]);
        }

        if (max == min) {
            return arr;
        }

        // 多少个桶
        int bucketNum = (max - min) / arr.length + 1;
        // 创建桶
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            bucketArr.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < arr.length; i++) {
            // 找到哪个桶
            int bucketIndex = (arr[i] - min) / arr.length;
            bucketArr.get(bucketIndex).add(arr[i]);
        }

        for (int i = 0; i < bucketArr.size(); i++) {
            // 排序
            Collections.sort(bucketArr.get(i));
        }

        // 把多个桶的IntStream合并并映射成数组
        arr = bucketArr.stream().flatMapToInt(a -> {
            // 每一个桶,我们把它映射成IntStream
            IntStream intStream = a.stream().flatMapToInt(b -> {
                return IntStream.of(b.intValue());
            });
            return intStream;
        }).toArray();

        return arr;

    }

    /**
     * 返回两值中的小的那个
     *
     * @param a
     * @param b
     * @return
     */
    public static int min(int a, int b) {
        return (a <= b) ? a : b;
    }

    /**
     * 返回两值中的大的那个
     *
     * @param a
     * @param b
     * @return
     */
    public static int max(int a, int b) {
        return (a >= b) ? a : b;
    }

}
