package org.vitaly.homework04.composite;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by vitaly on 2017-03-18.
 */
public class MultiplicationTest {
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
    public void multiplicationComponentComputesSumOfItsLeftAndRightOperands() throws Exception {
        Component multiplication = new Multiplication(left, right);

        long computationResult = multiplication.compute();

        assertEquals(computationResult, leftPrimitive * rightPrimitive);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructingMultiplicationWithNullLeftComponentShouldThrowException() throws Exception {
        new Multiplication(null, right);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructingMultiplicationWithNullRightComponentShouldThrowException() throws Exception {
        new Multiplication(left, null);
    }
}