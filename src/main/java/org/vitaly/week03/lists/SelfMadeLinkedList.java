package org.vitaly.week03.lists;

/**
 * Created by vitaly on 15.02.17.
 */
public interface SelfMadeLinkedList<T> {
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

    static <T> boolean contains(SinglyLinkedList<T> list, T data) {
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

    static <T> int positionOf(SinglyLinkedList<T> list, T data) {
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
}
