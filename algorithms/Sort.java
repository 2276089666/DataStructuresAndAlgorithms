import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

/**
 * @Author ws
 * @Date 2021/3/20 15:15
 * @Version 1.0
 * <p>
 * 排序的稳定性: 相同的数的顺序在排完序后不改变   [1,4,6,4,8,4]
 * 三个4的先后顺序不改变
 * 意义:可以保留原始次序
 * 例:  201803523 王   1班
 * 201803524 李   2班
 * 201803525 张   1班
 * 按照学号结果如上
 * 现在再按班级排序
 * 201803523 王  1班
 * 201803525 张  1班
 * 201803524 李  2班
 * 此时王还在张的前面,保留了第一次排序的原始次序
 */

/**
 * 排序的稳定性: 相同的数的顺序在排完序后不改变   [1,4,6,4,8,4]
 * 三个4的先后顺序不改变
 * 意义:可以保留原始次序
 * 例:  201803523 王   1班
 *      201803524 李   2班
 *      201803525 张   1班
 *  按照学号结果如上
 *  现在再按班级排序
 *      201803523 王  1班
 *      201803525 张  1班
 *      201803524 李  2班
 *  此时王还在张的前面,保留了第一次排序的原始次序
 */

/**
 * 工程排序: 在大数组时:1.快速排序(当需要排序的数据是基本数据类型时,因为他不需要稳定,就算不稳定我们也看不出来)
 *                  2.归并排序(当需要排序的数据是我们自定义的数据类型时,因为他需要稳定,就是上面所说的保留原始次序,如果不稳定我们可以看出来)
 *          在上面的两钟方法中的递归过程中,如果数据量小于60,直接用插入排序,因为在小数据量的时候插入排序的常数项更低更快
 */
public class Sort {

