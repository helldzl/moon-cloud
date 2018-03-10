package org.moonframework.programmer.ch04;

import java.util.concurrent.Executors;

/**
 * @author quzile
 * @version 1.0
 * @since 2018/3/10
 */
public class PoolTests {

    public static void main(String[] args) {
        Executors.newFixedThreadPool(2);
        Executors.newSingleThreadExecutor();
        Executors.newCachedThreadPool();
    }

}
