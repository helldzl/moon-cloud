package org.moonframework.programmer.ch06;

import java.util.Arrays;

/**
 * <p>二分查找</p>
 * <ul>
 * <li>静态查找表(search table), 适合使用二分查找</li>
 * <li>需要频繁进行插入或者删除的操作数据集, 维护有序集合会带来比较大的开销, 不建议使用二分查找</li>
 * </ul>
 * <p>时间复杂度: O(log₂N)</p>
 *
 * @author quzile
 * @version 1.0
 * @since 2018/3/21
 */
public class BinarySearch {

    /**
     * LOW  (L)
     * HIGH (H)
     * MID  (M)
     *
     * @param array array
     * @param key   key
     * @return index
     */
    private static int search(int[] array, int key) {
        int l = 0;
        int h = array.length - 1;
        int m;
        while (l <= h) {

            // m = mid(l, h, key, array);
            m = mid(l, h);

            if (array[m] > key)
                h = m - 1;
            else if (array[m] < key)
                l = m + 1;
            else
                return m;
        }
        return -1;
    }

    /**
     * <p>折半查找</p>
     * <p>折半算法 (low + high) / 2 = low + ((high - low) / 2)</p>
     *
     * @param low  low
     * @param high high
     * @return mid
     */
    private static int mid(int low, int high) {
        return (low + high) / 2;
    }

    /**
     * <p>插值查找 Interpolation Search, 是根据要查找的关键词KEY与查找表中最大最小记录的关键字比较后的查找方法</p>
     * <p>改进算法 low + ((high - low) * (key - a[low]) / (a[high] - a[low]))</p>
     * <p>适合于关键字分布比较均匀</p>
     *
     * @param low   low
     * @param high  high
     * @param key   key
     * @param array array
     * @return mid
     */
    private static int mid(int low, int high, int key, int[] array) {
        double x = (double) (key - array[low]) / (double) (array[high] - array[low]);
        double y = (high - low) * x;
        System.out.println(x);
        System.out.println(y);

        // array[high] - array[low] == 0 要判断一下
        // 要使用double, 最后再转int
        // FIXME

        return low + (high - low) * (key - array[low]) / (array[high] - array[low]);
    }

    // TODO fibonacci 查找

    private static void fibonacci3(int n) {
        int x = 0;
        int y = 1;
        System.out.println(y);
        for (int i = 2; i < n; i++) {

            int result = x + y;
            x = y;
            y = result;

            System.out.println(result);

        }
    }

    private static void fibonacci2(int n) {
        int[] array = new int[n];
        array[0] = 1;
        array[1] = 1;
        for (int i = 2; i < n; i++) {
            array[i] = array[i - 1] + array[i - 2];
            System.out.println(array[i]);
        }
    }

    private static int fibonacci(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        int[] array = new int[]{99, -1, -16, -21, -35, -47, 59, -62, -73, -88, -99};
        Arrays.sort(array);
        for (int i : array) {
            System.out.printf("%s ", i);
        }
        System.out.println();
        int search = search(array, -1000);
        System.out.println("index : " + search);


//        for (int i = 1; i < 10; i++) {
//            System.out.println(fibonacci(i));
//        }
        fibonacci3(10);
    }
}