    /**
     * 两值交换
     *
     * @param arr 需要交换的数组
     * @param i   下标
     * @param j   下标
     */
    private static void swap(int[] arr, int i, int j) {
        // 异或操作 相同为0,不同为1
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    /**
     * 冒泡排序(稳定)
     * O(N^2)
     *
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
        return arr;
    }

    /**
     * 选择排序(不稳定)
     * O(N^2)
     * @param arr
     */
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    /**
     * 插入排序,间隔为1(稳定)
     * O(N^2)
     *
     * @param arr
     * @return
     */
    public static int[] insertSort(int[] arr) {
//        for (int i = 1; i < arr.length; i++) {
//            int insert = arr[i];
//            // 插入值的前一个值的下标
//            int index = i - 1;
//            // 如果插入的数比前一个小,移动
//            while (index >= 0 && insert < arr[index]) {
//                // 移动数组
//                arr[index + 1] = arr[index];
//                index--;
//            }
//            // 将值插入到比insert小的值的后面
//            arr[index + 1] = insert;
//        }
//        return arr;

        shellInsertSort(arr, 1);
        return arr;
    }

    /**
     * 快速排序(不稳定)
     * <p>
     * O(N log2N)
     *
     * @param arr
     * @param low
     * @param high
     * @return
     */
    public static int[] quickSort(int[] arr, int low, int high) {
        // 从前向后比较的索引
        int start = low;
        // 从后向前比较的索引
        int end = high;
        // 基准值
        int key = arr[low];
        while (end > start) {
            while (end > start && arr[end] >= key)
                end--;
            // 从后向前比较,找到比基准值小的,并交换
            if (arr[end] < key) {
                swap(arr, start, end);
            }
            while (end > start && arr[start] <= key)
                start++;
            // 从前向后比较,找到比基准值大的,并交换
            if (arr[start] > key) {
                swap(arr, start, end);
            }

        }

        // 递归左边序列
        if (start > low) {
            quickSort(arr, low, start - 1);
        }
        // 递归右边序列
        if (end < high) {
            quickSort(arr, end + 1, high);
        }
        return arr;
    }


    /**
     * 快排的改进,我们把等于key的部分,不参与后续的递归(三路快排)
     *
     * @param arr
     * @param low
     * @param high
     * @return
     */
    public static void quickMoreSort(int[] arr, int low, int high) {
        if (low < high) {
            int[] p = partition(arr, low, high);
            quickMoreSort(arr, low, p[0] - 1);
            quickMoreSort(arr, p[1] + 1, high);
        }
    }

    private static int[] partition(int[] arr, int low, int high) {
        // 小于key的数组末尾下标
        int less = low - 1;
        // 大于key的数组的起始下标
        int more = high + 1;
        // 基准值
        int key = arr[high];
        // 当前下标
        int current = low;
        while (current < more) {
            if (arr[current] < key) {
                // current移动是因为交换的++less是等于key的区间,或者等于key的区间为空,就是自己交换自己
                // 他们永远<=key
                swap2(arr, ++less, current++);
            } else if (arr[current] > key) {
                swap2(arr, --more, current);
            } else {
                current++;
            }
        }
        // 返回等于key的区间起始下标和结尾下标
        return new int[]{less + 1, more - 1};
    }

    public static void swap2(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 希尔排序
     *
     * @param arr
     * @return
     */
    public static int[] shellSort(int[] arr) {
        // 决定间隔
        for (int dk = arr.length; dk > 0; dk = dk / 2) {
            shellInsertSort(arr, dk);
        }
        return arr;
    }

    /**
     * 以dk为间隔的插入排序
     *
     * @param arr
     * @param dk
     */
    private static void shellInsertSort(int[] arr, int dk) {
        // 根据间隔dk,将大数组分为若干数组
        for (int i = dk; i < arr.length; i++) {
            // 如果当前下标的值比它下标小dk的值小我们就交换,插入排序,间隔为dk
            // j >= dk避免后面的比较造成数组下标越界
            for (int j = i; j >= dk && arr[j] < arr[j - dk]; j = j - dk) {
                // 两值交换
                swap(arr, j, j - dk);
            }
        }
    }


    /**
     * 归并排序
     * O(N log2N)
     *
     * @param arr
     * @return
     */
    public static int[] mergeSort(int[] arr) {
        recursionSort(arr, 0, arr.length - 1);
        return arr;
    }

    /**
     * 对左右两边的数据进行递归
     *
     * @param arr
     * @param left
     * @param right
     */
    private static void recursionSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        // 递归左边数组
        recursionSort(arr, left, mid);
        // 递归右边数组
        recursionSort(arr, mid + 1, right);

        merge(arr, left, mid, right);
    }

    /**
     * 对以mid为界限的两个有序数组合并成一个有序数组并复制到arr里面去
     *
     * @param arr
     * @param left
     * @param mid   左边数组的最后一个索引
     * @param right
     */
    private static void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int p1 = left;
        int p2 = mid + 1;
        int i = 0;
        while (p1 <= mid && p2 <= right) {
            // 两个数组小的值先放入help数组
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 有一个数组先被遍历完
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= right) {
            help[i++] = arr[p2++];
        }
        // 拷贝数组
        for (i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }
    }

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

    /**
     * 基数排序(数组值不能有负数,如果必须要,我们把正数和负数分开排序再合并)
     * O(d(n+r))
     *
     * @param arr
     * @param maxDigit 最大位数
     * @return
     */
    public static int[] radixSort(int[] arr, int maxDigit) {
        // 10的maxDigit+1次方,数组最大位数的数据上限
        double max = Math.pow(10, maxDigit);
        // 代表位数对应的数:1,10,100~
        int n = 1;
        // 保存每一位的排序后的结果用于下一位的排序输入
        int k = 0;
        //  二维数组用于保存每次排序后的结果,将当前位上的排序结果相同的数字放在同一个桶里面
        int[][] bucket = new int[10][arr.length];
        // 用于保存每次排序后的结果,将当前位上排序结果相同的数字放在同一个桶里
        int[] order = new int[arr.length];
        while (n < max) {
            for (int i = 0; i < arr.length; i++) {
                // 个位上的数字,或者十位上的数字~
                int digit = (arr[i] / n) % 10;
                // 将数据放到桶里面
                bucket[digit][order[digit]] = arr[i];
                order[digit]++;
            }
            for (int i = 0; i < arr.length; i++) {
                // 桶里面如果有数据,从上到下遍历这个桶,并将数据保存到原数组
                if (order[i] != 0) {
                    for (int j = 0; j < order[i]; j++) {
                        arr[k] = bucket[i][j];
                        k++;
                    }
                }
                order[i] = 0;
            }
            // 从个位移到十位再到百位~
            n *= 10;
            k = 0; // 下一轮保存位排序结果做准备
        }
        return arr;
    }


