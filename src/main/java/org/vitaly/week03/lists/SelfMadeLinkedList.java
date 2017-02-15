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

    default void checkPosition(int position) {
        if (position < 0) {
            throw new IllegalArgumentException("Negative position in list!");
        }
        if (position > getSize()) {
            throw new IllegalArgumentException("Position is greater than list size!");
        }
    }
}
