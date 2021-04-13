package greedy;

/**
 * @Author ws
 * @Date 2021/4/13 21:26
 * @Version 1.0
 */
public class LightUp {
    /**
     * 给定一个字符串str，只由‘X’和‘.’两种字符构成。
     * ‘X’表示墙，不能放灯，也不需要点亮
     * ‘.’表示居民点，可以放灯，需要点亮
     * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
     * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
     */

    public static int getMinLight(String str) {
        if (str == null) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int index = 0;
        int num = 0;
        while (index < chars.length) {
            if (chars[index] == 'X') {
                index++;
            } else {
                num++;
                if (index+1 == str.length()) {
                    break;
                } else {
                    if (chars[index + 1] == 'X') {
                        index = index + 2;
                    } else {
                        index = index + 3;
                    }
                }
            }
        }
        return num;
    }

    public static void main(String[] args) {
        String a="X..X...X.";
        int minLight = getMinLight(a);
        System.out.println(minLight);
    }
}
