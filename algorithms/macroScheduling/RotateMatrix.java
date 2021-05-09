package macroScheduling;

/**
 * @Author ws
 * @Date 2021/5/9 19:47
 * @Version 1.0
 */
public class RotateMatrix {
    /**
     * 原地旋转正方形矩阵90度
     */

    // 还是用两个点确定一个固定大小的矩阵
    public static void rotateMatrix(int[][] arr){
        int rowX=0;
        int columnX=0;
        int rowY=arr.length-1;
        int columnY=arr[0].length-1;
        // 正方形只需要一个判断
        while (rowX<rowY){
            print(arr,rowX++,columnX++,rowY--,columnY--);
        }
    }

    private static void print(int[][] arr, int rowX, int columnX, int rowY, int columnY) {
        // 每个矩阵的每条边有多少个数要移动到另一个边
        int temp=0;
        for (int i = 0; i < columnY - columnX; i++) {
            temp=arr[rowX][columnX+i];
            // 上等于左
            arr[rowX][columnX+i]=arr[rowY-i][columnX];
            // 左等于下
            arr[rowY-i][columnX]=arr[rowY][columnY-i];
            // 下等于右
            arr[rowY][columnY-i]=arr[rowX+i][columnY];
            // 右等于上
            arr[rowX+i][columnY]=temp;
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 },
                           { 5, 6, 7, 8 },
                           { 9, 10,11,12},
                           { 13,14,15,16}};
        rotateMatrix(matrix);
        System.out.println("=========");
        printMatrix(matrix);

    }
}
