/**
 * @Author ws
 * @Date 2021/3/20 15:47
 * @Version 1.0
 */
public class Search {

    public static int binarySearch(int array[], int a) {
        int low = 0;
        int high = array.length - 1;
        int mid;
        while (low <= high) {
            // 算数右移,实现取中值
            mid = low + ((high - low) >> 1);
            if (array[mid] == a) {
                return mid;
            } else if (a > array[mid]) {
                // 向右查找
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 找到>=value的最左侧的位置            1 2 3 3 4 5  当value为3时,最左侧的位置为2
     *
     * @param arr
     * @param value
     * @return
     */
    public static int getMinValue(int[] arr, int value) {
        int l = 0;
        int h = arr.length - 1;
        int index = -1;
        while (l <= h) {
            int mid = l + ((h - l) >> 1);
            if (arr[mid] >= value) {
                index = mid;
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return index;
    }

    /**
     * 在一个无序数组中找到局部最小值:1.当该数是数组的最左边位置并比他右边相邻的一个数小时
     * 2.当该数是数组的最右边位置并比他左边相邻的一个数小时
     * 3.该数在数组中间,他要比相邻的左边和右边的数都小时
     * 这三种情况都是局部最小
     *
     * @param arr
     * @return
     */
    // TODO: 2021/4/4 说明二分不一定需要数组有序
    public static int localMinimum(int[] arr) {
        if (arr==null||arr.length<=1){
            return -1;
        }
        int l = 0;
        int h = arr.length - 1;

        // 最左边判断
        if (arr[l] < arr[l + 1]) {
            return l;
        }
        // 最右边判断
        if (arr[h] < arr[h - 1]) {
            return h;
        }
        // 上面的两个条件不满足时,数组必定有一个这样的趋势 \ / 局部最小值一定在中间
        l=l+1;
        h=h-1;
        while (l <= h) {
            int mid = l + ((h - l) >> 1);
            if (arr[mid] > arr[mid + 1]) {   // 趋势为一直递减,此时向右二分
                l = mid + 1;
            } else if (arr[mid] > arr[mid - 1]) {   // 趋势是一直递增,此时向左二分
                h = mid - 1;
            }else {
                // 当上面两种情况不满足时,此时趋势就是\ /是我们要找的局部最小值
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int array[] = new int[]{3, 4, 6, 20, 40, 45, 51, 62, 70, 99, 110};
        int i = binarySearch(array, 4);
        int arr[] = new int[]{1, 2, 3, 3, 4, 5};
        int minValue = getMinValue(arr, 3);
        System.out.println(minValue);
        int arr2[] = new int[]{5, 4, 7, 8, 6, 9};
        int i1 = localMinimum(arr2);
    }
}
