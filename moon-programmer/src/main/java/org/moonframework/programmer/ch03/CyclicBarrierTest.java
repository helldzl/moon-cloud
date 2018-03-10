package org.moonframework.programmer.ch03;

import java.util.concurrent.CyclicBarrier;

/**
 * @author quzile
 * @version 1.0
 * @since 2018/3/10
 */
public class CyclicBarrierTest {

    private static CyclicBarrier c = new CyclicBarrier(2, () -> {
        System.out.println("action");
    });

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                c.await();
                System.out.println("1");
            } catch (Exception ignore) {
            }
        }).start();

        try {
            c.await();
            System.out.println("2");
        } catch (Exception ignore) {
        }
    }

}
