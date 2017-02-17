package org.vitaly.week03.modeling;

import java.math.BigInteger;

/**
 * Created by vitaly on 2017-02-17.
 */
public class SimpleFraction {
    private final Number numerator;
    private final Number denominator;

    private SimpleFraction(Number numerator, Number denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Number getNumerator() {
        return numerator;
    }

    public Number getDenominator() {
        return denominator;
    }

    public SimpleFraction add(SimpleFraction other) {
        Number resultNumerator = Number.createNumber(
                this.numerator.getValue().multiply(other.denominator.getValue())
                        .add(other.numerator.getValue().multiply(this.denominator.getValue())));

        Number resultDenominator = Number.createNumber(this.denominator.getValue().multiply(other.denominator.getValue()));

        return simplify(resultNumerator, resultDenominator);
    }

    public SimpleFraction subtract(SimpleFraction other) {
        Number resultNumerator = Number.createNumber(
                this.numerator.getValue().multiply(other.denominator.getValue())
                        .subtract(other.numerator.getValue().multiply(this.denominator.getValue())));

        Number resultDenominator = Number.createNumber(this.denominator.getValue().multiply(other.denominator.getValue()));

        return simplify(resultNumerator, resultDenominator);

    }

    public SimpleFraction multiply(SimpleFraction other) {
        Number resultNumerator = Number.createNumber(this.numerator.getValue().multiply(other.numerator.getValue()));

        Number resultDenominator = Number.createNumber(this.denominator.getValue().multiply(other.denominator.getValue()));

        return simplify(resultNumerator, resultDenominator);
    }

    public SimpleFraction divide(SimpleFraction other) {
        if (other.getNumerator().getValue().equals(BigInteger.ZERO)) {
            throw new ArithmeticException("Division by zero!");
        }
        Number resultNumerator = Number.createNumber(this.numerator.getValue().multiply(other.denominator.getValue()));

        Number resultDenominator = Number.createNumber(this.denominator.getValue().multiply(other.numerator.getValue()));

        return simplify(resultNumerator, resultDenominator);
    }

    public static SimpleFraction simplify(Number numerator, Number denominator) {
        Number gcd = Number.createNumber(numerator.getValue().gcd(denominator.getValue()));

        Number simplifiedNumerator = Number.createNumber(numerator.getValue().divide(gcd.getValue()));
        Number simplifiedDenominator = Number.createNumber(denominator.getValue().divide(gcd.getValue()));

        return new SimpleFraction(simplifiedNumerator, simplifiedDenominator);
    }

    public static SimpleFraction createSimpleFraction(Number numerator, Number denominator) {
        Number notNullNumerator = numerator;
        if (numerator == null) {
            notNullNumerator = Number.createNumber(BigInteger.ZERO);
        }
        Number notNullDenominator = denominator;
        if (denominator == null) {
            notNullDenominator = Number.createNumber(BigInteger.ONE);
        }
        if (notNullDenominator.getValue().equals(BigInteger.ZERO)) {
            throw new IllegalArgumentException("Denominator equals zero!");
        }
        return new SimpleFraction(notNullNumerator, notNullDenominator);
    }

    @Override
    public String toString() {
        return this.numerator.getValue().toString() + "/" + this.denominator.getValue().toString();
    }
}
