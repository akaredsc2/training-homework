package org.vitaly.week03.figures;

import org.junit.Test;

import static java.lang.Double.MAX_VALUE;
import static java.lang.Double.MIN_VALUE;
import static java.lang.Double.POSITIVE_INFINITY;
import static org.junit.Assert.assertEquals;
import static org.vitaly.week03.figures.ShapeTest.PRECISION;
import static org.vitaly.week03.figures.ShapeTest.TRAPEZIUM_AREA;

/**
 * Created by vitaly on 2017-02-14.
 */
public class TrapeziumTest {
    @Test
    public void testZeroSidesAndHeight() throws Exception {
        assertEquals(0, new Trapezium().getArea(), PRECISION);
    }

    @Test
    public void testZeroSides() throws Exception {
        assertEquals(45.0, new Trapezium(0.0, 9.0, 10.0).getArea(), PRECISION);
        assertEquals(40.0, new Trapezium(8.0, 0.0, 10.0).getArea(), PRECISION);
    }

    @Test
    public void testZeroHeight() throws Exception {
        assertEquals(0, new Trapezium(8.0, 9.0, 0.0).getArea(), PRECISION);
    }

    @Test
    public void testMinValueSides() throws Exception {
        assertEquals(45.0, new Trapezium(MIN_VALUE, 9.0, 10.0).getArea(), PRECISION);
        assertEquals(40.0, new Trapezium(8.0, MIN_VALUE, 10.0).getArea(), PRECISION);
        assertEquals(0.0, new Trapezium(MIN_VALUE, MIN_VALUE, 10.0).getArea(), PRECISION);
    }

    @Test
    public void testMinValueHeight() throws Exception {
        assertEquals(0, new Trapezium(8.0, 9.0, MIN_VALUE).getArea(), PRECISION);
    }

    @Test
    public void testRegularSidesAndHeight() throws Exception {
        assertEquals(TRAPEZIUM_AREA, new Trapezium(8.0, 9.0, 10.0).getArea(), PRECISION);
    }

    @Test
    public void testMaxValueSides() throws Exception {
        assertEquals(POSITIVE_INFINITY, new Trapezium(MAX_VALUE, 9.0, 10.0).getArea(), PRECISION);
        assertEquals(POSITIVE_INFINITY, new Trapezium(8.0, MAX_VALUE, 10.0).getArea(), PRECISION);
        assertEquals(POSITIVE_INFINITY, new Trapezium(MAX_VALUE, MAX_VALUE, 10.0).getArea(), PRECISION);
    }

    @Test
    public void testMaxValueHeight() throws Exception {
        assertEquals(POSITIVE_INFINITY, new Trapezium(8.0, 9.0, MAX_VALUE).getArea(), PRECISION);
    }
}