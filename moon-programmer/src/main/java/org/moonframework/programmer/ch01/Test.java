package org.moonframework.programmer.ch01;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author quzile
 * @version 1.0
 * @since 2018/3/6
 */
public class Test {

    private static Lock lock = new Mutex();
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

    public static void main(String[] args) {
        Test.a();

        // A a = new A();
        // System.out.println(a.compareAndSwapName());
    }

//    public static class A {
//        private static final Unsafe unsafe = Unsafe.getUnsafe();
//        private static final long tailOffset;
//        private String name;
//
//        static {
//            try {
//                tailOffset = unsafe.objectFieldOffset
//                        (AbstractQueuedSynchronizer.class.getDeclaredField("name"));
//            } catch (NoSuchFieldException e) {
//                throw new Error(e);
//            }
//        }
//
//        public boolean compareAndSwapName() {
//            return unsafe.compareAndSwapObject(this, tailOffset, "hello", "hello world");
//        }
//    }

}
