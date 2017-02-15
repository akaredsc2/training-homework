package org.vitaly.week03.lists;

import java.util.Iterator;

/**
 * Created by vitaly on 2017-02-14.
 */
public class SinglyLinkedList<T> implements SelfMadeLinkedList<T>, Iterable<T> {
    private Entry<T> first;
    private int size;

    public SinglyLinkedList() {
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void insertFirst(T data) {
        this.first = new Entry<>(first, data);
        this.size += 1;
    }

    @Override
    public void insertAt(int position, T data) {
        checkGreaterThanOrEqualsTo(position, 0, "Position is less than zero!");
        checkLessThan(position, size + 1, "Position is greater than list size!");

        if (position == 0) {
            insertFirst(data);
        } else {
            Entry<T> currentEntry = first;
            for (int i = 0; i < position - 1; i++) {
                currentEntry = currentEntry.next;
            }

            if (position == this.size) {
                currentEntry.next = new Entry<>(null, data);
            } else {
                Entry<T> newEntry = new Entry<>(currentEntry.next, data);
                currentEntry.next = newEntry;
            }
            size += 1;
        }
    }

    @Override
    public void removeFirst() {
        if (this.size > 0) {
            this.first = this.first.next;
            this.size -= 1;
        }
    }

    @Override
    public void removeAt(int position) {
        checkGreaterThanOrEqualsTo(position, 0, "Position is less than zero!");
        checkLessThan(position, size, "Position is greater than or equals to list size!");

        if (position == 0) {
            removeFirst();
        } else {
            Entry<T> currentEntry = first;
            for (int i = 0; i < position - 1; i++) {
                currentEntry = currentEntry.next;
            }

            Entry<T> target = currentEntry.next;
            currentEntry.next = target.next;
            target.next = null;
            size -= 1;
        }
    }

    @Override
    public boolean contains(T data) {
        return SelfMadeLinkedList.contains(this, data);
    }

    @Override
    public int positionOf(T data) {
        return SelfMadeLinkedList.positionOf(this, data);
    }

    @Override
    public T getDataAt(int position) {
        checkGreaterThanOrEqualsTo(size, 0, "List is empty!");
        checkGreaterThanOrEqualsTo(position, 0, "Position is less than zero!");
        checkLessThan(position, size, "Position is greater than or equals to list size!");

        Iterator<T> iterator = this.iterator();
        T result = iterator.next();

        for (int i = 0; i < position; i++) {
            if (iterator.hasNext()) {
                result = iterator.next();
            }
        }

        return result;
    }

    @Override
    public void setDataAt(int position, T data) {
        checkGreaterThanOrEqualsTo(size, 0, "List is empty!");
        checkGreaterThanOrEqualsTo(position, 0, "Position is less than zero!");
        checkLessThan(position, size, "Position is greater than or equals to list size!");

        if (first != null) {
            Entry<T> target = first;
            for (int i = 0; i < position; i++) {
                target = target.next;
            }

            target.data = data;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Entry<T> current = SinglyLinkedList.this.first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T result = current.data;
                current = current.next;
                return result;
            }
        };
    }

    private static class Entry<T> {
        public Entry<T> next;
        public T data;

        public Entry(Entry<T> next, T data) {
            this.next = next;
            this.data = data;
        }
    }
}
