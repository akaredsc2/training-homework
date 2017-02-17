package org.vitaly.week03.lists;

import org.junit.Before;
import org.junit.Test;

import java.nio.charset.CoderMalfunctionError;
import java.util.ConcurrentModificationException;
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
    public void insertAt() throws Exception {
        list.insertAt(0, 9);
        list.insertAt(1, 8);
        list.insertAt(2, 7);
        list.insertAt(3, null);
        list.insertAt(1, 5);
        assertEquals(5, list.getSize());

        Iterator<Integer> iterator = list.iterator();

        assertEquals(9, iterator.next().intValue());
        assertEquals(5, iterator.next().intValue());
        assertEquals(8, iterator.next().intValue());
        assertEquals(7, iterator.next().intValue());
        assertEquals(null, iterator.next());
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertAtNegativePosition() throws Exception {
        list.insertAt(-1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertAtPositionGreaterThanListSize() throws Exception {
        list.insertAt(10, 1);
    }

    @Test
    public void removeFirst() throws Exception {
        list.removeFirst();
        assertEquals(0, list.getSize());

        list.insertFirst(4);
        list.insertFirst(5);
        assertEquals(2, list.getSize());

        list.removeFirst();
        assertEquals(1, list.getSize());
        assertEquals(4, list.iterator().next().intValue());

        list.removeFirst();
        assertEquals(0, list.getSize());
    }

    @Test
    public void removeAt() throws Exception {
        list.insertFirst(1);
        list.insertFirst(2);
        list.insertFirst(3);

        assertTrue(list.contains(2));
        list.removeAt(1);
        assertEquals(2, list.getSize());
        assertFalse(list.contains(2));

        assertTrue(list.contains(1));
        list.removeAt(1);
        assertEquals(1, list.getSize());
        assertFalse(list.contains(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeAfterNegativePosition() throws Exception {
        list.removeAt(-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeAfterPositionGreaterThanListSize() throws Exception {
        list.removeAt(10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeAfterPositionEqualsToListSize() throws Exception {
        list.insertFirst(45);
        list.removeAt(1);
    }

    @Test
    public void testContains() throws Exception {
        assertFalse(list.contains(100));


        assertFalse(list.contains(6));
        assertFalse(list.contains(8));
        list.insertFirst(6);
        list.insertFirst(8);
        assertTrue(list.contains(8));
        assertTrue(list.contains(6));

        assertFalse(list.contains(null));
        list.insertFirst(null);
        assertTrue(list.contains(null));
    }

    @Test
    public void testPositionOf() throws Exception {
        assertEquals(-1, list.positionOf(10));

        list.insertFirst(5);
        list.insertFirst(4);
        list.insertFirst(3);
        assertEquals(2, list.positionOf(5));
        assertEquals(1, list.positionOf(4));
        assertEquals(0, list.positionOf(3));

        list.insertFirst(null);
        assertEquals(0, list.positionOf(null));
    }

    @Test
    public void testGetDataAt() throws Exception {
        list.insertFirst(5);
        list.insertFirst(6);
        list.insertFirst(7);
        list.insertFirst(null);
        list.insertFirst(8);

        assertEquals(5, list.getDataAt(4).intValue());
        assertEquals(6, list.getDataAt(3).intValue());
        assertEquals(7, list.getDataAt(2).intValue());
        assertEquals(null, list.getDataAt(1));
        assertEquals(8, list.getDataAt(0).intValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDataAtNegativePosition() throws Exception {
        list.getDataAt(-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDataAtPositionGreaterThanListSize() throws Exception {
        list.getDataAt(10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDataAtPositionEqualToListSize() throws Exception {
        list.setDataAt(0, 0);
    }

    @Test
    public void testSetDataAt() throws Exception {
        list.insertFirst(6);
        list.insertFirst(7);
        list.insertFirst(8);

        assertEquals(7, list.getDataAt(1).intValue());
        list.setDataAt(1, 9);
        assertEquals(9, list.getDataAt(1).intValue());

        assertEquals(6, list.getDataAt(2).intValue());
        list.setDataAt(2, null);
        assertEquals(null, list.getDataAt(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDataAtNegativePosition() throws Exception {
        list.setDataAt(-10, -10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDataAtPositionGreaterThanListSize() throws Exception {
        list.setDataAt(10, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDataAtPositionEqualToListSize() throws Exception {
        list.setDataAt(0, 0);
    }

    @Test
    public void testIterator() throws Exception {
        Iterator<Integer> iterator = list.iterator();
        assertFalse(iterator.hasNext());

        list.insertFirst(10);
        list.insertFirst(20);
        list.insertFirst(30);
        iterator = list.iterator();
        assertEquals(30, iterator.next().intValue());
        assertEquals(20, iterator.next().intValue());
        assertEquals(10, iterator.next().intValue());

        list.removeFirst();
        iterator = list.iterator();
        assertEquals(20, iterator.next().intValue());
        assertEquals(10, iterator.next().intValue());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testConcurrentModification() throws Exception {
        list.insertFirst(10);
        list.insertFirst(20);
        list.insertFirst(30);

        Iterator<Integer> iterator = list.iterator();

        iterator.next();
        iterator.next();
        list.insertFirst(40);
        iterator.next();
    }
}