package org.vitaly.homework04.composite;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by vitaly on 2017-03-18.
 */
public class SubtractionTest {
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
    public void subtractionComponentComputesSumOfItsLeftAndRightOperands() throws Exception {
        Component subtraction = new Subtraction(left, right);

        long computationResult = subtraction.compute();

        assertEquals(computationResult, leftPrimitive - rightPrimitive);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructingSubtractionWithNullLeftComponentShouldThrowException() throws Exception {
        new Subtraction(null, right);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructingSubtractionWithNullRightComponentShouldThrowException() throws Exception {
        new Subtraction(left, null);
    }
}