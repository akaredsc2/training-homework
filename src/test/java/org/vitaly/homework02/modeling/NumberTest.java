package org.vitaly.homework02.modeling;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

/**
 * Created by vitaly on 2017-02-17.
 */

public class NumberTest {
    @Test
    public void createNumber() throws Exception {
        Number normalNumber = Number.createNumber(BigInteger.valueOf(10));
        assertEquals(BigInteger.TEN, normalNumber.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createNumberWithNull() throws Exception {
        Number.createNumber(null);
    }
}