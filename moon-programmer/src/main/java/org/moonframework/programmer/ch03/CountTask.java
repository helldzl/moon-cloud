package org.moonframework.programmer.ch03;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author quzile
 * @version 1.0
 * @since 2018/3/10
 */
public class CountTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 2;
    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);
            leftTask.fork();
            rightTask.fork();
            sum = leftTask.join() + rightTask.join();
        }

        return sum;
    }

    public static void main(String[] args) throws Exception{
        ForkJoinPool pool = new ForkJoinPool();
        CountTask task = new CountTask(1, 4);

        ForkJoinTask<Integer> submit = pool.submit(task);
        System.out.println(submit.get());

        AtomicInteger i;
    }

}
