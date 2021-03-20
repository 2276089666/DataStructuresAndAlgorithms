/**
 * @Author ws
 * @Date 2021/3/20 15:47
 * @Version 1.0
 */
public class Search {

    public static int binarySearch(int array [],int a ){
        int low=0;
        int high=array.length-1;
        int mid;
        while (low<=high){
            // 算数右移,实现取中值
            mid=low+((high-low)>>1);
            if (array[mid]==a){
                return mid;
            }else if (a>array[mid]){
                // 向右查找
                low=mid+1;
            }else {
                high=mid-1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int array[] =new int[]{3,4,6,20,40,45,51,62,70,99,110};
        int i = binarySearch(array, 4);

    }
}
