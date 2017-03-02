package org.vitaly.week03.lists;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by vitaly on 15.02.17.
 */

public class DoublyLinkedListTest {
    private DoublyLinkedList<Integer> list;

    @Before
    public void setUp() throws Exception {
        list = new DoublyLinkedList<>();
    }

    @Test
    public void testNewDoublyLinkedList() throws Exception {
        assertEquals(0, list.getSize());
    }

    @Test
    public void testInsertFirst() throws Exception {
        list.insertFirst(1);
        list.insertFirst(2);
        list.insertFirst(3);

        assertEquals(3, list.getSize());
    }

    @Test
    public void testInsertLast() throws Exception {
        list.insertLast(1);
        list.insertLast(2);
        list.insertLast(3);

        assertEquals(3, list.getSize());
    }

    @Test
    public void insertAt() throws Exception {
        list.insertAt(0, 1);
        list.insertAt(1, 2);
        list.insertAt(1, 3);
        list.insertAt(2, null);
        list.insertAt(4, 5);
        assertEquals(5, list.getSize());

        Iterator<Integer> iterator = list.iterator();
        assertEquals(1, iterator.next().intValue());
        assertEquals(3, iterator.next().intValue());
        assertEquals(null, iterator.next());
        assertEquals(2, iterator.next().intValue());
        assertEquals(5, iterator.next().intValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertAtNegativePosition() throws Exception {
        list.insertAt(-10, -10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertAtPositionGreaterThanListSize() throws Exception {
        list.insertAt(10, 10);
    }

    @Test
    public void testContains() throws Exception {
        assertFalse(list.contains(4));
        assertFalse(list.contains(5));
        assertFalse(list.contains(6));

        list.insertFirst(4);
        list.insertFirst(5);
        list.insertFirst(6);

        assertTrue(list.contains(4));
        assertTrue(list.contains(5));
        assertTrue(list.contains(6));


        assertFalse(list.contains(null));
        list.insertFirst(null);
        assertTrue(list.contains(null));
    }

    @Test
    public void testPositionOf() throws Exception {
        assertEquals(-1, list.positionOf(8));
        assertEquals(-1, list.positionOf(7));
        assertEquals(-1, list.positionOf(6));

        list.insertFirst(6);
        list.insertFirst(7);
        list.insertFirst(8);

        assertEquals(0, list.positionOf(8));
        assertEquals(1, list.positionOf(7));
        assertEquals(2, list.positionOf(6));

        assertEquals(-1, list.positionOf(null));
        list.insertFirst(null);
        assertEquals(0, list.positionOf(null));
    }

    @Test
    public void testGetDataAt() throws Exception {
        list.insertFirst(1);
        assertEquals(1, list.getDataAt(0).intValue());

        list.insertFirst(6);
        list.insertFirst(7);
        list.insertFirst(null);
        list.insertFirst(9);

        assertEquals(6, list.getDataAt(3).intValue());
        assertEquals(7, list.getDataAt(2).intValue());
        assertEquals(null, list.getDataAt(1));
        assertEquals(9, list.getDataAt(0).intValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDataAtNegativePosition() throws Exception {
        list.getDataAt(-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDataAtPositionEqualsToListSize() throws Exception {
        list.getDataAt(0);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testGetDataAtPositionGreaterThanListSize() throws Exception {
        list.getDataAt(10);
    }

    @Test
    public void testSetDataAt() throws Exception {
        list.insertFirst(6);
        list.insertFirst(7);

        assertEquals(6, list.getDataAt(1).intValue());
        assertEquals(7, list.getDataAt(0).intValue());

        list.setDataAt(0, 0);
        list.setDataAt(1, 1);

        assertEquals(0, list.getDataAt(0).intValue());
        assertEquals(1, list.getDataAt(1).intValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDataAtNegativePosition() throws Exception {
        list.setDataAt(-10, -10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDataAtPositionEqualsToListSize() throws Exception {
        list.setDataAt(0, 0);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testSetDataAtPositionGreaterThanListSize() throws Exception {
        list.setDataAt(10, 10);
    }

    @Test
    public void testRemoveFirst() throws Exception {
        list.removeFirst();
        assertEquals(0, list.getSize());

        list.insertFirst(10);
        list.insertFirst(20);
        list.insertFirst(30);
        assertEquals(3, list.getSize());
        assertEquals(30, list.getDataAt(0).intValue());

        list.removeFirst();
        assertEquals(2, list.getSize());
        assertEquals(20, list.getDataAt(0).intValue());
    }

    @Test
    public void testRemoveLast() throws Exception {
        list.removeLast();
        assertEquals(0, list.getSize());

        list.insertFirst(10);
        list.insertFirst(20);
        list.insertFirst(30);
        assertEquals(3, list.getSize());
        assertEquals(10, list.getDataAt(2).intValue());

        list.removeLast();
        assertEquals(2, list.getSize());
        assertEquals(20, list.getDataAt(1).intValue());
    }

    @Test
    public void testRemoveAt() throws Exception {
        list.insertFirst(10);
        list.insertFirst(20);
        list.insertFirst(30);

        assertTrue(list.contains(20));
        list.removeAt(1);
        assertEquals(2, list.getSize());
        assertFalse(list.contains(20));

        assertTrue(list.contains(10));
        list.removeAt(1);
        assertEquals(1, list.getSize());
        assertFalse(list.contains(10));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtNegativePosition() throws Exception {
        list.removeAt(-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtPositionEqualToListSize() throws Exception {
        list.removeAt(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtPositionGreaterThanListSize() throws Exception {
        list.removeAt(10);
    }

    @Test
    public void testIterator() throws Exception {
        Iterator<Integer> iterator = list.iterator();
        assertFalse(iterator.hasNext());

        list.insertFirst(10);
        list.insertLast(20);
        list.insertLast(30);

        iterator = list.iterator();
        assertEquals(10, iterator.next().intValue());
        assertEquals(20, iterator.next().intValue());
        assertEquals(30, iterator.next().intValue());
    }



    @Test(expected = ConcurrentModificationException.class)
    public void testConcurrentModification() throws Exception {
        list.insertFirst(50);
        list.insertFirst(20);
        list.insertFirst(30);

        Iterator<Integer> iterator = list.iterator();

        iterator.next();
        iterator.next();
        list.insertFirst(40);
        iterator.next();
    }
}