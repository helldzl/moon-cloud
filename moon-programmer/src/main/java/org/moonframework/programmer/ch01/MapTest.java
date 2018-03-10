package org.moonframework.programmer.ch01;

import java.util.HashMap;

/**
 * @author quzile
 * @version 1.0
 * @since 2018/3/9
 */
public class MapTest {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>(3, 1);
        map.put("a", "a");
        map.put("b", "b");
        map.put("c", "c");
        map.put("d", "d");
        map.put("e", "e");

        System.out.println(map);
    }

}
