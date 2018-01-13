package com.moonframework.test;

/**
 * @author quzile
 * @version 1.0
 * @since 2018/1/12
 */
public class RuntimeConstantPoolTests {

    public static void main(String[] args) {
        String s1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(s1.intern() == s1);

        s1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(s1.intern() == s1);

        String s2 = new StringBuilder("ja").append("va").toString();
        System.out.println(s2.intern() == s2);
    }

}
