package org.vitaly.week01.bit;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.vitaly.week01.bit.BitwiseAlgorithms.*;

/**
 * Created by vitaly on 2017-02-05.
 */

public class BitwiseAlgorithmsTest {
    @Test
    public void testCalculateGCD() throws Exception {
        assertEquals(1, calculateGCD(2345, 72));
        assertEquals(23445, calculateGCD(1406700, 164115));
        assertEquals(1, calculateGCD(Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void testCalculateGCDWithZeros() throws Exception {
        assertEquals(243532, calculateGCD(0, 243532));
        assertEquals(243532, calculateGCD(243532, 0));
        assertEquals(0, calculateGCD(0, 0));
    }

    @Test
    public void testCalculateGCDWithNegativeNumbers() throws Exception {
        assertEquals(31415, calculateGCD(-30315475, 24440870));
        assertEquals(31415, calculateGCD(30315475, -24440870));
        assertEquals(31415, calculateGCD(-30315475, -24440870));
    }

    @Test
    public void testCountBitsInLong() throws Exception {
        assertEquals(64, countBitsInLong());
    }

    @Test
    public void testCountBitsInInt() throws Exception {
        assertEquals(32, countBitsInInt());
    }

    @Test
    public void testCountBitsInShort() throws Exception {
        assertEquals(16, countBitsInShort());
    }

    @Test
    public void testCountBitsInByte() throws Exception {
        assertEquals(8, countBitsInByte());
    }

    @Test
    public void testSetBitAtPosition() throws Exception {
        assertEquals(0b1010, setBitAtPosition(0b1010, -1, BitValue.ONE));
        assertEquals(0b1010, setBitAtPosition(0b1010, -1, BitValue.ZERO));

        assertEquals(0b1010, setBitAtPosition(0b0010, 3, BitValue.ONE));
        assertEquals(0b1010, setBitAtPosition(0b1110, 2, BitValue.ZERO));

        assertEquals(0b1010, setBitAtPosition(0b1010, 32, BitValue.ONE));
        assertEquals(0b1010, setBitAtPosition(0b1010, 32, BitValue.ZERO));
    }
}