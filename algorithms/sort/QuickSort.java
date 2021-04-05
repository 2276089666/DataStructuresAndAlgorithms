package sort;

/**
 * @Author ws
 * @Date 2021/4/5 14:53
 * @Version 1.0
 */
public class QuickSort {

    /**
     * 快速排序(不稳定)
     * <p>
     * O(N log2N)
     *
     * @param arr
     * @return
     */
    public static void quickSort(int[] arr) {
       if (arr==null||arr.length<2){
           return;
       }
       process2(arr,0,arr.length-1);
    }

    private static void process2(int[] arr, int low, int high) {
        if (low>=high){
            return;
        }
        int mid=partition2(arr,low,high);
        process2(arr,low,mid-1);
        process2(arr,mid+1,high);
    }

    private static int partition2(int[] arr, int low, int high) {
        if (low > high) {
            return -1;
        }
        if (low == high) {
            return low;
        }
        int less=low-1;
        int key=arr[high];
        int current=low;
        while (current<=high){
            if (arr[current]<=key){
                Swap.swap2(arr,++less,current);
            }
            current++;
        }
        return less;
    }


    /**
     * 快排的改进,我们把等于key的部分,不参与后续的递归(三路快排)
     * T(N)=aT(N/b)+O(N^d)  由于基准值key得问题,该排序在数学期望下时间复杂度为: N logN  额外空间复杂度为:logN
     * master公式:
     * log(b,a) > d   时间复杂度为:N^log(b,a)
     * log(b,a) ==d   时间复杂度为:N^d * logN   √
     * log(b,a) < d   时间复杂度为:N^d
     *
     * @param arr
     * @return
     */
    public static void quickMoreSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        // 随机找个low到high之间得数与high交换,实现每次递归得基准值key不一样,不然在数组有序得情况下快排得时间复杂度会变成O(N^2)
        Swap.swap2(arr, low + (int) (Math.random() * (high - low + 1)), high);
        int[] p = partition(arr, low, high);    //里面有个while (current < more)所以常数项为O(N^1)
        process(arr, low, p[0] - 1);  //几次process,我们的master公式的a为几
        process(arr, p[1] + 1, high);  // int mid, int high这两个参数的范围就是N/2
    }

    private static int[] partition(int[] arr, int low, int high) {
        if (low > high) {
            return new int[]{-1, -1};
        }
        if (low == high) {
            return new int[]{low, high};
        }
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
                Swap.swap2(arr, ++less, current++);
            } else if (arr[current] > key) {
                Swap.swap2(arr, --more, current);
            } else {
                current++;
            }
        }
        // 返回等于key的区间起始下标和结尾下标
        return new int[]{less + 1, more - 1};
    }
}
