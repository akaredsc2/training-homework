package org.vitaly.week03.figures;

import static java.lang.Math.PI;

/**
 * Created by vitaly on 2017-02-14.
 */
public class Ring extends Shape {
    private final double radius;

    public Ring() {
        this(0.0);
    }

    public Ring(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return PI * radius * radius;
    }
}
