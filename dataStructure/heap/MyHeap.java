package heap;

import java.util.*;

/**
 * @Author ws
 * @Date 2021/4/6 17:50
 * @Version 1.0
 */
public class MyHeap<T> {
    List<T> heap;
    Map<T, Integer> indexMap;
    private int heapSize;
    Comparator<T> comparator;

    public MyHeap(Comparator<T> comparator) {
        this.heap = new ArrayList<>();
        this.indexMap = new HashMap<>();
        this.heapSize = 0;
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public boolean contains(T value) {
        return this.indexMap.containsKey(value);
    }

    public int size() {
        return heapSize;
    }

    public void push(T value) {
        if (contains(value)) {
            return;
        }
        heap.add(value);
        indexMap.put(value, heapSize);
        heapInsert(heapSize++);
    }

    public T pop() {
        // 根节点
        T t = heap.get(0);
        int end = heapSize - 1;
        // 第一个元素和最后一个交换
        swap(0, end);
        // 删除第一个元素得信息
        indexMap.remove(t);
        // 删除第一个元素
        heap.remove(end);
        // 下沉
        heapify(0, --heapSize);
        return t;
    }

    // 刷新节点得值,重新排序
    public void resign(T value) {
        int valueIndex = indexMap.get(value);
        // heapInsert和heapify只会执行一个
        heapInsert(valueIndex);
        heapify(valueIndex, heapSize);
    }

    private void heapify(int i, int heapSize) {
        int leftChild = (i << 1) | 1;
        while (leftChild < heapSize) {
            int largest = leftChild + 1 < heapSize && (comparator.compare(heap.get(leftChild + 1), heap.get(leftChild)) < 0)
                    ? leftChild + 1
                    : leftChild;
            largest = comparator.compare(heap.get(largest), heap.get(leftChild)) < 0 ? largest : leftChild;
            if (largest == i) {
                break;
            }
            swap(largest, i);
            i = largest;
            leftChild = (i << 1) | 1;
        }
    }

    private void heapInsert(int index) {
        while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void swap(int i, int j) {
        T t1 = heap.get(i);
        T t2 = heap.get(j);
        heap.set(j, t1);
        heap.set(i, t2);
        indexMap.put(t1, j);
        indexMap.put(t2, i);
    }

    public static class Student{
        public int classNo;
        public int age;
        public int id;

        public Student(int c, int a, int i) {
            classNo = c;
            age = a;
            id = i;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "classNo=" + classNo +
                    ", age=" + age +
                    ", id=" + id +
                    '}';
        }
    }

    public static class StudentComparator implements Comparator<Student>{

        @Override
        public int compare(Student o1, Student o2) {
            return o1.classNo-o2.classNo;
        }
    }

    public static void main(String[] args) {
        Student s1 = null;
        Student s2 = null;
        Student s3 = null;
        Student s4 = null;
        Student s5 = null;
        Student s6 = null;

        s1 = new Student(2, 50, 11111);
        s2 = new Student(1, 60, 22222);
        s3 = new Student(6, 10, 33333);
        s4 = new Student(3, 20, 44444);
        s5 = new Student(7, 72, 55555);
        s6 = new Student(1, 14, 66666);

        MyHeap<Student> studentMyHeap = new MyHeap<>(new StudentComparator());
        studentMyHeap.push(s1);
        studentMyHeap.push(s2);
        studentMyHeap.push(s3);
        studentMyHeap.push(s4);
        studentMyHeap.push(s5);
        studentMyHeap.push(s6);

        s6.classNo=100;
        studentMyHeap.resign(s6);

        while (!studentMyHeap.isEmpty()){
            System.out.println(studentMyHeap.pop());
        }

    }
}
