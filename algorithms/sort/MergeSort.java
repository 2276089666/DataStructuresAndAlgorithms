package sort;

/**
 * @Author ws
 * @Date 2021/4/5 14:56
 * @Version 1.0
 */
public class MergeSort {
    /**
     * 归并排序(递归,稳定)
     * a=2,b=2,d=1
     * log(b,a)==d  ==>  N^d * logN ==> N*logN
     * 时间复杂度 O(N log2N)
     * 额外空间复杂度 O(N)
     * @param arr
     * @return
     */
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        recursionSort(arr, 0, arr.length - 1);
    }


    /**
     * 对左右两边的数据进行递归
     *
     * @param arr
     * @param left
     * @param right
     */
    private static void recursionSort(int[] arr, int left, int right) {
        if (left == right) {
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
        // 拷贝数组 left + i 注意
        for (i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }
    }


    /**
     * 归并排序(非递归的)
     * log(2,N)次merge,每次merge为N
     * O(N logN)
     *
     * @param arr
     * @return
     */
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length - 1;
        int mergeSize = 1;
        while (mergeSize < N) {
            int L = 0;
            // 相同mergeSize下的操作
            while (L < N) {
                int M = L + mergeSize - 1;
                if (M > N) {
                    break;
                }
                // 防止最后两个小数组的右边的小数组凑不齐
                int R = Math.min(M + mergeSize, N);
                merge(arr, L, M, R);
                // 到下两个小数组左边界
                L = R + 1;
            }
            if (mergeSize > N / 2) {
                break;
            }
            // mergeSize左移一位乘2
            mergeSize = mergeSize << 1;
        }
    }

    public static class MergeSortApplication {
        /**
         *   得一个数组得小和  [1,4,2,7,9]
         *   1:左边没比他小的          0
         *   4:左边比他小的有1         1
         *   2:左边比他小的有1         1
         *   7:左边比他小的有1,4,2     1,4,2
         *   9:左边比他小的有1,4,2,7   1,4,2,7
         *   小和:sum=0+1+1+(1+4+2)+(1+4+2+7)=23
         */

        /**
         * 使用遍历得方法,用逆向思维分别找比1大得数右边有几个,依次类推
         *
         * @param arr
         * @return
         */
        public static int method1(int[] arr) {
            int sum = 0;
            for (int i = 0; i < arr.length - 1; i++) {
                int count = 0;
                for (int j = i; j < arr.length - 1; j++) {
                    if (arr[i] < arr[j + 1]) {
                        count++;
                    }
                }
                sum += arr[i] * count;
            }
            return sum;
        }

        /**
         * 使用归并排序每次归并排序拿到小和,将排好序得两个小数组继续归并拿小和
         * @param arr
         * @return
         */
        public static int method2(int[] arr) {
            if (arr == null || arr.length < 2) {
                return 0;
            }
            return mergeGetSum(arr, 0, arr.length - 1);
        }

        private static int mergeGetSum(int[] arr, int l, int r) {
            if (l == r) {
                return 0;
            }
            int mid = l + ((r - l) >> 1);
            int a = mergeGetSum(arr, l, mid);
            int b = mergeGetSum(arr, mid + 1, r);
            int c = mergeSum(arr, l, mid, r);
            return a + b + c;
        }

        private static int mergeSum(int[] arr, int l, int mid, int r) {
            int[] help = new int[r - l + 1];
            int p1 = l;
            int p2 = mid + 1;
            int sum = 0;
            int i = 0;
            while (p1 <= mid && p2 <= r) {
                // 如果左边数组第一个数比右边第一个数小,那么在两个排好序得数组中,左边得第一个数一定比右边得数组中得数都小
                // 直接拿小和p1++,否则去看右边数组后面的数p2++直到p2>r越界
                sum += arr[p1] < arr[p2] ? (r - p2+1) * arr[p1] : 0;
                // 移动操作,并将比较过得数拷贝到help数组排序
                help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
            }
            while (p1<=mid){
                help[i++]=arr[p1++];
            }
            while (p2<=r){
                help[i++]=arr[p2++];
            }
            for (int j = 0; j < help.length; j++) {
                arr[l+j]=help[j];
            }
            return sum;
        }

        public static void main(String[] args) {
            int[] arr = new int[]{1, 4, 2, 7, 9};
            int i = method1(arr);
            System.out.println(i);
            int i1 = method2(arr);
            System.out.println(i1);
        }
    }

}
