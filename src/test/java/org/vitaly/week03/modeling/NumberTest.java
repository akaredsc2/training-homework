package org.vitaly.week03.modeling;

import org.junit.Ignore;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * Created by vitaly on 2017-02-17.
 */

@Ignore(value = "Homework 2 passed")
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