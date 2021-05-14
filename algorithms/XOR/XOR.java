package XOR;

import java.util.Arrays;

/**
 * @Author ws
 * @Date 2021/4/4 13:31
 * @Version 1.0
 */
public class XOR {
    /**
     * 两值交换
     * 异或:相同两数异或为0,任何数与0异或还是任何数
     *
     * @param arr 需要交换的数组
     * @param i   下标
     * @param j   下标
     */
    private static void swap(int[] arr, int i, int j) {
        // 内存位置相同,arr[1]^arr[1]=0,会导致arr[1]=0
        // 但是 arr[]{1,2,1}   arr[0]^arr[2]不会出错
        if (i == j) {
            return;
        }
        // 异或操作 相同为0,不同为1
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j]; // arr[j] = arr[i]^arr[j]^arr[j] = arr[i]^0 = arr[i]
        arr[i] = arr[i] ^ arr[j]; // arr[i] = arr[i]^arr[j]^arr[i] = arr[j]^0 = arr[j]
    }


    // arr中，只有一种数，出现奇数次
    public static void printOddTimesNum1(int[] arr) {
        int ero = 0;
        for (int i = 0; i < arr.length; i++) {
            ero = ero ^ arr[i];
        }
        System.out.println("arr = " + Arrays.toString(arr));
        System.out.println("数组中出现基数次的数为:");
        System.out.println(ero);
    }

    // arr中，有两种数，出现奇数次
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor = eor ^ arr[i];
        }
        // 此时的eor=奇数1^奇数2 ,eor为这两个奇数各位不一样的数的和
        // 通过骚操作随便找出eor某个位上为1的数
        int eor2 = eor & (~eor + 1);   //当前数取反再加1,然后与该数与,得到该数最低位为1的数得值
        int first = 0;
        for (int i = 0; i < arr.length; i++) {
            // 该位为1得数得这个部分是出现奇数次x与偶数次得部分
            if ((arr[i] & eor2) != 0) {
                first = first ^ arr[i];
            }
        }
        System.out.println("其中一个出现奇数次得数为" + first);
        System.out.println("另一个出现奇数次得数为" + (first ^ eor));
    }

    // 找到一个数得二进制为1得个数
    public static int getNum1ByCountByBinary(int value){
        int count=0;
        while (value!=0){
            int rightOne=value&(~value+1);
            count++;
            // 把这个数得二进制最右边得1变为0,不能用减法,当value为负数时,减就不对
            value=value^rightOne;
        }
        return count;
    }


    public static void main(String[] args) {
        int[] a = new int[]{1, 5, 7, 5, 7, 1, 5};
        printOddTimesNum1(a);
        int[] b = new int[]{1, 5, 7, 5, 7, 1, 5, 1};
        printOddTimesNum2(b);
        int num1ByCountByBinary = getNum1ByCountByBinary(-2);
        System.out.println(num1ByCountByBinary);
    }
}
