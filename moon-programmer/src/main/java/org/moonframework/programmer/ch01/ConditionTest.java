package org.moonframework.programmer.ch01;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author quzile
 * @version 1.0
 * @since 2018/3/9
 */
public class ConditionTest {

    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public void condition() throws InterruptedException {
        lock.lock();
        try {
            System.out.println("condition");
        } finally {
            lock.unlock();
        }
    }

    public void conditionWait() throws InterruptedException {
        lock.lock();
        try {
            notEmpty.await();
            System.out.println("wait");
        } finally {
            lock.unlock();
        }
    }

    public void conditionSignal() throws InterruptedException {
        lock.lock();
        try {
            TimeUnit.SECONDS.sleep(5);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionTest test = new ConditionTest();
        new Thread(() -> {
            try {
                test.conditionWait();
            } catch (InterruptedException e) {
            }
        }).start();

        new Thread(() -> {
            try {
                test.condition();
            } catch (InterruptedException e) {
            }
        }).start();

        TimeUnit.MILLISECONDS.sleep(100);

        new Thread(() -> {
            try {
                test.conditionSignal();
            } catch (InterruptedException e) {
            }
        }).start();

        TimeUnit.SECONDS.sleep(10);
        System.out.println("FINISHED");
    }

}
