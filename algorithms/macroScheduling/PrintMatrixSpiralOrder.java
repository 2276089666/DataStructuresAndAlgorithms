package macroScheduling;

/**
 * @Author ws
 * @Date 2021/5/9 18:44
 * @Version 1.0
 */
public class PrintMatrixSpiralOrder {

    /**
     * 转圈打印矩阵
     */

    // 对角线的两个点就可以确定一个大小固定的唯一矩阵,此处我们x为左上角的点,y为右下角的点
    public static void printMatrixSpiralOrder(int[][] arr){
        int rowX=0;
        int columnX=0;
        int rowY=arr.length-1;
        int columnY=arr[0].length-1;
        // 有可能为长方形,所以行和列都要限制
        while (rowX<=rowY&&columnX<=columnY){
            print(arr,rowX++,columnX++,rowY--,columnY--);
        }
    }

    private static void print(int[][] arr, int rowX, int columnX, int rowY, int columnY) {
        // 只有一行时
        if (rowX==rowY){
            for (int i = columnX; i <=columnY ; i++) {
                System.out.print(arr[rowX][i]+"");
            }
        }else if (columnX==columnY){  // 只有一列时
            for (int i = rowX; i <=rowY ; i++) {
                System.out.println(arr[i][columnX]);
            }
        }else {
            int currentRow=rowX;
            int currentColumn=columnX;
            while (currentColumn!=columnY){
                System.out.println(arr[rowX][currentColumn++]);
            }
            while (currentRow!=rowY){
                System.out.println(arr[currentRow++][columnY]);
            }
            while (currentColumn!=columnX){
                System.out.println(arr[rowY][currentColumn--]);
            }
            while (currentRow!=rowX){
                System.out.println(arr[currentRow--][columnX]);
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 },
                           { 5, 6, 7, 8 },
                           { 9, 10,11,12},
                           { 13,14,15,16}};
        printMatrixSpiralOrder(matrix);
    }
}
