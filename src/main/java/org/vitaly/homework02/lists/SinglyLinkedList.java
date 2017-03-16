package org.vitaly.homework02.lists;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.vitaly.util.InputChecker.*;

/**
 * Created by vitaly on 2017-02-14.
 */
public class SinglyLinkedList<T> implements SelfMadeLinkedList<T>, Iterable<T> {
    private Entry<T> first;
    private int size;

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
        requireInRange(position, 0, size + 1, "Position must be between zero inclusive and list size inclusive!");

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
        requireInRange(position, 0, size, POSITION_MUST_BE_BETWEEN_ZERO_AND_LIST_SIZE);

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
        requireZeroOrPositiveInteger(size, LIST_IS_EMPTY);
        requireInRange(position, 0, size, POSITION_MUST_BE_BETWEEN_ZERO_AND_LIST_SIZE);

        return SelfMadeLinkedList.getDataAt(this, position);
    }

    @Override
    public void setDataAt(int position, T data) {
        requireZeroOrPositiveInteger(size, LIST_IS_EMPTY);
        requireInRange(position, 0, size, POSITION_MUST_BE_BETWEEN_ZERO_AND_LIST_SIZE);

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
            int size = SinglyLinkedList.this.size;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (SinglyLinkedList.this.getSize() != size)
                    throw new ConcurrentModificationException();
                if (current == null)
                    throw new NoSuchElementException();
                T result = current.data;
                current = current.next;
                return result;
            }
        };
    }

    private static class Entry<T> {
        private Entry<T> next;
        private T data;

        public Entry(Entry<T> next, T data) {
            this.next = next;
            this.data = data;
        }
    }
}
