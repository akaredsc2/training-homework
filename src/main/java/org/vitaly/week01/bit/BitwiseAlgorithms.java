package org.vitaly.week01.bit;

import static java.lang.Math.abs;

/**
 * This class contains bitwise-driven algorithms for computing GCD, counting bits in primitive integer types and
 * setting bits in number to specific values.
 *
 * @author vitaly
 */
public class BitwiseAlgorithms {
    private BitwiseAlgorithms() {
    }

    /**
     * Calculate greatest common divisor of two numbers using bitwise Euclid algorithm
     *
     * @param firstNumber  first number
     * @param secondNumber second number
     * @return greatest common divisor of two numbers
     */
    public static int calculateGCD(int firstNumber, int secondNumber) {
        if (firstNumber == 0) {
            return secondNumber;
        } else if (secondNumber == 0 || firstNumber == secondNumber) {
            return firstNumber;
        }

        int positiveFirstNumber = abs(firstNumber);
        int positiveSecondNumber = abs(secondNumber);

        if (isEven(positiveFirstNumber)) {
            if (isEven(positiveSecondNumber)) {
                return calculateGCD(positiveFirstNumber >> 1, positiveSecondNumber >> 1) << 1;
            } else {
                return calculateGCD(positiveFirstNumber >> 1, positiveSecondNumber);
            }
        } else {
            if (isEven(positiveSecondNumber)) {
                return calculateGCD(positiveFirstNumber, positiveSecondNumber >> 1);
            } else if (positiveFirstNumber > positiveSecondNumber) {
                return calculateGCD((positiveFirstNumber - positiveSecondNumber) >> 1, positiveSecondNumber);
            } else {
                return calculateGCD(positiveFirstNumber, (positiveSecondNumber - positiveFirstNumber) >> 1);
            }
        }
    }

    private static boolean isEven(int firstNumber) {
        return (firstNumber & 0b1) == 0;
    }

    /**
     * Count how many bits does long primitive type contain
     *
     * @return count of bits in long primitive type
     */
    public static int countBitsInLong() {
        long leftmostBit = 1L << -1;
        int result = 0;

        while (leftmostBit != 0) {
            leftmostBit = leftmostBit >>> 1;
            result += 1;
        }

        return result;
    }

    /**
     * Count how many bits does integer primitive type contain
     *
     * @return count of bits in integer primitive type
     */
    public static int countBitsInInt() {
        int leftmostBit = 1 << -1;
        int result = 0;

        while (leftmostBit != 0) {
            leftmostBit = leftmostBit >>> 1;
            result += 1;
        }

        return result;
    }

    /**
     * Count how many bits does short primitive type contain
     *
     * @return count of bits in short primitive type
     */
    public static int countBitsInShort() {
        short rightmostBit = 1;
        int result = 0;

        while (rightmostBit != 0) {
            rightmostBit = (short) (rightmostBit << 1);
            result += 1;
        }

        return result;
    }

    /**
     * Count how many bits does byte primitive type contain
     *
     * @return count of bits in byte primitive type
     */
    public static int countBitsInByte() {
        byte rightmostBit = 1;
        int result = 0;

        while (rightmostBit != 0) {
            rightmostBit = (byte) (rightmostBit << 1);
            result += 1;
        }

        return result;
    }

    /**
     * Set bit at selected position to specified value in given number. Position 0 corresponds to rightmost bit and 31
     * to leftmost one. If position is less than 0 or greater than 31 than reurned number will be unchanged.
     *
     * @param number      target number
     * @param bitPosition position of bit to be changed
     * @param value       value for bit to be set
     * @return number with set bit
     */
    public static int setBitAtPosition(int number, int bitPosition, BitValue value) {
        if (bitPosition < 0 || bitPosition > 31) {
            return number;
        }
        if (value == BitValue.ZERO) {
            int mask = ~(1 << bitPosition);
            return number & mask;
        } else {
            int mask = 1 << bitPosition;
            return number | mask;
        }
    }
}
