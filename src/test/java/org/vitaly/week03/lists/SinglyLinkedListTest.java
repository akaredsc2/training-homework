package org.vitaly.week03.lists;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by vitaly on 2017-02-14.
 */
public class SinglyLinkedListTest {
    private SinglyLinkedList<Integer> list;

    @Before
    public void setUp() throws Exception {
        list = new SinglyLinkedList<>();
    }

    @Test
    public void testNewSinglyLinkedList() throws Exception {
        assertEquals(0, list.getSize());
    }

    @Test
    public void insertFirst() throws Exception {
        list.insertFirst(3);
        list.insertFirst(5);
        list.insertFirst(7);
        list.insertFirst(9);
        assertEquals(4, list.getSize());

        Iterator<Integer> iterator = list.iterator();

        assertEquals(9, iterator.next().intValue());
        assertEquals(7, iterator.next().intValue());
        assertEquals(5, iterator.next().intValue());
        assertEquals(3, iterator.next().intValue());
    }

    @Test
    public void insertAfter() throws Exception {
        list.insertAfter(0, 9);
        list.insertAfter(1, 8);
        list.insertAfter(2, 7);
        list.insertAfter(3, 6);
        list.insertAfter(1, 5);
        assertEquals(5, list.getSize());

        Iterator<Integer> iterator = list.iterator();

        assertEquals(9, iterator.next().intValue());
        assertEquals(5, iterator.next().intValue());
        assertEquals(8, iterator.next().intValue());
        assertEquals(7, iterator.next().intValue());
        assertEquals(6, iterator.next().intValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertAfterNegativePosition() throws Exception {
        list.insertAfter(-1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeAfterPositionGreaterThanListSize() throws Exception {
        list.insertAfter(10, 1);
    }

    @Test
    public void removeFirst() throws Exception {
        list.insertFirst(4);
        list.insertFirst(5);
        assertEquals(2, list.getSize());

        list.removeFirst();
        assertEquals(1, list.getSize());

        list.removeFirst();
        assertEquals(0, list.getSize());
    }

    @Test
    public void removeFirstInEmptyList() throws Exception {
        list.removeFirst();
        assertEquals(0, list.getSize());
    }

    @Test
    public void removeAfter() throws Exception {

    }

    @Test
    public void testContains() throws Exception {
        list.insertFirst(5);
        list.insertFirst(6);
        list.insertFirst(7);
        list.insertFirst(8);
        assertTrue(list.contains(8));
        assertTrue(list.contains(6));
        assertFalse(list.contains(99));
        assertFalse(list.contains(1));
    }

    @Test
    public void testContainsNull() throws Exception {
        list.insertFirst(2);
        list.insertFirst(null);
        list.insertFirst(3);
        assertTrue(list.contains(null));
        assertTrue(list.contains(2));
        assertFalse(list.contains(1));
    }

    @Test
    public void testContainsOnEmptyList() throws Exception {
        assertFalse(list.contains(100));
    }
}