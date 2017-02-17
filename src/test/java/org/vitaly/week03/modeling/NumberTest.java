package org.vitaly.week03.modeling;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * Created by vitaly on 2017-02-17.
 */
public class NumberTest {
    @Test
    public void createNumber() throws Exception {
        Number normalNumber = Number.createNumber(BigInteger.valueOf(10));
        assertEquals(BigInteger.TEN, normalNumber.getValue());

        Number nullNumber = Number.createNumber(null);
        assertEquals(BigInteger.ZERO, nullNumber.getValue());
    }

}