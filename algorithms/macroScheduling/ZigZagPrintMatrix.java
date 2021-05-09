package macroScheduling;


/**
 * @Author ws
 * @Date 2021/5/9 16:21
 * @Version 1.0
 */
public class ZigZagPrintMatrix {
    /**
     * 之字打印矩阵
     */

    // 在(0,0)位置的分别弄两个标记点x,y 其中x先向右走再向下走,y先向下走再向右走,直到两个点在矩阵的最右下方相遇
    // 两个点同时走,两个点之间的连线的点就是我们的打印路径,我们记录一下每次的打印顺序就行
    public static void zigZagPrintMatrix(int[][] arr) {
        int rowX = 0;
        int columnX = 0;
        int rowY = 0;
        int columnY = 0;

        int endRow = arr.length - 1;
        int endColumn = arr[0].length - 1;
        // 默认为从下往上打印
        boolean fromUp = false;
        while (rowX < endRow + 1) {
            print(arr, rowX, columnX, rowY, columnY, fromUp);
            // 如果x不能向右,就向下
            // columnX作为判断条件,他的赋值应该在后面
            rowX = columnX == endColumn ? rowX + 1 : rowX;
            columnX = columnX == endColumn ? columnX : columnX + 1;

            // 如果y不能向下,就向右
            // rowY作为判断条件,他的赋值应该在后面
            columnY = rowY == endRow ? columnY + 1 : columnY;
            rowY = rowY == endRow ? rowY : rowY + 1;


            // 改变打印方向
            fromUp = !fromUp;
        }
    }

    private static void print(int[][] arr, int rowX, int columnX, int rowY, int columnY, boolean fromUp) {
        if (fromUp) {
            // 从右上到左下
            while (rowX <= rowY) {
                System.out.print(arr[rowX++][columnX--] + " ");
            }
        } else {
            // 从左下到右上
            while (rowY >= rowX) {
                System.out.print(arr[rowY--][columnY++] + " ");
            }
        }
    }


    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4},
                          {5, 6, 7, 8},
                          {9,10,11,12}};
        zigZagPrintMatrix(matrix);
    }
}
