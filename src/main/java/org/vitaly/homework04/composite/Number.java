package org.vitaly.homework04.composite;

/**
 * Created by vitaly on 2017-03-04.
 */
public class Number implements Component {
    private int value;

    public Number(int value) {
        this.value = value;
    }

    @Override
    public long compute() {
        return value;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
