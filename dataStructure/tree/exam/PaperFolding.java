package tree.exam;

/**
 * @Author ws
 * @Date 2021/4/10 20:30
 * @Version 1.0
 */
public class PaperFolding {
    /**
     * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时折痕是凹下去的，即折痕突起的方向指向纸条的背面。 如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕。
     * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次。 请从上到下打印所有折痕的方向。
     * 例如:N=1时，打印: down N=2时，打印: down down up
     */

    // 可以发现折痕是一个满二叉树,任何子树的左子树为down,右子树为up,根节点为down
    // N为折叠的层数,即树的层数
    // boolean downOrUP  true:down  false:up
    public static void printPaperFolding(int N) {
        if (N == 0) {
            return;
        }
        printFolding(N, 1,true);
    }

    private static void printFolding(int level, int currentLevel,boolean downOrUP) {
        if (currentLevel>level){
            return;
        }
        printFolding(level,currentLevel+1,true);
        System.out.println(downOrUP?"down":"up");
        printFolding(level,currentLevel+1,false);
    }

    public static void main(String[] args) {
        printPaperFolding(3);
    }
}
