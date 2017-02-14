package org.vitaly.week03.figures;

import org.junit.Test;

import static java.lang.Double.*;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.vitaly.week03.figures.ShapeTest.PRECISION;
import static org.vitaly.week03.figures.ShapeTest.TRIANGLE_AREA;
import static org.vitaly.week03.figures.Triangle.isTriangleInequalityCorrect;
import static org.vitaly.week03.figures.Triangle.newTriangle;

/**
 * Created by vitaly on 2017-02-14.
 */
public class TriangleTest {
    @Test
    public void testZeroSidesTriangle() throws Exception {
        assertEquals(0, newTriangle(0, 0, 0).getArea(), PRECISION);
    }

    @Test
    public void testMinValueSidesTriangle() throws Exception {
        assertEquals(0, newTriangle(MIN_VALUE, MIN_VALUE, MIN_VALUE).getArea(), PRECISION);
    }

    @Test
    public void testRegularSidesTriangle() throws Exception {
        assertEquals(TRIANGLE_AREA, newTriangle(2, 3, 4).getArea(), PRECISION);
    }

    @Test
    public void testMaxValueSidesTriangle() throws Exception {
        assertEquals(POSITIVE_INFINITY, newTriangle(MAX_VALUE, MAX_VALUE, MAX_VALUE).getArea(), PRECISION);
    }

    @Test
    public void testTriangleInequality() throws Exception {
        assertTrue(isTriangleInequalityCorrect(0, 0, 0));
        assertTrue(isTriangleInequalityCorrect(1, 2, 3));

        assertFalse(isTriangleInequalityCorrect(2, 4, 8));
        assertFalse(isTriangleInequalityCorrect(0, 2, 3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void newTriangleWithNaNSide() throws Exception {
        newTriangle(NaN, 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newTriangleWithInfiniteSide() throws Exception {
        newTriangle(POSITIVE_INFINITY, 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newTriangleWithNegativeSide() throws Exception {
        newTriangle(-1, 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newTriangleWithSidesNotSatisfyingTriangleInequality() throws Exception {
        newTriangle(1, 2, 10);
    }
}