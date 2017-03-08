package org.vitaly.homework02.modeling;

import org.vitaly.util.InputChecker;

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
        InputChecker.requireNonNull(value, "No value supplied!");
        return new Number(value);
    }
}
