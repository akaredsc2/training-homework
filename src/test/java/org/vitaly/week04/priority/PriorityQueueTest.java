package org.vitaly.week04.priority;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Created by vitaly on 2017-02-20.
 */
public class PriorityQueueTest {
    private PriorityQueue<Integer> priorityQueue;

    @Before
    public void setUp() throws Exception {
        this.priorityQueue = new PriorityQueue<>(Integer::compareTo);
        priorityQueue.insert(1);
        priorityQueue.insert(2);
        priorityQueue.insert(3);

    }

    @Test
    public void testIncreaseKey() throws Exception {
        priorityQueue.increaseKey(1, 4);
        int actual = priorityQueue.pop();

        int expected = 4;
        assertThat(actual, equalTo(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncreaseKeySmallerThanCurrent() throws Exception {
        priorityQueue.increaseKey(1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncreaseKeyWithNullElement() throws Exception {
        priorityQueue.increaseKey(1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncreaseKeyWithWrongIndex() throws Exception {
        priorityQueue.increaseKey(-1, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void maintainHeapPropertyWithNegativeIndex() throws Exception {
        priorityQueue.maintainHeapProperty(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void maintainHeapPropertyWithTooBigIndex() throws Exception {
        priorityQueue.maintainHeapProperty(3);
    }

    @Test
    public void testInsert() throws Exception {
        int actual = priorityQueue.getHeapSize();

        Integer expected = 3;
        assertThat(actual, equalTo(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertNull() throws Exception {
        priorityQueue.insert(null);
    }

    @Test
    public void testPeek() throws Exception {
        Integer actual = priorityQueue.peek();

        int expected = 3;
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testPeekOnEmptyQueue() throws Exception {
        priorityQueue = new PriorityQueue<>(Integer::compareTo);

        Integer actual = priorityQueue.peek();

        assertNull(actual);
    }

    @Test
    public void testPop() throws Exception {
        priorityQueue.pop();
        Integer actual = priorityQueue.pop();

        int expected = 2;
        assertThat(actual, equalTo(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPopOnEmptyQueue() throws Exception {
        priorityQueue = new PriorityQueue<>(Integer::compareTo);

        priorityQueue.pop();
    }

    @Test
    public void testEnsureCapacity() throws Exception {
        priorityQueue.insert(40);
        priorityQueue.insert(50);
        priorityQueue.insert(60);
        priorityQueue.insert(70);
        priorityQueue.insert(80);
        priorityQueue.insert(90);

        int actual = priorityQueue.getArraySize();

        int expected = 14;
        assertThat(actual, equalTo(expected));
    }
}