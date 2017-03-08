package org.vitaly.homework02.figures;

import org.junit.Test;

import static java.lang.Double.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.vitaly.homework02.figures.Parallelogram.newParallelogram;
import static org.vitaly.homework02.figures.ShapeTest.PARALLELOGRAM_AREA;
import static org.vitaly.homework02.figures.ShapeTest.PRECISION;

/**
 * Created by vitaly on 2017-02-14.
 */

public class ParallelogramTest {
    @Test
    public void testZeroSidesAndAngle() throws Exception {
        assertEquals(0, newParallelogram(0, 0, 0).getArea(), PRECISION);
    }

    @Test
    public void testZeroSides() throws Exception {
        assertEquals(0, newParallelogram(0, 0, 7).getArea(), PRECISION);
        assertEquals(0, newParallelogram(0, 6, 7).getArea(), PRECISION);
        assertEquals(0, newParallelogram(5, 0, 7).getArea(), PRECISION);
    }

    @Test
    public void testZeroAngle() throws Exception {
        assertEquals(0, newParallelogram(5, 6, 0).getArea(), PRECISION);
    }

    @Test
    public void testMinValueSides() throws Exception {
        assertEquals(0, newParallelogram(MIN_VALUE, 6, 7).getArea(), PRECISION);
        assertEquals(0, newParallelogram(5, MIN_VALUE, 7).getArea(), PRECISION);
        assertEquals(0, newParallelogram(MIN_VALUE, MIN_VALUE, 7).getArea(), PRECISION);
    }

    @Test
    public void testMinValueAngle() throws Exception {
        assertEquals(0, newParallelogram(5, 6, MIN_VALUE).getArea(), PRECISION);
    }

    @Test
    public void testRegularSidesAndAngle() throws Exception {
        assertEquals(PARALLELOGRAM_AREA, newParallelogram(5, 6, 7).getArea(), PRECISION);
    }

    @Test
    public void testMaxValueSides() throws Exception {
        assertEquals(POSITIVE_INFINITY, newParallelogram(MAX_VALUE, 6, 7).getArea(), PRECISION);
        assertEquals(POSITIVE_INFINITY, newParallelogram(5, MAX_VALUE, 7).getArea(), PRECISION);
        assertEquals(POSITIVE_INFINITY, newParallelogram(MAX_VALUE, MAX_VALUE, 7).getArea(), PRECISION);
    }

    @Test
    public void testMaxValueAngle() throws Exception {
        assertNotEquals(0.0, newParallelogram(5, 6, MAX_VALUE).getArea(), PRECISION);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newParallelogramWithNaNSide() throws Exception {
        newParallelogram(NaN, 1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newParallelogramWithInfiniteSide() throws Exception {
        newParallelogram(POSITIVE_INFINITY, 1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newParallelogramWithNaNAngle() throws Exception {
        newParallelogram(1, 2, NaN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newParallelogramWithInfiniteAngle() throws Exception {
        newParallelogram(1, 2, POSITIVE_INFINITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newParallelogramWithNegativeSide() throws Exception {
        newParallelogram(-1, 1, 2);
    }
}