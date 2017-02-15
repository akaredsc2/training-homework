package org.vitaly.week03.lists;

import java.util.Iterator;

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
        checkPosition(position);

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
                header.prev = null;
            }
            size -= 1;
        }
    }

    public void removeLast() {

    }

    @Override
    public void removeAt(int position) {

    }

    @Override
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
    public int positionOf(T data) {
        int i = 0;

        for (T current : this) {
            if (data == null) {
                if (current == null) {
                    return i;
                }
            } else {
                if (data.equals(current)) {
                    return i;
                }
            }
            i += 1;
        }
        return -1;
    }

    @Override
    public T getDataAt(int position) {
        if (position != 0) {
            checkPosition(position - 1);
        } else {
            if (size == 0) {
                throw new IllegalArgumentException("List is empty!");
            }
        }

        Iterator<T> iterator = this.iterator();
        T result =
//                iterator.next()
                null;

        for (int i = 0; i < position; i++) {
            if (iterator.hasNext()) {
                result = iterator.next();
            }
        }

        return result;
    }

    @Override
    public void setDataAt(int position, T data) {
        if (position != 0) {
            checkPosition(position - 1);
        } else {
            if (size == 0) {
                throw new IllegalArgumentException("List is empty!");
            }
        }

        Entry<T> target = getFirst();
        for (int i = 0; i < position - 1; i++) {
            target = target.next;
        }

        target.data = data;
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
