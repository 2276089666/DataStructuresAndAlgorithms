package interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author ws
 * @Date 2021/5/23 12:25
 */
public class 自定义堆构造top_K容器 {
    /**
     * 请实现如下结构：
     * TopRecord{
     * public TopRecord(int K)  :  构造时事先指定好K的大小，构造后就固定不变了
     * public  void add(String str)  :   向该结构中加入一个字符串，可以重复加入
     * public  List<String> top() : 返回之前加入的所有字符串中，词频最大的K个
     * }(相同词频,不排序,随机)
     * 要求：
     * add方法，复杂度O(log K);
     * top方法，复杂度O(K)
     */


    public static class TopRecord {
        int sizeOrIndex;
        String[] heap = null;
        private HashMap<String, Integer> timesMap = null;  // 词频统计
        private HashMap<String, Integer> indexMap = null; // 节点在堆中的位置

        public TopRecord(int k) {
            this.sizeOrIndex = 0;
            heap = new String[k];
            this.timesMap = new HashMap<>();
            this.indexMap = new HashMap<>();
        }

        public void add(String str) {
            // 修改词频
            int preIndex = -1;
            if (!timesMap.containsKey(str)) {
                timesMap.put(str, 1);
                indexMap.put(str, -1);
            } else {
                Integer times = timesMap.get(str);
                timesMap.put(str, ++times);
                preIndex = indexMap.get(str);
            }

            // 修改节点在堆中的位置表
            if (preIndex == -1) {
                // 堆没满
                if (sizeOrIndex != heap.length) {
                    indexMap.put(str, sizeOrIndex);
                    heap[sizeOrIndex] = str;
                    heapInsert(sizeOrIndex);
                    sizeOrIndex++;
                } else {
                    if (timesMap.get(str) > timesMap.get(heap[0])) {
                        indexMap.put(str, 0);
                        // 移出堆
                        indexMap.put(heap[0], -1);
                        heap[0] = str;
                        heapify(0, sizeOrIndex);
                    }
                }
            } else {
                heapify(preIndex, sizeOrIndex);
            }
        }

        private void heapInsert(int sizeOrIndex) {
            while (sizeOrIndex != 0) {
                // sizeOrIndex-1为负数时parentIndex计算机算出来是0
                int parentIndex = (sizeOrIndex - 1) / 2;
                if (timesMap.get(heap[sizeOrIndex]) < timesMap.get(heap[parentIndex])) {
                    swap(heap, sizeOrIndex, parentIndex);
                    sizeOrIndex = parentIndex;
                } else {
                    break;
                }
            }
        }

        //  交换堆的数据时,节点的下标indexMap也就要交换
        private void swap(String[] heap, int sizeOrIndex, int parentIndex) {
            indexMap.put(heap[sizeOrIndex], parentIndex);
            indexMap.put(heap[parentIndex], sizeOrIndex);
            String temp = heap[sizeOrIndex];
            heap[sizeOrIndex] = heap[parentIndex];
            heap[parentIndex] = temp;
        }

        private void heapify(int index, int sizeOrIndex) {
            int leftChild = (index << 1) | 1;
            while (leftChild < sizeOrIndex) {
                // 最大孩子的下标
                int largest = leftChild + 1 < sizeOrIndex && (timesMap.get(heap[leftChild + 1]) > timesMap.get(heap[leftChild]))
                        ? leftChild + 1
                        : leftChild;
                // 如果父比孩子大,小根堆,要交换
                largest = timesMap.get(heap[index])>timesMap.get(heap[largest]) ? largest : index;
                // 如果父比孩子小,不交换
                if (largest == index) {
                    break;
                }
                swap(heap, index, largest);
                index = largest;
                leftChild = (index << 1) | 1;
            }
        }

        public List<String> top() {
            ArrayList<String> list = new ArrayList<>();
            int index=sizeOrIndex;
            while (index > 0) {
                list.add(heap[index-1]);
                index--;
            }
            return list;
        }
    }

    public static void main(String[] args) {
        TopRecord topRecord = new TopRecord(3);
        topRecord.add("the");
        topRecord.add("sunny");
        topRecord.add("the");
        topRecord.add("ab");
        topRecord.add("is");
        topRecord.add("is");
        topRecord.add("aa");
        List<String> top = topRecord.top();
        for (String s : top) {
            System.out.println(s);
        }
    }
}
