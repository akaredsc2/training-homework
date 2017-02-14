package org.vitaly.week03.figures;

import org.junit.Test;

import static java.lang.Double.*;
import static org.junit.Assert.assertEquals;
import static org.vitaly.week03.figures.ShapeTest.PRECISION;
import static org.vitaly.week03.figures.ShapeTest.RING_AREA;

/**
 * Created by vitaly on 2017-02-14.
 */
public class RingTest {
    @Test
    public void testZeroRadius() throws Exception {
        assertEquals(0.0, new Ring().getArea(), PRECISION);
    }

    @Test
    public void testMinDoubleRadius() throws Exception {
        assertEquals(0.0, new Ring(MIN_VALUE).getArea(), PRECISION);
    }

    @Test
    public void testRegularRadius() throws Exception {
        assertEquals(RING_AREA, new Ring(1.0).getArea(), PRECISION);
    }

    @Test
    public void testMaxDoubleRadius() throws Exception {
        assertEquals(POSITIVE_INFINITY, new Ring(MAX_VALUE).getArea(), PRECISION);
    }
}