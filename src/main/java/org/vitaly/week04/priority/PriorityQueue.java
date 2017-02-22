package org.vitaly.week04.priority;

import java.util.Comparator;

import static org.vitaly.week04.util.QueueChecker.checkElement;
import static org.vitaly.week04.util.QueueChecker.checkIndex;

/**
 * Created by vitaly on 20.02.17.
 */
public class PriorityQueue<T> {
    private static final int INITIAL_CAPACITY = 7;
    private T[] elements;
    private Comparator<? super T> comparator;
    private int heapSize;

    @SuppressWarnings("unchecked")
    public PriorityQueue(Comparator<? super T> comparator) {
        this.elements = (T[]) new Object[INITIAL_CAPACITY];
        this.comparator = comparator;
        this.heapSize = 0;
    }

    public int getArraySize() {
        return elements.length;
    }

    public int getHeapSize() {
        return heapSize;
    }

    public void maintainHeapProperty(int index) {
        checkIndex(index, heapSize);

        int leftIndex = getLeftChildIndex(index);
        int rightIndex = getRightChildIndex(index);
        int largestIndex = index;
        if (leftIndex < heapSize && comparator.compare(elements[leftIndex], elements[index]) > 0) {
            largestIndex = leftIndex;
        }
        if (rightIndex < heapSize && comparator.compare(elements[rightIndex], elements[largestIndex]) > 0) {
            largestIndex = rightIndex;
        }
        if (largestIndex != index) {
            swap(elements, index, largestIndex);
            maintainHeapProperty(largestIndex);
        }
    }

    private void swap(T[] array, int firstPosition, int secondPosition) {
        checkElement(array);
        checkIndex(firstPosition, array.length);
        checkIndex(secondPosition, array.length);

        T temp = array[firstPosition];
        array[firstPosition] = array[secondPosition];
        array[secondPosition] = temp;
    }

    public T peek() {
        return elements[0];
    }

    public T pop() {
        if (heapSize < 1) {
            throw new IllegalArgumentException("Can't pop from empty priority queue!");
        }
        T result = peek();
        elements[0] = elements[heapSize - 1];
        heapSize -= 1;
        maintainHeapProperty(0);
        return result;
    }

    public void increaseKey(int index, T element) {
        checkIndex(index);
        checkElement(element);

        if (comparator.compare(element, elements[index]) < 0) {
            throw new IllegalArgumentException("New key is smaller than current key");
        }
        elements[index] = element;
        int localIndex = index;
        while (localIndex > 0 && comparator.compare(elements[getParentIndex(localIndex)], elements[localIndex]) < 0) {
            swap(elements, localIndex, getParentIndex(localIndex));
            localIndex = getParentIndex(index);
        }
    }

    public void insert(T element) {
        checkElement(element);
        ensureCapacity(heapSize);

        elements[heapSize] = element;
        increaseKey(heapSize, element);
        heapSize += 1;
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity(int currentHeapSize) {
        if (currentHeapSize == elements.length) {
            T[] newArray = (T[]) new Object[elements.length << 1];
            System.arraycopy(elements, 0, newArray, 0, elements.length);
            elements = newArray;
        }
    }

    private int getParentIndex(int index) {
        checkIndex(index);
        return index >> 1;
    }

    private int getLeftChildIndex(int index) {
        checkIndex(index);
        return index << 1;
    }

    private int getRightChildIndex(int index) {
        checkIndex(index);
        return index << 1 | 1;
    }
}
