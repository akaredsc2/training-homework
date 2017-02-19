package org.vitaly.week03.figures;

import org.junit.Ignore;
import org.junit.Test;

import static java.lang.Double.*;
import static org.junit.Assert.assertEquals;
import static org.vitaly.week03.figures.Ring.newRing;
import static org.vitaly.week03.figures.ShapeTest.PRECISION;
import static org.vitaly.week03.figures.ShapeTest.RING_AREA;

/**
 * Created by vitaly on 2017-02-14.
 */

@Ignore(value = "Homework 2 passed")
public class RingTest {
    @Test
    public void testZeroRadius() throws Exception {
        assertEquals(0, newRing(0).getArea(), PRECISION);
    }

    @Test
    public void testMinDoubleRadius() throws Exception {
        assertEquals(0, newRing(MIN_VALUE).getArea(), PRECISION);
    }

    @Test
    public void testRegularRadius() throws Exception {
        assertEquals(RING_AREA, newRing(1).getArea(), PRECISION);
    }

    @Test
    public void testMaxDoubleRadius() throws Exception {
        assertEquals(POSITIVE_INFINITY, newRing(MAX_VALUE).getArea(), PRECISION);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newRingWithNaN() throws Exception {
        newRing(NaN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newRingWithInfinity() throws Exception {
        newRing(POSITIVE_INFINITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newRingWithNegativeNumber() throws Exception {
        newRing(-1);
    }
}