package org.vitaly.homework04.composite;

import org.vitaly.util.InputChecker;

import static org.vitaly.util.InputChecker.LEFT_COMPONENT;
import static org.vitaly.util.InputChecker.RIGHT_COMPONENT;

/**
 * Created by vitaly on 2017-03-04.
 */
public class Division implements Component {
    private Component numerator;
    private Component denominator;

    public Division(Component numerator, Component denominator) {
        InputChecker.requireNonNull(numerator, LEFT_COMPONENT);
        InputChecker.requireNonNull(denominator, RIGHT_COMPONENT);

        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public long compute() {
        if (denominator.compute() == 0) {
            throw new ArithmeticException("Division by zero!");
        }
        return numerator.compute() / denominator.compute();
    }
}
