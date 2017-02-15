package org.vitaly.week03.lists;

import org.junit.Before;
import org.junit.Test;

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
    public void insertNull() throws Exception {
        list.insertFirst(null);
        list.insertLast(null);

        assertEquals(2, list.getSize());
    }

    @Test
    public void insertAt() throws Exception {
        list.insertAt(0, 1);
        list.insertAt(1, 2);
        list.insertAt(1, 3);

        assertEquals(3, list.getSize());
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
        list.insertFirst(4);
        list.insertFirst(5);
        list.insertFirst(6);

        assertFalse(list.contains(1));
        assertFalse(list.contains(2));
        assertFalse(list.contains(3));

        assertTrue(list.contains(4));
        assertTrue(list.contains(5));
        assertTrue(list.contains(6));
    }

    @Test
    public void testContainsNull() throws Exception {
        list.insertFirst(1);
        list.insertFirst(2);
        list.insertFirst(null);

        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
        assertTrue(list.contains(null));
    }

    @Test
    public void testPositionOf() throws Exception {
        list.insertFirst(6);
        list.insertFirst(7);
        list.insertFirst(8);

        assertEquals(0, list.positionOf(8));
        assertEquals(1, list.positionOf(7));
        assertEquals(2, list.positionOf(6));
    }

    @Test
    public void testPositionOfNull() throws Exception {
        list.insertFirst(null);
        list.insertFirst(1);

        assertEquals(1, list.positionOf(null));
        assertEquals(0, list.positionOf(1));
    }

    @Test
    public void testGetDataAt() throws Exception {
        list.insertFirst(6);
        list.insertFirst(7);
        list.insertFirst(null);
        list.insertFirst(9);

        assertEquals(6, list.getDataAt(4).intValue());
        assertEquals(7, list.getDataAt(3).intValue());
        assertEquals(null, list.getDataAt(2));
        assertEquals(9, list.getDataAt(1).intValue());
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

        assertEquals(6, list.getDataAt(2).intValue());
        assertEquals(7, list.getDataAt(1).intValue());

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

        list.insertFirst(1);
        list.insertFirst(2);

        list.removeFirst();
        assertEquals(1, list.getSize());
        assertEquals(1, list.getDataAt(0).intValue());
    }
}