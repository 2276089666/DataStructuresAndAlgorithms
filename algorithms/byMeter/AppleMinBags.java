package byMeter;

/**
 * @Author ws
 * @Date 2021/5/9 12:03
 * @Version 1.0
 */
public class AppleMinBags {
    /**
     * 打表找规律
     * 1）某个面试题，输入参数类型简单，并且只有一个实际参数
     * 2）要求的返回值类型也简单，并且只有一个
     * 3）用暴力方法，把输入参数对应的返回值，打印出来看看，进而优化code
     */

    /**
     * 小虎去买苹果，商店只提供两种类型的塑料袋，每种类型都有任意数量。
     * 1）能装下6个苹果的袋子
     * 2）能装下8个苹果的袋子
     * 小虎可以自由使用两种袋子来装苹果，但是小虎有强迫症，他要求自己使用的袋子数量必须最少，且使用的每个袋子必须装满。
     * 给定一个正整数N，返回至少使用多少袋子。如果N无法让使用的每个袋子必须装满，返回-1
     */

    // 暴力解法,先将8的数量弄到最大,一个个的减少8的数量直到ok,否则返回-1   O(log(8,n))
    public static int getMinBags(int n){
        if (n<6){
            return -1;
        }
        // 奇数永远不行
        if (n%2==1){
            return -1;
        }
        int count=0;
        int rest=0;
        count=n/8;
        rest=n%8;
        int result=0;
        while (count>=0&&rest<24){
            if (rest%6==0){
                return result+=count+rest/6;
            }else {
                rest+=8;
                count--;
            }
        }
        return -1;
    }

    // 利用上面的暴力方法,我们发现规律,18以后的奇数为-1,每八个加1,来优化代码  O(1)
    public static int getMinBags2(int n) {
        if ((n & 1) == 1) { // 如果是奇数，返回-1
            return -1;
        }
        if (n < 18) {
            return n == 0 ? 0 : (n == 6 || n == 8) ? 1
                    : (n == 12 || n == 14 || n == 16) ? 2 : -1;
        }
        return (n - 18) / 8 + 3;
    }

    public static void main(String[] args) {
        // 打表,发现规律,去优化代码
        for (int i = 1; i < 100; i++) {
            System.out.println(i+":\t"+getMinBags(i));
        }

        int i = getMinBags2(98);
        System.out.println(i);
    }
}
