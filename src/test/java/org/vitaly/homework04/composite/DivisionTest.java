package org.vitaly.homework04.composite;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by vitaly on 2017-03-18.
 */
public class DivisionTest {
    private Number numerator;
    private Number denominator;
    private int numeratorPrimitive;
    private int denominatorPrimitive;

    @Before
    public void setUp() throws Exception {
        numeratorPrimitive = 18;
        numerator = new Number(numeratorPrimitive);
        denominatorPrimitive = 3;
        denominator = new Number(denominatorPrimitive);
    }

    @Test
    public void divisionComponentComputesSumOfItsLeftAndRightOperands() throws Exception {
        Component division = new Division(numerator, denominator);

        long computationResult = division.compute();

        assertEquals(computationResult, numeratorPrimitive / denominatorPrimitive);
    }

    @Test(expected = ArithmeticException.class)
    public void computingDivisionByZeroShouldThrowException() throws Exception {
        new Division(numerator, new Number(0)).compute();
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructingDivisionWithNullLeftComponentShouldThrowException() throws Exception {
        new Division(null, denominator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructingDivisionWithNullRightComponentShouldThrowException() throws Exception {
        new Division(numerator, null);
    }
}