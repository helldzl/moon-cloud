package org.moonframework.rx;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author quzile
 * @version 1.0
 * @since 2018/3/2
 */
public class LockTests {

    private static ReentrantLock lock = new ReentrantLock(true);
    private static Condition condition = lock.newCondition();
    private static int i = 0;

    public static void a() {
        lock.lock();
        try {
            System.out.println("lock");
            if (i++ == 0) {
                a();
            }

            System.out.print(1);
        } finally {
            lock.unlock();
        }
    }

    public static class Mutex implements Lock {

        private static class Sync extends AbstractQueuedLongSynchronizer {
            @Override
            protected boolean isHeldExclusively() {
                return getState() == 1;
            }

            @Override
            protected boolean tryAcquire(long arg) {
                if (compareAndSetState(0, 1)) {
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
                return false;
            }

            @Override
            protected boolean tryRelease(long arg) {
                if (getState() == 0) {
                    throw new IllegalMonitorStateException();
                }
                setExclusiveOwnerThread(null);
                setState(0);
                return true;
            }

            Condition newCondition() {
                return new ConditionObject();
            }
        }

        private final Sync sync = new Sync();

        @Override
        public void lock() {
            sync.acquire(1);
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {
            sync.acquireInterruptibly(1);
        }

        @Override
        public boolean tryLock() {
            return sync.tryAcquire(1);
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return sync.tryAcquireNanos(1, unit.toNanos(time));
        }

        @Override
        public void unlock() {
            sync.release(1);
        }

        @Override
        public Condition newCondition() {
            return sync.newCondition();
        }

        public boolean isLocked() {
            return sync.isHeldExclusively();
        }

        public boolean hasQueuedThreads() {
            return sync.hasQueuedThreads();
        }
    }

    public static void main(String[] args) {

        a();


    }

}
