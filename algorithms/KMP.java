/**
 * @Author ws
 * @Date 2021/4/27 19:24
 * @Version 1.0
 */
public class KMP {
    /**
     * 假设字符串str长度为N，字符串match长度为M，M <= N
     * 想确定str中是否有某个子串是等于match的。如果有返回str中等于match的起始下标,如果没有返回-1
     * 时间复杂度O(N)
     */


    public static int getIndex(String str, String match) {
        if (str == null || match == null || match.length() < 1 || match.length() > str.length()) {
            return -1;
        }
        char[] strArray = str.toCharArray();
        char[] matchArray = match.toCharArray();
        int[] next = getNext(matchArray);
        int x = 0;  // str的下标
        int y = 0;  // match的下标
        while (x < strArray.length && y < matchArray.length) {
            if (strArray[x] == matchArray[y]) {
                x++;
                y++;
            } else if (y == 0) {   // match下标回退到0,但是strArray[x]!=matchArray[y],说明x前面都不可能存在解,x++从新找
                x++;
            } else {
                y = next[y];  // 不相等,match往左找首尾相等最长字串的下标,因为x左边的一部分一定等于match的y的尾部分,但是尾部分一定等于首部分
                // 所以接着拿x和y的首部分的下一个比较,可以避免重复比较
            }
        }
        // 如果y==matchArray.length那一定找到了答案,答案就是x走到的位置减去y,否则没有答案返回-1
        return y == matchArray.length ? x - y : -1;
    }

    private static int[] getNext(char[] matchArray) {
        if (matchArray.length==1){
            return new int[]{-1};
        }
        int[] next = new int[matchArray.length];
        // 规定除本字符外,前面字符首尾最长字符串,next[0]前面没有数直接-1
        next[0]=-1;
        // 规定除本字符外,前面字符首尾最长字符串不能包含所有字符,但是只有next[0],故next[1]=0
        next[1]=0;
        // next[]从下标2开始赋值
        int i=2;
        // 我们发现next的i依赖于i-1的next值,如果matchArray[next[i-1]]==matchArray[i-1],next[i]=next[i-1]+1
        // 否则next[i-1]一直往左走,直到找到next[i]的值,否则就为-1;
        // cn代表，cn位置的字符下标，是当前和i-1位置比较的字符
        int cn=0;
        while (i<next.length){
            if (matchArray[cn]==matchArray[i-1]){
                next[i]=cn+1;
                i++;
                // i向后移动一个,matchArray[next[i-1]]也会向后移动一个,cn++
                cn++;
            }else if (cn>0){
                // cn向左移动
                cn=next[cn];
            }else {
                // 一个也没匹配到.next[i]=0
                i++;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String a="abcabcdek";
        String b="abcd";
        int index = getIndex(a, b);
        System.out.println(index);
    }
}
