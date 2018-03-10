package org.moonframework.programmer.ch02;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author quzile
 * @version 1.0
 * @since 2018/3/10
 */
public class BlockingQueueTest {

    public static void main(String[] args) throws Exception {
        LinkedBlockingDeque<Integer> queue = new LinkedBlockingDeque<>(1);

        for (int i = 0; i < 10; i++) {
            queue.put(1);
            System.out.println(i);
        }

    }
}
