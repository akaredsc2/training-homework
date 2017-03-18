package org.vitaly.homework04.composite;

import org.vitaly.util.InputChecker;

import static org.vitaly.util.InputChecker.LEFT_COMPONENT;
import static org.vitaly.util.InputChecker.RIGHT_COMPONENT;

/**
 * Created by vitaly on 2017-03-04.
 */
public class Subtraction implements Component {
    private Component left;
    private Component right;

    public Subtraction(Component left, Component right) {
        InputChecker.requireNonNull(left, LEFT_COMPONENT);
        InputChecker.requireNonNull(right, RIGHT_COMPONENT);

        this.left = left;
        this.right = right;
    }

    @Override
    public long compute() {
        return left.compute() - right.compute();
    }
}
