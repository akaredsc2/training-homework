package org.vitaly.week03.figures;

import org.junit.Before;
import org.junit.Test;

import static java.lang.Math.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by vitaly on 2017-02-14.
 */

public class ShapeTest {
    public static final double PRECISION = 0.0001;
    public static final double RING_AREA = PI;
    public static final double TRIANGLE_AREA = 3 * sqrt(15) / 4;
    public static final double PARALLELOGRAM_AREA = 5 * 6 * abs(sin(toRadians(7)));
    public static final double TRAPEZIUM_AREA = 85;

    private Shape ring;
    private Shape triangle;
    private Shape parallelogram;
    private Shape trapezium;

    @Before
    public void setUp() throws Exception {
        this.ring = Ring.newRing(1);
        this.triangle = Triangle.newTriangle(2, 3, 4);
        this.parallelogram = Parallelogram.newParallelogram(5, 6, 7);
        this.trapezium = Trapezium.newTrapezium(8, 9, 10);
    }

    @Test
    public void addToRing() throws Exception {
        assertEquals(RING_AREA * 2, ring.add(ring), PRECISION);
        assertEquals(RING_AREA + TRIANGLE_AREA, ring.add(triangle), PRECISION);
        assertEquals(RING_AREA + PARALLELOGRAM_AREA, ring.add(parallelogram), PRECISION);
        assertEquals(RING_AREA + TRAPEZIUM_AREA, ring.add(trapezium), PRECISION);
    }

    @Test
    public void addToTriangle() throws Exception {
        assertEquals(TRIANGLE_AREA + RING_AREA, triangle.add(ring), PRECISION);
        assertEquals(TRIANGLE_AREA * 2, triangle.add(triangle), PRECISION);
        assertEquals(TRIANGLE_AREA + PARALLELOGRAM_AREA, triangle.add(parallelogram), PRECISION);
        assertEquals(TRIANGLE_AREA + TRAPEZIUM_AREA, triangle.add(trapezium), PRECISION);
    }

    @Test
    public void addToParallelogram() throws Exception {
        assertEquals(PARALLELOGRAM_AREA + RING_AREA, parallelogram.add(ring), PRECISION);
        assertEquals(PARALLELOGRAM_AREA + TRIANGLE_AREA, parallelogram.add(triangle), PRECISION);
        assertEquals(PARALLELOGRAM_AREA * 2, parallelogram.add(parallelogram), PRECISION);
        assertEquals(PARALLELOGRAM_AREA + TRAPEZIUM_AREA, parallelogram.add(trapezium), PRECISION);
    }

    @Test
    public void addToTrapezium() throws Exception {
        assertEquals(TRAPEZIUM_AREA + RING_AREA, trapezium.add(ring), PRECISION);
        assertEquals(TRAPEZIUM_AREA + TRIANGLE_AREA, trapezium.add(triangle), PRECISION);
        assertEquals(TRAPEZIUM_AREA + PARALLELOGRAM_AREA, trapezium.add(parallelogram), PRECISION);
        assertEquals(TRAPEZIUM_AREA * 2, trapezium.add(trapezium), PRECISION);
    }

    @Test
    public void addNull() throws Exception {
        assertEquals(RING_AREA, ring.add(null), PRECISION);
        assertEquals(TRIANGLE_AREA, triangle.add(null), PRECISION);
        assertEquals(PARALLELOGRAM_AREA, parallelogram.add(null), PRECISION);
        assertEquals(TRAPEZIUM_AREA, trapezium.add(null), PRECISION);
    }
}