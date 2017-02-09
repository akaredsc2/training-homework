package org.vitaly.week01.rsa;

import java.math.BigInteger;

/**
 * Pair object contains Pair of numbers stored in BigInteger objects
 *
 * @author vitaly
 */
public class Pair {
    private BigInteger first;
    private BigInteger second;

    /**
     * Create object of Pair class using supplied numbers stored in BigInteger objects
     *
     * @param first  first number
     * @param second second number
     */
    public Pair(BigInteger first, BigInteger second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Returns first number of pair
     *
     * @return first number of pair
     */
    public BigInteger getFirst() {
        return first;
    }

    /**
     * Returns second number of pair
     *
     * @return second number of pair
     */
    public BigInteger getSecond() {
        return second;
    }
}
