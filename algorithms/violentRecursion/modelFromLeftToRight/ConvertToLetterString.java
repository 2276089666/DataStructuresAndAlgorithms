package violentRecursion.modelFromLeftToRight;

/**
 * @Author ws
 * @Date 2021/4/17 22:24
 * @Version 1.0
 */
public class ConvertToLetterString {
    /**
     * 规定1和A对应、2和B对应、3和C对应...
     * 那么一个数字字符串比如"111”就可以转化为:
     * "AAA"、"KA"和"AK"
     * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
     */

    public int getResultNum(String str) {
        if (str == null || "".equals(str)) {
            return -1;
        }
        char[] chars = str.toCharArray();
        return getResult(chars, 0);
    }

    private int getResult(char[] chars, int index) {
        if (chars.length == index) {
            return 1;
        }
        // 0没有对应的大写字母
        if (chars[index] == '0') {
            return 0;
        }
        if (chars[index] == '1') {
            int result = getResult(chars, index + 1); // index自己作为单独的部分，后续有多少种方法
            if (index + 1 < chars.length) {
                result += getResult(chars, index + 2); // (index和index+1)作为单独的部分，后续有多少种方法
            }
            return result;
        }
        if (chars[index] == '2') {
            int result = getResult(chars, index + 1); // index自己作为单独的部分，后续有多少种方法
            // (index和index+1)作为单独的部分并且没有超过26，后续有多少种方法
            if (index + 1 < chars.length && (chars[index + 1] >= '0' && chars[index] <= '6')) {
                result+=getResult(chars,index+2);
            }
            return result;
        }
        // chars[i] == '3' ~ '9'
        return getResult(chars,index+1);
    }


}
