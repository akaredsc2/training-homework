package org.vitaly.week03.figures;

import static java.lang.Math.abs;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

/**
 * Created by vitaly on 2017-02-14.
 */
public class Parallelogram extends Shape {
    private final double firstSide;
    private final double secondSide;
    private final double angle;

    public Parallelogram() {
        this(0.0, 0.0, 0.0);
    }

    public Parallelogram(double firstSide, double secondSide, double angle) {
        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.angle = angle;
    }

    @Override
    public double getArea() {
        return firstSide * secondSide * abs(sin(toRadians(angle)));
    }
}
