package org.vitaly.week01.karatsuba;

import java.math.BigInteger;

/**
 * KaratsubaMultiplier implements Karatsuba algorithm. The Karatsuba algorithm is a fast multiplication algorithm.
 *
 * @author vitaly
 */
public class KaratsubaMultiplier {

    /**
     * If two numbers bit lengths are lower that this threshold than regular multiplication will be performed
     */
    public static final int KARATSUBA_THRESHOLD = 5;

    private KaratsubaMultiplier() {
    }

    /**
     * This method reduces the multiplication of two n-digit numbers to at most n^(log(3))
     *
     * @param firstNumber  multiplier
     * @param secondNumber multiplicand
     * @return product of multiplier and multiplicand calculated using Karatsuba algorithm
     */
    public static BigInteger multiply(BigInteger firstNumber, BigInteger secondNumber) {
        int maxNumberLength = Math.max(firstNumber.bitLength(), secondNumber.bitLength());

        if (maxNumberLength < KARATSUBA_THRESHOLD) {
            return firstNumber.multiply(secondNumber);
        }

        int shiftLength = (maxNumberLength / 2) + (maxNumberLength % 2);

        BigInteger firstNumberFraction = firstNumber.shiftRight(shiftLength);
        BigInteger firstNumberRemainder = firstNumber.subtract(firstNumberFraction.shiftLeft(shiftLength));
        BigInteger secondNumberFraction = secondNumber.shiftRight(shiftLength);
        BigInteger secondNumberRemainder = secondNumber.subtract(secondNumberFraction.shiftLeft(shiftLength));

        BigInteger fractionsProduct = multiply(firstNumberFraction, secondNumberFraction);
        BigInteger remaindersProduct = multiply(firstNumberRemainder, secondNumberRemainder);

        BigInteger firstSum = firstNumberFraction.add(firstNumberRemainder);
        BigInteger secondSum = secondNumberFraction.add(secondNumberRemainder);

        BigInteger sumsProduct = multiply(firstSum, secondSum);

        BigInteger firstAddend = fractionsProduct.shiftLeft(2 * shiftLength);
        BigInteger secondAddend = sumsProduct.subtract(fractionsProduct).subtract(remaindersProduct)
                .shiftLeft(shiftLength);

        return firstAddend.add(secondAddend).add(remaindersProduct);
    }
}
