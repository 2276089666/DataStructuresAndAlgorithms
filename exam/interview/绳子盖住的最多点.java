package interview;

/**
 * @Author ws
 * @Date 2021/5/18 18:46
 * @Version 1.0
 */
public class 绳子盖住的最多点 {
    /**
     * 给定一个有序数组arr，从左到右依次表示X轴上从左往右点的位置
     * 给定一个正整数K，返回如果有一根长度为K的绳子，最多能盖住几个点
     * 绳子的边缘点碰到X轴上的点，也算盖住
     */

    // 暴力从下标0一个个向右尝试  O(N^2)
    public static int getMax(int[] arr,int k){
        if (arr==null||arr.length==0||k<=0){
            return 0;
        }
        int result=0;
        for (int i = 0; i < arr.length; i++) {
            int temp=0;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j]-arr[i]>k){
                    break;
                }
                temp++;
            }
            result=Math.max(result,temp+1);
        }
        return result;
    }


    // 看到有单调性,立马使用窗口   O(N)
    public static int getMax2(int[] arr,int k){
        if (arr==null||arr.length==0||k<=0){
            return 0;
        }
        int result=0;
        int L=0;
        int R=0;
        int distance=0;
        while (R<arr.length){
            if (distance<=k){
                R++;
                if (R==arr.length){
                    result=Math.max(result,R-L);
                    break;
                }
                distance=arr[R]-arr[L];
            }else  {
                result=Math.max(result,R-L);
                distance=arr[R]-arr[++L];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {-4, -1, 2, 3, 6, 7, 10,11,12};
        int max = getMax(arr, 6);
        System.out.println(max);
        int max2 = getMax2(arr, 6);
        System.out.println(max2);
    }
}
