package org.vitaly.week03.figures;

import org.junit.Test;

import static java.lang.Double.MAX_VALUE;
import static java.lang.Double.MIN_VALUE;
import static java.lang.Double.POSITIVE_INFINITY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.vitaly.week03.figures.ShapeTest.PARALLELOGRAM_AREA;
import static org.vitaly.week03.figures.ShapeTest.PRECISION;

/**
 * Created by vitaly on 2017-02-14.
 */
public class ParallelogramTest {
    @Test
    public void testZeroSidesAndAngle() throws Exception {
        assertEquals(0, new Parallelogram().getArea(), PRECISION);
    }

    @Test
    public void testZeroSides() throws Exception {
        assertEquals(0, new Parallelogram(0, 0, 7.0).getArea(), PRECISION);
        assertEquals(0, new Parallelogram(0, 6.0, 7.0).getArea(), PRECISION);
        assertEquals(0, new Parallelogram(5.0, 0, 7.0).getArea(), PRECISION);
    }

    @Test
    public void testZeroAngle() throws Exception {
        assertEquals(0, new Parallelogram(5.0, 6.0, 0).getArea(), PRECISION);
    }

    @Test
    public void testMinValueSides() throws Exception {
        assertEquals(0, new Parallelogram(MIN_VALUE, 6.0, 7.0).getArea(), PRECISION);
        assertEquals(0, new Parallelogram(5.0, MIN_VALUE, 7.0).getArea(), PRECISION);
        assertEquals(0, new Parallelogram(MIN_VALUE, MIN_VALUE, 7.0).getArea(), PRECISION);
    }

    @Test
    public void testMinValueAngle() throws Exception {
        assertEquals(0, new Parallelogram(5.0, 6.0, MIN_VALUE).getArea(), PRECISION);
    }

    @Test
    public void testRegularSidesAndAngle() throws Exception {
        assertEquals(PARALLELOGRAM_AREA, new Parallelogram(5.0, 6.0, 7.0).getArea(), PRECISION);
    }

    @Test
    public void testMaxValueSides() throws Exception {
        assertEquals(POSITIVE_INFINITY, new Parallelogram(MAX_VALUE, 6.0, 7.0).getArea(), PRECISION);
        assertEquals(POSITIVE_INFINITY, new Parallelogram(5.0, MAX_VALUE, 7.0).getArea(), PRECISION);
        assertEquals(POSITIVE_INFINITY, new Parallelogram(MAX_VALUE, MAX_VALUE, 7.0).getArea(), PRECISION);
    }

    @Test
    public void testMaxValueAngle() throws Exception {
        assertNotEquals(0.0, new Parallelogram(5.0, 6.0, MAX_VALUE).getArea(), PRECISION);
    }
}