package interview;

/**
 * @Author ws
 * @Date 2021/5/19 15:22
 * @Version 1.0
 */
public class Exam_04 {
    /**
     * 有一些排成一行的正方形。每个正方形已经被染成红色或者绿色。现在可以选择任意一个正方形然后用这两种颜色的任意一种进行染色,
     * 这个正方形的颜色将 会被覆盖。目标是在完成染色之后,每个红色R都比每个绿色G距离最左侧近。 返回最少需要涂染几个正方形。
     * 如样例所示: s = RGRGR 我们涂染之后变成RRRGG满足要求了,涂染的个数为2,没有比这个更好的涂染方案。
     */

    public static int getMin(String str){
        if (str==null||str.length()==0){
            return -1;
        }
        char[] array = str.toCharArray();
        int rightRNum=0;  // 分界线右边R的数量
        for (int i = 0; i < array.length; i++) {
            rightRNum+=array[i]=='R'?1:0;
        }
        int result=rightRNum;
        int leftGNum=0;  // 分界线左边G的数量
        // 以arr[i]和arr[i+1]中间为分界线
        for (int i = 0; i < array.length; i++) {
            leftGNum+=array[i]=='G'?1:0;  // 分界线左边需要从G涂成R的数量
            rightRNum-=array[i]=='R'?1:0;  // 分界线右边需要从R涂成G的数量
            result=Math.min(result,leftGNum+rightRNum);
        }
        return result;
    }

    public static void main(String[] args) {
        String s="GRRRRR";
        int min = getMin(s);
        System.out.println(min);
    }
}
