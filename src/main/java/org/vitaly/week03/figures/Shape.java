package org.vitaly.week03.figures;

/**
 * Created by vitaly on 2017-02-14.
 */
public abstract class Shape {
    public abstract double getArea();

    public final double add(Shape other) {
        double otherArea = other == null ? 0.0 : other.getArea();
        return this.getArea() + otherArea;
    }
}
