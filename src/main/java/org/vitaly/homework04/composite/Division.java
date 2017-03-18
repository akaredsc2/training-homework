package org.vitaly.homework04.composite;

/**
 * Created by vitaly on 2017-03-04.
 */
public class Division implements Component {
    private Component left;
    private Component right;

    public Division(Component left, Component right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public long compute() {
        return left.compute() / right.compute();
    }
}
