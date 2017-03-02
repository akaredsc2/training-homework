package org.vitaly.week03.figures;

import org.junit.Test;

import static java.lang.Double.*;
import static org.junit.Assert.assertEquals;
import static org.vitaly.week03.figures.ShapeTest.PRECISION;
import static org.vitaly.week03.figures.ShapeTest.TRAPEZIUM_AREA;
import static org.vitaly.week03.figures.Trapezium.newTrapezium;

/**
 * Created by vitaly on 2017-02-14.
 */

public class TrapeziumTest {
    @Test
    public void testZeroSidesAndHeight() throws Exception {
        assertEquals(0, newTrapezium(0, 0, 0).getArea(), PRECISION);
    }

    @Test
    public void testZeroSides() throws Exception {
        assertEquals(45, newTrapezium(0, 9, 10).getArea(), PRECISION);
        assertEquals(40, newTrapezium(8, 0, 10).getArea(), PRECISION);
    }

    @Test
    public void testZeroHeight() throws Exception {
        assertEquals(0, newTrapezium(8, 9, 0).getArea(), PRECISION);
    }

    @Test
    public void testMinValueSides() throws Exception {
        assertEquals(45, newTrapezium(MIN_VALUE, 9, 10).getArea(), PRECISION);
        assertEquals(40, newTrapezium(8, MIN_VALUE, 10).getArea(), PRECISION);
        assertEquals(0, newTrapezium(MIN_VALUE, MIN_VALUE, 10).getArea(), PRECISION);
    }

    @Test
    public void testMinValueHeight() throws Exception {
        assertEquals(0, newTrapezium(8, 9, MIN_VALUE).getArea(), PRECISION);
    }

    @Test
    public void testRegularSidesAndHeight() throws Exception {
        assertEquals(TRAPEZIUM_AREA, newTrapezium(8, 9, 10).getArea(), PRECISION);
    }

    @Test
    public void testMaxValueSides() throws Exception {
        assertEquals(POSITIVE_INFINITY, newTrapezium(MAX_VALUE, 9, 10).getArea(), PRECISION);
        assertEquals(POSITIVE_INFINITY, newTrapezium(8, MAX_VALUE, 10).getArea(), PRECISION);
        assertEquals(POSITIVE_INFINITY, newTrapezium(MAX_VALUE, MAX_VALUE, 10).getArea(), PRECISION);
    }

    @Test
    public void testMaxValueHeight() throws Exception {
        assertEquals(POSITIVE_INFINITY, newTrapezium(8, 9, MAX_VALUE).getArea(), PRECISION);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newTrapeziumWithNaNSide() throws Exception {
        newTrapezium(NaN, 1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newTrapeziumWithInfiniteSide() throws Exception {
        newTrapezium(POSITIVE_INFINITY, 1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newTrapeziumWithNaNHeight() throws Exception {
        newTrapezium(1, 2, NaN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newTrapeziumWithInfiniteHeight() throws Exception {
        newTrapezium(1, 2, POSITIVE_INFINITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newTrapeziumWithNegativeSide() throws Exception {
        newTrapezium(-1, 2, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newTrapeziumWithNegativeHeight() throws Exception {
        newTrapezium(1, 2, -3);
    }
}