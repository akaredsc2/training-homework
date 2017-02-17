package org.vitaly.week03.modeling;

import org.junit.Test;

import java.math.BigInteger;

import static java.math.BigInteger.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.vitaly.week03.modeling.Number.createNumber;
import static org.vitaly.week03.modeling.SimpleFraction.createSimpleFraction;

/**
 * Created by vitaly on 2017-02-17.
 */
public class SimpleFractionTest {
    private static final BigInteger THREE = valueOf(3);
    private static final BigInteger FIVE = valueOf(5);
    private static final BigInteger EIGHT = valueOf(8);
    private static final BigInteger FIFTEEN = valueOf(15);

    @Test
    public void add() throws Exception {
        SimpleFraction first = createSimpleFraction(createNumber(THREE), createNumber(FIVE));
        SimpleFraction second = createSimpleFraction(createNumber(valueOf(7)), createNumber(EIGHT));

        SimpleFraction result = first.add(second);
        assertEquals(valueOf(59), result.getNumerator().getValue());
        assertEquals(valueOf(40), result.getDenominator().getValue());

        first = createSimpleFraction(createNumber(THREE), createNumber(FIVE));
        second = createSimpleFraction(createNumber(ZERO), createNumber(ONE));

        result = first.add(second);
        assertEquals(THREE, result.getNumerator().getValue());
        assertEquals(FIVE, result.getDenominator().getValue());
    }

    @Test
    public void subtract() throws Exception {
        SimpleFraction first = createSimpleFraction(createNumber(THREE), createNumber(FIVE));
        SimpleFraction second = createSimpleFraction(createNumber(ONE), createNumber(FIFTEEN));

        SimpleFraction result = first.subtract(second);
        assertEquals(EIGHT, result.getNumerator().getValue());
        assertEquals(FIFTEEN, result.getDenominator().getValue());

        first = createSimpleFraction(createNumber(THREE), createNumber(FIVE));
        second = createSimpleFraction(createNumber(ZERO), createNumber(ONE));

        result = first.subtract(second);
        assertEquals(THREE, result.getNumerator().getValue());
        assertEquals(FIVE, result.getDenominator().getValue());
    }

    @Test
    public void multiply() throws Exception {
        SimpleFraction first = createSimpleFraction(createNumber(THREE), createNumber(FIVE));
        SimpleFraction second = createSimpleFraction(createNumber(ONE), createNumber(FIFTEEN));

        SimpleFraction result = first.multiply(second);
        assertEquals(ONE, result.getNumerator().getValue());
        assertEquals(BigInteger.valueOf(25), result.getDenominator().getValue());

        first = createSimpleFraction(createNumber(THREE), createNumber(FIVE));
        second = createSimpleFraction(createNumber(ZERO), createNumber(ONE));

        result = first.multiply(second);
        assertEquals(ZERO, result.getNumerator().getValue());
        assertNotEquals(ZERO, result.getDenominator().getValue());
    }

    @Test
    public void divide() throws Exception {
        SimpleFraction first = createSimpleFraction(createNumber(THREE), createNumber(FIVE));
        SimpleFraction second = createSimpleFraction(createNumber(ONE), createNumber(FIFTEEN));

        SimpleFraction result = first.divide(second);
        assertEquals(BigInteger.valueOf(9), result.getNumerator().getValue());
        assertEquals(ONE, result.getDenominator().getValue());
    }

    @Test(expected = ArithmeticException.class)
    public void divideByZero() throws Exception {
        SimpleFraction first = createSimpleFraction(createNumber(THREE), createNumber(FIVE));
        SimpleFraction second = createSimpleFraction(createNumber(ZERO), createNumber(FIFTEEN));

        first.divide(second);
    }

    @Test
    public void simplify() throws Exception {
        Number numerator = createNumber(THREE);
        Number denominator = createNumber(FIFTEEN);

        SimpleFraction simplifiedSimpleFraction = SimpleFraction.simplify(numerator, denominator);

        assertEquals(ONE, simplifiedSimpleFraction.getNumerator().getValue());
        assertEquals(FIVE, simplifiedSimpleFraction.getDenominator().getValue());
    }

    @Test
    public void testCreateSimpleFraction() throws Exception {
        Number numerator = createNumber(ONE);
        Number denominator = createNumber(TEN);

        SimpleFraction simpleFraction = createSimpleFraction(numerator, denominator);
        assertEquals(ONE, simpleFraction.getNumerator().getValue());
        assertEquals(TEN, simpleFraction.getDenominator().getValue());
    }

    @Test
    public void createSimpleFractionWithNull() throws Exception {
        SimpleFraction fraction = createSimpleFraction(null, null);
        assertEquals(ZERO, fraction.getNumerator().getValue());
        assertEquals(ONE, fraction.getDenominator().getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createSimpleFractionDivideByZero() throws Exception {
        createSimpleFraction(createNumber(TEN), createNumber(ZERO));
    }

    @Test
    public void testToString() throws Exception {
        SimpleFraction first = createSimpleFraction(createNumber(THREE), createNumber(FIVE));
        SimpleFraction second = createSimpleFraction(createNumber(ONE), createNumber(FIFTEEN));

        assertEquals("3/5", first.toString());
        assertEquals("1/15", second.toString());
    }
}