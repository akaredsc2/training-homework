package org.vitaly.week03.lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by vitaly on 15.02.17.
 */
public class DoublyLinkedList<T> implements SelfMadeLinkedList<T>, Iterable<T> {
    private Entry<T> header;
    private int size;

    public DoublyLinkedList() {
        header = new Entry<>(null, null, null);
    }

    @Override
    public int getSize() {
        return this.size;
    }

    private Entry<T> getFirst() {
        return header.prev;
    }

    private Entry<T> getLast() {
        return header.next;
    }

    @Override
    public void insertFirst(T data) {
        Entry<T> newEntry = new Entry<>(null, null, data);
        if (size == 0) {
            header.prev = newEntry;
            header.next = newEntry;
        } else {
            newEntry.next = getFirst();
            getFirst().prev = newEntry;
            header.prev = newEntry;
        }
        size += 1;
    }

    public void insertLast(T data) {
        Entry<T> newEntry = new Entry<>(null, null, data);
        if (size == 0) {
            header.prev = newEntry;
            header.next = newEntry;
        } else {
            newEntry.prev = getLast();
            getLast().next = newEntry;
            header.next = newEntry;
        }
        size += 1;
    }

    @Override
    public void insertAt(int position, T data) {
        checkGreaterThanOrEqualsTo(position, 0, POSITION_IS_LESS_THAN_ZERO);
        checkLessThan(position, size + 1, POSITION_IS_GREATER_THAN_OR_EQUALS_TO_LIST_SIZE);

        if (position == 0) {
            insertFirst(data);
        } else if (position == this.size) {
            insertLast(data);
        } else {
            Entry<T> newEntry = new Entry<>(null, null, data);
            Entry<T> currentEntry = getFirst();
            for (int i = 0; i < position - 1; i++) {
                currentEntry = currentEntry.next;
            }
            newEntry.prev = currentEntry;
            newEntry.next = currentEntry.next;
            currentEntry.next = newEntry;
            newEntry.next.prev = newEntry;
            size += 1;
        }
    }

    @Override
    public void removeFirst() {
        if (size != 0) {
            if (size == 1) {
                header.prev = null;
                header.next = null;
            } else {
                header.prev = header.prev.next;
                header.prev.prev = null;
            }
            size -= 1;
        }
    }

    public void removeLast() {
        if (size != 0) {
            if (size == 1) {
                header.prev = null;
                header.next = null;
            } else {
                header.next = header.next.prev;
                header.next.next = null;
            }
            size -= 1;
        }
    }

    @Override
    public void removeAt(int position) {
        checkGreaterThanOrEqualsTo(position, 0, POSITION_IS_LESS_THAN_ZERO);
        checkLessThan(position, size, POSITION_IS_GREATER_THAN_OR_EQUALS_TO_LIST_SIZE);

        if (position == 0) {
            removeFirst();
        } else if (position == size - 1) {
            removeLast();
        } else {
            Entry<T> currentEntry = getFirst();
            for (int i = 0; i < position - 1; i++) {
                currentEntry = currentEntry.next;
            }
            Entry<T> target = currentEntry.next;

            target.prev = null;
            currentEntry.next = target.next;
            target.next = null;
            currentEntry.next.prev = currentEntry;
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
        checkGreaterThanOrEqualsTo(size, 0, LIST_IS_EMPTY);
        checkGreaterThanOrEqualsTo(position, 0, POSITION_IS_LESS_THAN_ZERO);
        checkLessThan(position, size, POSITION_IS_GREATER_THAN_OR_EQUALS_TO_LIST_SIZE);

        return SelfMadeLinkedList.getDataAt(this, position);
    }

    @Override
    public void setDataAt(int position, T data) {
        checkGreaterThanOrEqualsTo(size, 0, LIST_IS_EMPTY);
        checkGreaterThanOrEqualsTo(position, 0, POSITION_IS_LESS_THAN_ZERO);
        checkLessThan(position, size, POSITION_IS_GREATER_THAN_OR_EQUALS_TO_LIST_SIZE);

        Entry<T> first = getFirst();
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
            DoublyLinkedList.Entry<T> current = DoublyLinkedList.this.getFirst();

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                T result = current.data;
                current = current.next;
                return result;
            }
        };
    }


    private static class Entry<T> {
        public Entry<T> next;
        public Entry<T> prev;
        public T data;

        public Entry(Entry<T> next, Entry<T> prev, T data) {
            this.next = next;
            this.prev = prev;
            this.data = data;
        }
    }
}
