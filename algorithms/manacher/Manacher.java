package manacher;

/**
 * @Author ws
 * @Date 2021/5/6 15:42
 * @Version 1.0
 */
public class Manacher {
    /**
     * 假设字符串str长度为N，想返回最长回文子串的长度
     * <p>
     * 在下面不同的分支下,通过R的变化,发现R最大变化为N,所以,时间复杂度O(N)
     */
    public static int getMaxLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        // 生成我们的manacher串,方便我们的虚轴判断
        char[] arr = manacherString(str);
        // 回文的半径大小
        int[] pArr = new int[arr.length];
        int C = -1;  // 中轴
        int R = -1;  // 中轴C的最长回文右边界,第一个破坏回文规则的下标
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            /**
             * (一).i大于R,没有优化,以i为中轴,一个个的向两边尝试
             * (二).i小于R,分下面三种情况:
             *
             *  1. #2#2#1#3#3#1#2#5#
             *     i对称点+(i对称点的回文半径)小于R,当i的位置到达后面的一个1的位置,中轴C在两个3中间的#的位置,
             *     R在5左边的#的位置,此时后面位置的1的回文半径必然等于前面位置的1的回文半径,
             *     类似动态规划,不用求,直接得答案
             *  2. i对称点+(i对称点的回文半径)大于R,由于R不能再向右扩,推导发现,此时回文半径必是R-i
             *  3. i对称点+(i对称点的回文半径)等于R,从i+(i的对称点的回文半径)向两边尝试
             */
            // pArr[i]至少不用向外扩的区域
            // 情况(一)下pArr[i]=1
            // 情况(二)下,1,2两种情况可以优化,向两边扩的次数可以减少,3情况和(一)情况一样,挨个向两边尝试
            pArr[i] = i >= R ? 1 : Math.min(pArr[2 * C - i], R - i);
            // 试探以i为中轴的回文半径
            while (i + pArr[i] < arr.length && i - pArr[i] > -1) {
                if (arr[i + pArr[i]] == arr[i - pArr[i]])
                    pArr[i]++;
                else {
                    break;
                }
            }
            // 移动C,R下标
            if (i+pArr[i]>R){
                R=i+pArr[i];
                C=i;
            }
            max=Math.max(max,pArr[i]);
        }
        return max-1;
    }

    // 处理结果: #2#2#1#3#3#1#2#5#
    private static char[] manacherString(String str) {
        char[] array = str.toCharArray();
        char[] result = new char[str.length() * 2 + 1];
        for (int i = 0; i < result.length; i++) {
            if (i % 2 == 0) {
                result[i] = '#';
            } else {
                result[i] = array[i / 2];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String a="22133125";
        int maxLength = getMaxLength(a);
        System.out.println(maxLength);
    }
}
