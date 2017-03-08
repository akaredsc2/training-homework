package org.vitaly.homework02.figures;

/**
 * Shape interface states that class implementing it has area and the addition operation is
 * defined as sum of areas of shapes.
 *
 * @author vitaly
 */
@FunctionalInterface
public interface Shape {
    /**
     * Returns area of shape.
     *
     * @return area of
     */
    double getArea();

    /**
     * Returns sum of current shape and other shape
     *
     * @param other shape to add to current
     * @return sum of current shape and other shape
     */
    default double add(Shape other) {
        double otherArea = other == null ? 0.0 : other.getArea();
        return this.getArea() + otherArea;
    }
}
