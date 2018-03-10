package org.moonframework.rx.concurrent;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.Proxy;

/**
 * @author quzile
 * @version 1.0
 * @since 2018/3/3
 */
public class MultiThread {

    public static A connection() {
        return (A) Proxy.newProxyInstance(B.class.getClassLoader(), new Class<?>[]{A.class}, (proxy, method, args) -> {

            if (method.getName().equals("say")) {
                System.out.println(" world");
            }

            return null;
        });
    }

    public static void main(String[] args) {

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            // System.out.println(threadInfo.getThreadName());
        }
        String result = connection().say("quzile");
        System.out.println(result);
    }

    public interface A {

        String say(String name);
    }

    public class B implements A {
        @Override
        public String say(String name) {
            return "hello " + name;
        }
    }


}
