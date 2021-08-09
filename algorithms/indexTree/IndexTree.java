package indexTree;

/**
 * @Author ws
 * @Date 2021/8/9 15:38
 */
public class IndexTree {
    /**
     * 求一个数组i~j位置的累加和
     */

    // 暴力方法,预处理数组,算出一个数组的每个下标的前缀和,查询j+1下标和i下标的前缀和,用j+1下标前缀和减去i下标的前缀和得出答案
    public static class ArrayIndexSum {
        int[] sourceNum;
        int[] indexSumNum;

        public ArrayIndexSum(int[] sourceNum) {
            this.sourceNum=sourceNum;
            this.indexSumNum=new int[sourceNum.length+1];
            this.indexSumNum[0]=0;
            for (int i = 1; i <= sourceNum.length; i++) {
                indexSumNum[i]+=indexSumNum[i-1]+sourceNum[i-1];
            }
        }

        // 原数组某个下标的值增加d
        public void add(int index, int d) {
            sourceNum[index] += d;
        }

        // 拿到某个下标的前缀和
        public int sum(int index) {
            return indexSumNum[index];
        }
    }

    // 发现上面的方法一旦调用add(),原数组某个下标的值增加d,前缀和数组就失效了,由此我们诞生indexTree
    // sum()时间复杂度O(logN),add()的时间复杂度O(logN)
    public static class IndexTrees {
        /**
         * indexTree的前缀和数组的维护方式和上面的不同
         * 维护的累加和        1  1+2   3  1+2+3+4   5   5+6   7   1+2+3+4+5+6+7+8
         * 下标              1    2   3     4      5    6    7          8
         * 每个下标index的前缀和表示 = 源数组中,index的二进制减去最右边的1的值+1到index的累加和
         * 例:5  101   101~101  如上图5只表示5的累加和
         *   8  1000   1~1000  如上图8表示1+2+3+4+5+6+7+8的累加和
         */
        private int[] tree;
        private int N;

        public IndexTrees(int size) {
            N = size;
            tree = new int[N + 1];
        }

        // 0 ~ index 范围上的累加和 =
        public int sum(int index) {
            int ret = 0;
            while (index > 0) {
                ret += tree[index];
                // index & (~index+1)提取最右侧的1
                index -= index & (~index+1);
            }
            return ret;
        }

        // index位置的数，想加上d，还有哪些位置也要都加d
        public void add(int index, int d) {
            while (index <= N) {
                tree[index] += d;
                index += index & (~index+1);
            }
        }
    }


    public static void main(String[] args) {
        ArrayIndexSum arrayIndexSum = new ArrayIndexSum(new int[]{1, 5, 3, 7});
        // 求2~3下标位置的累加和
        int sum1 = arrayIndexSum.sum(2);
        int sum2 = arrayIndexSum.sum(3+1);
        System.out.println(sum2-sum1); // 10
        //  原数组下标2的值增加2
        arrayIndexSum.add(2,2);
        int sum3 = arrayIndexSum.sum(2);
        int sum4 = arrayIndexSum.sum(3+1);
        // 发现结果还是10,原数组某个下标2的值增加2,导致前缀和数组失效,结果出错
        System.out.println(sum4-sum3);


        IndexTrees indexTrees = new IndexTrees(4);
        indexTrees.add(1,1);
        indexTrees.add(2,5);
        indexTrees.add(3,3);
        indexTrees.add(4,7);
        int sum5 = indexTrees.sum(2);
        int sum6 = indexTrees.sum(3 + 1);
        System.out.println(sum6-sum5); // 10
        //  原数组下标2的值增加2
        indexTrees.add(3,2);
        int sum7 = indexTrees.sum(2);
        int sum8 = indexTrees.sum(3 + 1);
        System.out.println(sum8-sum7); // 12
    }
}
