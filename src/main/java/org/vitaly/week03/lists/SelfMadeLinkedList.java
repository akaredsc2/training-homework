package org.vitaly.week03.lists;

import java.util.Iterator;

/**
 * Created by vitaly on 15.02.17.
 */
public interface SelfMadeLinkedList<T> {
    String LIST_IS_EMPTY = "List is empty!";
    String POSITION_IS_LESS_THAN_ZERO = "Position is less than zero!";
    String POSITION_IS_GREATER_THAN_OR_EQUALS_TO_LIST_SIZE = "Position is greater than or equals to list size!";

    int getSize();

    void insertFirst(T data);

    void insertAt(int position, T data);

    void removeFirst();

    void removeAt(int position);

    boolean contains(T data);

    int positionOf(T data);

    T getDataAt(int position);

    void setDataAt(int position, T data);

    default void checkGreaterThanOrEqualsTo(int position, int value, String errorMessage) {
        if (!(position >= value)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    default void checkLessThan(int position, int value, String errorMessage) {
        if (!(position < value)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    static <T> boolean contains(Iterable<T> list, T data) {
        for (T element : list) {
            if (data == null) {
                if (element == null) {
                    return true;
                }
            } else if (data.equals(element)) {
                return true;
            }
        }
        return false;
    }

    static <T> int positionOf(Iterable<T> list, T data) {
        int i = 0;
        for (T entryData : list) {
            if (data == null) {
                if (entryData == null) {
                    return i;
                }
            } else {
                if (data.equals(entryData)) {
                    return i;
                }
            }
            i += 1;
        }
        return -1;
    }

    static <T> T getDataAt(Iterable<T> list, int position) {
        Iterator<T> iterator = list.iterator();
        T result = iterator.next();

        for (int i = 0; i < position; i++) {
            if (iterator.hasNext()) {
                result = iterator.next();
            }
        }

        return result;
    }
}
