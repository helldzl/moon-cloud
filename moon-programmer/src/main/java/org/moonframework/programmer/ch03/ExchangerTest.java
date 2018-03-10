package org.moonframework.programmer.ch03;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author quzile
 * @version 1.0
 * @since 2018/3/10
 */
public class ExchangerTest {

    private static final Exchanger<String> exchange = new Exchanger<>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(() -> {
            String a = "aa";
            try {
                String exchange = ExchangerTest.exchange.exchange(a);
                System.out.println("im a, receive b value : " + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadPool.execute(() -> {
            String bb = "bb";
            try {
                String exchange = ExchangerTest.exchange.exchange(bb);
                System.out.println("im b, receive a value : " + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
