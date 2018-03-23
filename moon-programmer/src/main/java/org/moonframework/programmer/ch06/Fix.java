package org.moonframework.programmer.ch06;

/**
 * @author quzile
 * @version 1.0
 * @since 2018/3/22
 */
public class Fix {

    public static void main(String[] args) {
        // 37 65 4e 6e 6c 66 43 37 52 52 4f 2d 65 50 36 5f 30 41 77 62 42 77

        //  71 4d 4e 4e 70 4b 42 52 56 36 39 43 47 46 67 66 4b 43 49 4d 77

        int[] a = new int[]{0x53, 0x71, 0x4d, 0x4e, 0x4e, 0x70, 0x4b, 0x42, 0x52, 0x56, 0x36, 0x39, 0x43, 0x47, 0x46, 0x67, 0x66, 0x4b, 0x43, 0x49, 0x4d, 0x77};
        for (int i : a) {
            System.out.print((char) i);
        }
        System.out.println();
    }


}
