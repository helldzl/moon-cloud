package org.moonframework.programmer.ch05;

import java.util.Arrays;

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
    public static void sort(int[] array) {
        int tail = array.length - 1;
        for (int i = (tail - 1) / 2; i >= 0; i--) {
            adjust(array, i, tail);
        }

        for (int i = tail; i > 0; i--) {
            swap(array, 0, i);
            adjust(array, 0, i - 1);
        }
    }

    private static void adjust(int[] array, int x, int y) {
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

    private static void swap(int[] array, int head, int tail) {
        int temp = array[head];
        array[head] = array[tail];
        array[tail] = temp;
    }

    private static void print(int[] array) {
        System.out.println();
        for (int i : array) {
            System.out.printf("%d \t", i);
        }
    }

    private static boolean test() {
        int[] array = new int[20];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 100);
        }
        int[] origin = Arrays.copyOf(array, array.length);

        long start = System.nanoTime();
        HeapSort.sort(array);
        long end = System.nanoTime();
        System.out.println("success in " + (end - start));
        print(origin);
        print(array);

        boolean valid = true;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                valid = false;
                print(origin);
                print(array);
                break;
            }
        }
        return valid;
    }

    public static void main(String[] args) {
        while (test()) {

        }
    }

}
