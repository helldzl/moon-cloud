package org.moonframework.programmer.ch02;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author quzile
 * @version 1.0
 * @since 2018/3/10
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(16, 1);
        for (int i = 0; i < 10000; i++) {
            map.put(i + "", (i * i) + "");
        }

        map.get(1 + "");
        map.get(2 + "");
    }

}
