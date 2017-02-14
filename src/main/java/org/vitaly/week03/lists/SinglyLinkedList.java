package org.vitaly.week03.lists;

import java.util.Iterator;

/**
 * Created by vitaly on 2017-02-14.
 */
public class SinglyLinkedList<T> implements Iterable<T> {
    private Entry<T> first;
    private int size;

    public SinglyLinkedList() {
    }

    public int getSize() {
        return size;
    }

    public void insertFirst(T data) {
        this.first = new Entry<>(first, data);
        this.size += 1;
    }

    public void insertAfter(int position, T data) {
        if (position < 0) {
            throw new IllegalArgumentException("Negative position in list!");
        }
        if (position > this.size) {
            throw new IllegalArgumentException("Position is greater than list size!");
        }

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

    public void removeFirst() {
        if (this.size > 0) {
            this.first = this.first.next;
            this.size -= 1;
        }
    }

    public void removeAfter(int position) {

    }

    public boolean contains(T data) {
        for (T element : this) {
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SinglyLinkedList{");
        for (T element : this) {
            builder.append(element)
                    .append(" ");
        }
        return builder.append("\b}").toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Entry<T> current = new Entry<>(SinglyLinkedList.this.first, null);

            @Override
            public boolean hasNext() {
                return current != null && current.next != null;
            }

            @Override
            public T next() {
                T result = current.next.data;
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
