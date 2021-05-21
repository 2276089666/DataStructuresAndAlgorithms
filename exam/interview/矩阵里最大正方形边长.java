package interview;

/**
 * @Author ws
 * @Date 2021/5/19 15:24
 */
public class 矩阵里最大正方形边长 {
    /**
     * 给定一个N*N的矩阵matrix，只有0和1两种值，返回边框全是1的最大正方形的边长长度。
     * 例如:
     * 01111
     * 01001
     * 01001
     * 01111
     * 01011
     * 其中边框全是1的最大正方形的大小为4*4，所以返回4。
     */

    public static int getMaxSquare(int[][] matrix) {
        int[][] rightMax = new int[matrix.length][matrix[0].length];
        int[][] onMax = new int[matrix.length][matrix[0].length];
        // 求出每个点的右边最长连续1矩阵rightMax,下面最长连续1矩阵onMax
        process(matrix, rightMax, onMax);
        int result = 0;
        // 每个点都尝试一次
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                for (int k = 1; k <= matrix.length; k++) {  // 正方形的边长
                    if (i + k < matrix.length && j + k < matrix[0].length && rightMax[i][j] >= k && onMax[i][j] >= k && rightMax[i + k][j] >= k && onMax[i][j + k] >= k) {
                        result = Math.max(result, k+1);
                    }
                }
            }
        }
        return result;
    }

    private static void process(int[][] matrix, int[][] rightMax, int[][] onMax) {
        for (int j = rightMax[0].length - 1; j >= 0; j--) {
            for (int i = 0; i < rightMax.length; i++) {
                if (j == rightMax[0].length - 1) {
                    rightMax[i][j] = matrix[i][j];
                } else {
                    rightMax[i][j] = matrix[i][j] == 1 ? 1 + rightMax[i][j + 1] : 0;
                }
            }
        }
        for (int i = onMax.length - 1; i >= 0; i--) {
            for (int j = 0; j < onMax[0].length; j++) {
                if (i == onMax.length - 1) {
                    onMax[i][j] = matrix[i][j];
                } else {
                    onMax[i][j] += matrix[i][j] == 1 ? 1 + onMax[i + 1][j] : 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 1, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {0, 1, 0, 0, 1},
                {0, 1, 1, 1, 1},
                {0, 1, 0, 1, 1}};
        int maxSquare = getMaxSquare(matrix);
        System.out.println(maxSquare);

    }
}
