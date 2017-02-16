package org.vitaly.week03.figures;

import static java.lang.Double.isInfinite;
import static java.lang.Double.isNaN;

/**
 * Created by vitaly on 2017-02-14.
 */
public class Trapezium implements Shape {
    private final double bottomSide;
    private final double topSide;
    private final double height;

    private Trapezium(double bottomSide, double topSide, double height) {
        this.bottomSide = bottomSide;
        this.topSide = topSide;
        this.height = height;
    }

    @Override
    public double getArea() {
        return (bottomSide + topSide) * height / 2;
    }

    public static Trapezium newTrapezium(double bottomSide, double topSide, double height) {
        if (isNaN(bottomSide) || isNaN(topSide)) {
            throw new IllegalArgumentException("One of trapezium sides is not a number!");
        }
        if (isInfinite(bottomSide) || isInfinite(topSide)) {
            throw new IllegalArgumentException("One of trapezium sides is infinite!");
        }
        if (isNaN(height)) {
            throw new IllegalArgumentException("Height of trapezium sides is not a number!");
        }
        if (isInfinite(height)) {
            throw new IllegalArgumentException("Height of trapezium sides is infinite!");
        }
        if (bottomSide < 0 || topSide < 0) {
            throw new IllegalArgumentException("One of trapezium sides is less than zero!");
        }
        if (height < 0) {
            throw new IllegalArgumentException("Height of trapezium is less than zero!");
        }
        return new Trapezium(bottomSide, topSide, height);
    }
}
