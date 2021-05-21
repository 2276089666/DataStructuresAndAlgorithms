package interview;

/**
 * @Author ws
 * @Date 2021/5/21 17:22
 */
public class 一维最大装水问题 {
    /**
     * 给定一个数组arr，已知其中所有的值都是非负的，将这个数组看作一个容器， 请返回容器能装多少水
     * 比如，arr = {3，1，2，5，2，4}，根据值画出的直方图就是容器形状，该容 器可以装下5格水
     * 再比如，arr = {4，5，1，3，2}，该容器可以装下2格水
     */

    // 利用前缀最大辅助数组,后缀最大辅助数组       时间:O(N)   额外空间:O(2N)
    public static int getWater(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        // 生成前缀最大数组
        int[] preMaxArray = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                preMaxArray[i] = arr[i];
            } else {
                preMaxArray[i] = Math.max(arr[i], preMaxArray[i - 1]);
            }
        }
        // 生成后缀最大数组
        int[] lastMaxArray = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            if (i == arr.length - 1) {
                lastMaxArray[i] = arr[i];
            } else {
                lastMaxArray[i] = Math.max(arr[i], lastMaxArray[i + 1]);
            }
        }
        int result = 0;
        // 最左和最右的都装不了水,直接跳过
        for (int i = 1; i < arr.length - 1; i++) {
            int min = Math.min(preMaxArray[i], lastMaxArray[i]);
            result += min - arr[i] > 0 ? min - arr[i] : 0;
        }
        return result;
    }

    // 使用双指针记录两边的最大值,谁小,更新哪边的水量  时间O(N)  额外空间O(1)
    public static int getWater2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int L=1;
        int R=arr.length-2;
        int result=0;
        int leftMax=arr[0];
        int rightMax=arr[arr.length-1];
        while (L<R){
            if (leftMax<rightMax){
                result+=leftMax-arr[L]>0?leftMax-arr[L]:0;
                leftMax=Math.max(leftMax,arr[L]);
                L++;
            }else if (leftMax==rightMax){
                result+=leftMax-arr[L]>0?leftMax-arr[L]:0+rightMax-arr[R]>0?rightMax-arr[R]:0;
                leftMax=Math.max(leftMax,arr[L]);
                rightMax=Math.max(rightMax,arr[R]);
                L++;
                R--;
            }else {
                result+=rightMax-arr[R]>0?rightMax-arr[R]:0;
                rightMax=Math.max(rightMax,arr[R]);
                R--;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[] arr = {4, 5, 1, 3, 2};
        int water = getWater(arr);
        System.out.println(water);
        int water2 = getWater2(arr);
        System.out.println(water2);
    }
}
