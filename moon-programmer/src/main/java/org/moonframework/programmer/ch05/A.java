package org.moonframework.programmer.ch05;

/**
 * @author quzile
 * @version 1.0
 * @since 2018/3/13
 */
public class A {

    public static int max(int a, int b) {
        return (a >= b) ? a : b;
    }

    public static int max(int a, int b, int c) {
        if (a >= b) {
            return a;
        } else {
            return b;
        }
    }

    public static int maxAdd(int a[], int n) {
        int sumV, maxV;
        // int start = 0, end = 0;
        sumV = maxV = a[0];
        for (int i = 1; i < n; i++) {
            int s = a[i] + sumV;
            int c = a[i];
            if (c >= s) {
                sumV = c;
//                if (i <= end) {
//                    start = i;
//                }
            } else {
                sumV = s;
            }

            // sumV = max(a[i], a[i] + sumV);
            if (sumV > maxV) {
                maxV = sumV;
                //end = i;
            }
            // maxV = max(sumV, maxV);
        }
//        System.out.println(start);
//        System.out.println(end);
        return maxV;
    }

    private static int max1(int a[]) {
        int len = a.length;
        int sum = a[0];
        int max = sum;
        int head = 0;
        int tail = 0;
        for (int i = 1; i < len; i++) {
            int x = a[i];
            int y = a[i] + sum;
            boolean restart = false;
            if (x >= y) {
                sum = x;
                restart = true;
            } else {
                sum = y;
            }
            if (max < sum) {
                max = sum;
                tail = i;
            }
            if (i <= tail && restart) {
                head = i;
            }
        }

        System.out.println("head " + head);
        System.out.println("tail " + tail);
        return max;
    }


    private static int max2(int a[]) {
        int len = a.length;
        int sum = a[0];
        int max = sum;
        int tail = 0;
        int head = 0;
        for (int i = 1; i < len; i++) {
            sum = Math.max(a[i] + sum, a[i]);
            if (max < sum) {
                max = sum;
                tail = i;
            }
        }

        sum = 0;
        for (int i = tail; i >= 0; i--) {
            sum += a[i];
            if (sum == max) {
                head = i;
                break;
            }
        }

        System.out.println("head " + head);
        System.out.println("tail " + tail);
        return max;
    }

    public static void main(String[] args) {
        int[] a = new int[]{-1, -101, -50, -51, 2, 7, -2, 3, 6, 8, 4, -28, 4};
        int b = max2(a);
        System.out.println(b);

        System.getProperties().forEach((o, o2) -> {
            System.out.println(String.format("%s, %s", o, o2));
        });

    }
}
