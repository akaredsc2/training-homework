package org.vitaly.week01.karatsuba;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.vitaly.week01.karatsuba.KaratsubaMultiplier.multiply;

/**
 * Created by vitaly on 2017-02-05.
 */
public class KaratsubaMultiplierTest {
    public static final BigInteger FIRST = new BigInteger("12345678987654321");
    public static final BigInteger SECOND = new BigInteger("10203040504030201");

    @Test
    public void multiplyPositiveNumbers() throws Exception {
        assertEquals(FIRST.multiply(SECOND), multiply(FIRST, SECOND));
    }

    @Test
    public void multiplyByZero() throws Exception {
        assertEquals(BigInteger.ZERO, multiply(FIRST, BigInteger.ZERO));
        assertEquals(BigInteger.ZERO, multiply(BigInteger.ZERO, SECOND));
    }

    @Test
    public void multiplyByNegativeNumber() throws Exception {
        assertEquals(FIRST.multiply(SECOND).negate(), multiply(FIRST.negate(), SECOND));
        assertEquals(FIRST.multiply(SECOND).negate(), multiply(FIRST, SECOND.negate()));
        assertEquals(FIRST.multiply(SECOND), multiply(FIRST.negate(), SECOND.negate()));
    }
}