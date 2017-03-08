package org.vitaly.homework02.figures;

import static java.lang.Math.PI;

/**
 * Created by vitaly on 2017-02-14.
 */
public class Ring implements Shape {
    private final double radius;

    private Ring(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return PI * radius * radius;
    }

    public static Ring newRing(double radius) {
        if (Double.isNaN(radius)) {
            throw new IllegalArgumentException("Radius is not a number!");
        }
        if (Double.isInfinite(radius)) {
            throw new IllegalArgumentException("Radius is infinite!");
        }
        if (radius < 0) {
            throw new IllegalArgumentException("Ring radius is less than zero!");
        }
        return new Ring(radius);
    }
}
