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
public class Queue<V> {

    private Node<V>[] array;
    private int count, head, tail;
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    @SuppressWarnings("unchecked")
    public Queue(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException();
        }
        this.array = (Node<V>[]) new Node<?>[size];
    }

    public void add(V t) throws InterruptedException {
        lock.lock();
        try {
            while (count == array.length) {
                notFull.await();
            }

            head = head % 10;
            array[head++] = new Node<>(t);


            count++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public V remove() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }

            tail = tail % 10;
            V value = array[tail++].getValue();

            count--;
            notFull.signal();
            return value;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Queue<Integer> queue = new Queue<>(10);
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                    queue.add(i);
                    System.out.println("add " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(800);
                    Object remove = queue.remove();
                    System.out.println("remove " + remove);
                } catch (InterruptedException ignore) {
                }
            }
        }).start();

        TimeUnit.SECONDS.sleep(100);
    }

}