    /**
     * 堆排序(不稳定)
     * 基于完全二叉树
     *
     * @param arr
     */
    public static void heapSort(int[] arr) {
        if (arr == null && arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            // 创建大根堆
            heapInsert(arr, i);
        }

        // 排序
        int size = arr.length - 1;
        do {
            // 第一个节点和最后一个节点交换,size--是最后一个是当前循环最大的不用参与比较
            swap2(arr, 0, size--);
            // 堆化
            heapify(arr, 0, size);
        } while (size > 0);
    }

    /**
     * 大根堆插入
     *
     * @param arr
     * @param i
     */
    private static void heapInsert(int[] arr, int i) {
        int index = i;
        // 如果子节点比父节点大,我们交换位置,当index=0时  (index-1)/2 =0 计算机就是这么算的
        while (arr[index] > arr[(index - 1) / 2]) {
            swap2(arr, index, (index - 1) / 2);
            // 继续向上找父亲节点
            index = (index - 1) / 2;
        }
    }

    /**
     * 堆的下沉
     *
     * @param arr
     * @param i
     * @param size
     */
    private static void heapify(int[] arr, int i, int size) {
        int index = i;
        // 左孩子
        int left = index * 2 + 1;
        while (left <= size) {
            // 返回左右孩子那个大的节点的下标
            int large = left + 1 <= size && arr[left + 1] > arr[left] ? left + 1 : left;
            // 找到父节点和他儿子节点那个大的节点的下标
            large = arr[large] > arr[index] ? large : index;
            // 如果父节点大,本次不下沉
            if (large == index) {
                break;
            }
            // 值小的节点下沉,形成大根堆
            swap2(arr, index, large);

            index = large;
            left = index * 2 + 1;
        }
    }


    public static void main(String[] args) {

        int[] arr = {1, 4, -6, 8, 5, 7, 54, 10, 56, 12, -46, 231, 654, 2, 98, 4};
        int[] sort = bubbleSort(arr);
        int[] arr2 = {1, 4, -6, 8, 5, 7, 54, 10, 56, 12, -46, 231, 654, 2, 98, 4};
        int[] sort2 = insertSort(arr2);
        int[] arr3 = {1, 4, -6, 8, 5, 7, 54, 10, 56, 12, -46, 231, 654, 2, 98, 4};
        int[] sort3 = quickSort(arr3, 0, arr3.length - 1);
        int[] arr4 = {1, 4, -6, 8, 5, 7, 54, 10, 56, 12, -46, 231, 654, 2, 98, 4};
        int[] sort4 = shellSort(arr4);
        int[] arr5 = {1, 4, -6, 8, 5, 7, 54, 10, 56, 12, -46, 231, 654, 2, 98, 4};
        int[] sort5 = mergeSort(arr5);
        int[] arr6 = {1, 4, 8, 5, 7, 54, 10, 56, 12, 231, 654, 2, 98, 4};
        bucketSort(arr6);
        int[] arr7 = {1, 4, 6, 8, 5, 7, 54, 10, 56, 12, 46, 231, 654, 2, 98, 4};
        int[] sort7 = radixSort(arr7, 3);

        int[] arr8 = {1, 4, -6, 8, 5, 7, 54, 10, 4};
        quickMoreSort(arr8, 0, arr8.length - 1);

        int[] arr9 = {1, 4, -6, 8, 5, 7, 54, 10, 4};
        heapSort(arr9);
    }
}
