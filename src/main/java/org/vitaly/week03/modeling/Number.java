package org.vitaly.week03.modeling;

import java.math.BigInteger;

/**
 * Created by vitaly on 2017-02-17.
 */
public class Number {
    private final BigInteger value;

    private Number(BigInteger value) {
        this.value = value;
    }

    public BigInteger getValue() {
        return value;
    }

    public static Number createNumber(BigInteger value) {
        if (value == null) {
            throw new IllegalArgumentException("No value supplied!");
        }
        return new Number(value);
    }
}
