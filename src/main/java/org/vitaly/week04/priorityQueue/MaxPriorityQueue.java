package org.vitaly.week04.priorityQueue;

import java.util.ArrayList;

/**
 * Created by vitaly on 20.02.17.
 */
public class MaxPriorityQueue<T extends Comparable<T>> {
    private ArrayList<T> elements;
    private int heapSize;

    public MaxPriorityQueue() {
        this.elements = new ArrayList<>();
        this.heapSize = 0;
    }

    public void insert(T element) {

    }

    public T getMaximum() {
        return this.elements.get(0);
    }

    public T removeMaximum() {
        if (heapSize < 1) {
            throw new IllegalArgumentException("Priority queue is empty!");
        }
        T result = getMaximum();
        elements.set(0, elements.get(heapSize - 1));
        heapSize -= 1;
        maxHeapify(0);
        return result;
    }

    public void maxHeapify(int index) {
        int leftIndex = getLeftChildIndex(index);
        int rightIndex = getRightChildIndex(index);

        int largest = 0;
        if (leftIndex <= heapSize);

//        if () {

//        }
    }

    public int getParentIndex(int index) {
        return index / 2;
    }

    public int getLeftChildIndex(int index) {
        return index * 2;
    }

    public int getRightChildIndex(int index) {
        return index * 2 + 1;
    }

    public void increaseKey(T element, int newIndex) {

    }
}
