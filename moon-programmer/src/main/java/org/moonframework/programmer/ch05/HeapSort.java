package org.moonframework.programmer.ch05;

/**
 * @author quzile
 * @version 1.0
 * @since 2018/3/10
 */
public class HeapSort {

    /**
     * 如果下标从0开始, 最后一个非叶子节点下标:(n-1)/2 左孩子:2*i+1, 右孩子:2*i+2
     * 下标从1开始, 最后一个非叶子节点下标:n/2 左孩子:2*i, 右孩子:2*i+1
     *
     * @param array
     */
    public static int[] sort(int[] array) {
        int tail = array.length - 1;
        for (int i = (tail - 1) / 2; i >= 0; i--) {
            adjust(array, i, tail);
        }

        for (int i = tail; i > 0; i--) {
            swap(array, 0, i);
            adjust(array, 0, i - 1);
        }
        return array;
    }

    public static void adjust(int[] array, int x, int y) {
        int temp = array[x];
        for (int index = 2 * x + 1; index <= y; index = 2 * index + 1) {
            if (index < y && array[index] < array[index + 1])
                ++index;

            if (temp > array[index])
                break;

            array[x] = array[index];
            x = index;
        }
        array[x] = temp;
    }

    public static void swap(int[] array, int head, int tail) {
        int temp = array[head];
        array[head] = array[tail];
        array[tail] = temp;
    }

    public static void main(String[] args) {
        int[] result = HeapSort.sort(new int[]{23, 45, 12, 67, 55, 48, 98, 34, 24, 78});
        for (int i : result) {
            System.out.println(i);
        }
    }

}
