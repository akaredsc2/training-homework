package org.vitaly.week03.figures;

import org.junit.Test;

import static java.lang.Double.MAX_VALUE;
import static java.lang.Double.MIN_VALUE;
import static java.lang.Double.POSITIVE_INFINITY;
import static org.junit.Assert.assertEquals;
import static org.vitaly.week03.figures.ShapeTest.PRECISION;
import static org.vitaly.week03.figures.ShapeTest.TRIANGLE_AREA;

/**
 * Created by vitaly on 2017-02-14.
 */
public class TriangleTest {
    @Test
    public void testZeroSidesTriangle() throws Exception {
        assertEquals(0.0, new Triangle().getArea(), PRECISION);
    }

    @Test
    public void testMinValueSidesTriangle() throws Exception {
        assertEquals(0.0, new Triangle(MIN_VALUE, MIN_VALUE, MIN_VALUE).getArea(), PRECISION);
    }

    @Test
    public void testRegularSidesTriangle() throws Exception {
        assertEquals(TRIANGLE_AREA, new Triangle(2.0, 3.0, 4.0).getArea(), PRECISION);
    }

    @Test
    public void testMaxValueSidesTriangle() throws Exception {
        assertEquals(POSITIVE_INFINITY, new Triangle(MAX_VALUE, MAX_VALUE, MAX_VALUE).getArea(), PRECISION);
    }
}