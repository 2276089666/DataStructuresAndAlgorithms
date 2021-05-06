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
     * 时间复杂度O(N)
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
            // i位置的回文半径最少多大
            pArr[i] = i >= R ? 1 : Math.min(pArr[2 * C - i], R - i);
            // 试探以i为中轴的回文半径
            while (i + pArr[i] < arr.length && i - pArr[i] > -1) {
                if (arr[i + pArr[i]] == arr[i - pArr[i]])
                    pArr[i]++;
                else {
                    break;
                }
            }
            // 移动下标
            if (i+pArr[i]>R){
                R=i+pArr[i];
                C=i;
            }
            max=Math.max(max,pArr[i]);
        }
        return max-1;
    }

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
        String a="2213312";
        int maxLength = getMaxLength(a);
        System.out.println(maxLength);
    }
}
