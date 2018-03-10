package org.moonframework.programmer.ch01;

/**
 * @author quzile
 * @version 1.0
 * @since 2018/3/10
 */
public class Node<V> {

    private V value;

    public Node(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }
}
