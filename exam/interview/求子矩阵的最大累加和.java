package interview;

/**
 * @Author ws
 * @Date 2021/5/27 15:35
 */
public class 求子矩阵的最大累加和 {
    /**
     * 给定一个整型矩阵，返回子矩阵的最大累计和。
     */

    public static int getMaxInMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return -1;
        }
        int max=Integer.MIN_VALUE;
        int current=0;
        // 枚举矩阵行行之间的组合，我们压缩矩阵，把每种不同行的组合累加成为一维数组，再求子数组最大累加和问题
        for (int i = 0; i < matrix.length; i++) {
            int[] help = new int[matrix[0].length];
            // 以行号为i的压缩矩阵有几种情况
            for (int j = i; j < matrix.length; j++) {
                for (int k = 0; k < help.length; k++) {
                    help[k]+=matrix[j][k];
                }
                // 子数组最大累加和问题
                current=0;
                for (int k = 0; k < help.length; k++) {
                    current+=help[k];
                    max=Math.max(max,current);
                    current=current<0?0:current;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                { -90, 48, 78 },
                { 64, -40, 64 },
                { -81, -7, 66 }
        };
        int maxInMatrix = getMaxInMatrix(matrix);
        System.out.println(maxInMatrix);
    }
}
