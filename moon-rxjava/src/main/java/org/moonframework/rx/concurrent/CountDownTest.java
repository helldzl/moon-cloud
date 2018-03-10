package org.moonframework.rx.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author quzile
 * @version 1.0
 * @since 2018/3/3
 */
public class CountDownTest {

    private static CountDownLatch countDownLatch = new CountDownLatch(10);


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new A());
            thread.start();
        }

        System.out.println("all start");

        try {
            countDownLatch.await();

            countDownLatch.await();
        } catch (InterruptedException e) {
        }

        System.out.println("finished");
    }

    public static class A implements Runnable {
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }
    }

}
