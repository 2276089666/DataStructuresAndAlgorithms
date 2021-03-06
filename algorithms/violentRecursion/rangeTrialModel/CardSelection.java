package violentRecursion.rangeTrialModel;

/**
 * @Author ws
 * @Date 2021/4/18 14:51
 * @Version 1.0
 */
public class CardSelection {
    /**
     * 给定一个整型数组arr，代表数值不同的纸牌排成一条线，
     * 玩家A和玩家B依次拿走每张纸牌，
     * 规定玩家A先拿，玩家B后拿，
     * 但是每个玩家每次只能拿走最左或最右的纸牌，
     * 玩家A和玩家B都绝顶聪明。请返回最后获胜者的分数
     */

    public static int getCardSelection(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(firstSelect(arr, 0, arr.length - 1), lastSelect(arr, 0, arr.length - 1));
    }

    public static int firstSelect(int[] arr, int left, int right) {
        // 只剩最后一张牌，先选者必定会选,baseCase
        if (left == right) {
            return arr[left];
        }
        int selectLeftResult = arr[left] + lastSelect(arr, left + 1, right);
        int selectRightResult = arr[right] + lastSelect(arr, left, right - 1);
        // 当前先选者拿到最大值的选择
        return Math.max(selectLeftResult, selectRightResult);
    }

    public static int lastSelect(int[] arr, int left, int right) {
        // 后选者在剩最后一张牌时，必定会被先选者拿走，故没牌了，返回0 baseCase
        if (left == right) {
            return 0;
        }
        int selectLeftResult = firstSelect(arr, left + 1, right);
        int selectRightResult = firstSelect(arr, left, right - 1);
        // 让下一次的先选者拿到最小值的选择
        return Math.min(selectLeftResult, selectRightResult);
    }

    /**
     * 动态规划
     * @param arr
     * @return
     */
    public static int dpWay(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 范围上的尝试模型,我们发现他的动态规划数组是一个正方形矩阵,并且L>R处都失效
        int N = arr.length;
        // 从firstSelect函数递归的baseCase可以看出矩阵的对角线都为arr[i];
        int[][] firstSelect = new int[N][N];
        // 从lastSelect函数的baseCase可以看出矩阵的对角线都为0;
        int[][] lastSelect = new int[N][N];
        for (int i = 0; i < N; i++) {
            firstSelect[i][i] = arr[i];
        }
        // 其实默认也是0,此步骤可以省略
        for (int i = 0; i < N; i++) {
            lastSelect[i][i] = 0;
        }
        // 通过分析我们发现俩个递归函数在相互嵌套
        for (int i = 1; i < N; i++) {
            int left = 0;
            int right = i;
            while (left < N && right < N) {
                firstSelect[left][right] = Math.max(arr[left] + lastSelect[left + 1][right], arr[right] + lastSelect[left][right - 1]);
                lastSelect[left][right] = Math.min(firstSelect[left + 1][right], firstSelect[left][right - 1]);
                left++;
                right++;
            }
        }
        return Math.max(firstSelect[0][N - 1], lastSelect[0][N - 1]);
    }

    public static void main(String[] args) {
        int[] arr = {1, 100, 7};
        int i = firstSelect(arr, 0, arr.length - 1);
        int i1 = lastSelect(arr, 0, arr.length - 1);
        System.out.println("先选者的value:\t" + i);
        System.out.println("后选者的value:\t" + i1);
        int cardSelection = getCardSelection(arr);
        System.out.println("最大值:\t" + cardSelection);
        int i2 = dpWay(arr);
        System.out.println(i2);
    }
}
