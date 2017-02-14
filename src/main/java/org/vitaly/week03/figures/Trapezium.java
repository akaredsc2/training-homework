package org.vitaly.week03.figures;

/**
 * Created by vitaly on 2017-02-14.
 */
public class Trapezium extends Shape {
    private final double bottomSide;
    private final double topSide;
    private final double height;

    public Trapezium() {
        this(0.0, 0.0, 0.0);
    }

    public Trapezium(double bottomSide, double topSide, double height) {
        this.bottomSide = bottomSide;
        this.topSide = topSide;
        this.height = height;
    }

    @Override
    public double getArea() {
        return (bottomSide + topSide) * height / 2;
    }
}
