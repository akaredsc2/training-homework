package org.vitaly.week03.figures;

import static java.lang.Double.isInfinite;
import static java.lang.Double.isNaN;
import static java.lang.Math.*;

/**
 * Created by vitaly on 2017-02-14.
 */
public class Parallelogram extends Shape {
    private final double firstSide;
    private final double secondSide;
    private final double angle;

    private Parallelogram(double firstSide, double secondSide, double angle) {
        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.angle = angle;
    }

    @Override
    public double getArea() {
        return firstSide * secondSide * abs(sin(toRadians(angle)));
    }

    public static Parallelogram newParallelogram(double firstSide, double secondSide, double angle) {
        if (isNaN(firstSide) || isNaN(secondSide)) {
            throw new IllegalArgumentException("One of parallelogram sides is not a number!");
        }
        if (isInfinite(firstSide) || isInfinite(secondSide)) {
            throw new IllegalArgumentException("One of parallelogram sides is infinite!");
        }
        if (isNaN(angle)) {
            throw new IllegalArgumentException("Angle of parallelogram sides is not a number!");
        }
        if (isInfinite(angle)) {
            throw new IllegalArgumentException("Angle of parallelogram sides is infinite!");
        }
        if (firstSide < 0 || secondSide < 0) {
            throw new IllegalArgumentException("One of parallelogram sides is less than zero!");
        }
        return new Parallelogram(firstSide, secondSide, angle);
    }
}
