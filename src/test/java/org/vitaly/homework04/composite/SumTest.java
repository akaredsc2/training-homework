package org.vitaly.homework04.composite;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by vitaly on 2017-03-18.
 */
public class SumTest {
    private Number left;
    private Number right;
    private int leftPrimitive;
    private int rightPrimitive;

    @Before
    public void setUp() throws Exception {
        leftPrimitive = 18;
        left = new Number(leftPrimitive);
        rightPrimitive = 3;
        right = new Number(rightPrimitive);
    }

    @Test
    public void sumComponentComputesSumOfItsLeftAndRightOperands() throws Exception {
        Component sum = new Sum(left, right);

        long computationResult = sum.compute();

        assertEquals(computationResult, leftPrimitive + rightPrimitive);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructingSumWithNullLeftComponentShouldThrowException() throws Exception {
        new Sum(null, right);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructingSumWithNullRightComponentShouldThrowException() throws Exception {
        new Sum(left, null);
    }
}