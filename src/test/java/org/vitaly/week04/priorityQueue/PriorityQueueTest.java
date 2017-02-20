package org.vitaly.week04.priorityQueue;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by vitaly on 2017-02-20.
 */
public class PriorityQueueTest {
    @Test
    public void testInsert() throws Exception {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Integer::compareTo);
        priorityQueue.insert(1);
        priorityQueue.insert(2);
        priorityQueue.insert(3);

        int actual = priorityQueue.getHeapSize();

        Integer expected = 3;
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testPeek() throws Exception {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Integer::compareTo);
        priorityQueue.insert(1);
        priorityQueue.insert(2);
        priorityQueue.insert(3);

        Integer actual = priorityQueue.peek();

        int expected = 3;
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testPop() throws Exception {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Integer::compareTo);
        priorityQueue.insert(1);
        priorityQueue.insert(2);
        priorityQueue.insert(3);

        priorityQueue.pop();
        Integer actual = priorityQueue.pop();

        int expected = 2;
        assertThat(actual, equalTo(expected));
    }
}