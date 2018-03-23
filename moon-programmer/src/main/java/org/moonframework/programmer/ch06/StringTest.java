package org.moonframework.programmer.ch06;

/**
 * <p>KMP 模式匹配算法</p>
 *
 * @author quzile
 * @version 1.0
 * @since 2018/3/20
 */
public class StringTest {

    /**
     * @param s 字符串
     * @return next数组
     */
    public static int[] next(String s) {
        int[] next = new int[s.length()];

        int i = 0;
        int j = -1;
        next[0] = -1;
        while (i < next.length - 1) {
            debug(i);

            if (j == -1 || s.charAt(i) == s.charAt(j)) {
                // s.charAt(i) 表示后缀单个字符
                // s.charAt(j) 表示前缀单个字符
                ++i;
                ++j;
                next[i] = j;
            } else {
                // j值回溯
                j = next[j];
            }
        }

        return next;
    }

    public static int[] nextVal(String s) {
        int[] next = new int[s.length()];

        int i = 0;
        int j = -1;
        next[0] = -1;
        while (i < next.length - 1) {
            debug(i);

            if (j == -1 || s.charAt(i) == s.charAt(j)) {
                // s.charAt(i) 表示后缀单个字符
                // s.charAt(j) 表示前缀单个字符
                ++i;
                ++j;

                if (s.charAt(i) != s.charAt(j)) {
                    next[i] = j;
                } else {
                    next[i] = next[j];
                }
            } else {
                // j值回溯
                j = next[j];
            }
        }

        return next;
    }

    public static int indexOf(String source, String target) {
        int i = 0;  // 主串当前下标值
        int j = 0;  // 子串当前下标值

        int[] next = next(target);
        while (i < source.length() && j < target.length()) {
            debug(i);

            if (j == -1 || source.charAt(i) == target.charAt(j)) {
                ++i;
                ++j;
            } else {
                j = next[j]; // j回退到合适位置, i不变, j = next[j], 即让target模式串右移j-next[j]个位置
            }
        }
        if (j == target.length())
            return i - j;
        return -1;
    }

    private static void debug(int i) {
        if (i == 6) {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String s = "aaaaax";
        for (int next : next(s)) {
            System.out.print(next);
        }

        System.out.println();

        for (int next : nextVal(s)) {
            System.out.print(next);
        }

        System.out.println();
        System.out.println(indexOf("abcabxxe12xxx3", "xxx"));
    }

}
