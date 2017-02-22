package org.vitaly.week04.util;

/**
 * Created by vitaly on 22.02.17.
 */
public class QueueChecker {
    private QueueChecker() {

    }
    public static <T> void checkElement(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element of priority queue must not be null!");
        }
    }

    public static void checkIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index is less than zero!");
        }
    }

    public static void checkIndex(int index, int heapSize) {
        checkIndex(index);
        if (index >= heapSize) {
            throw new IllegalArgumentException("Index is greater than or equal to heap size!");
        }
    }
}
