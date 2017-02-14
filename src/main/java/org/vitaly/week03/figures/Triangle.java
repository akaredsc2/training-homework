package org.vitaly.week03.figures;

import static java.lang.Math.sqrt;

/**
 * Created by vitaly on 2017-02-14.
 */
public class Triangle extends Shape {
    private final double[] sides;

    public Triangle() {
        this(0.0, 0.0, 0.0);
    }

    public Triangle(double firstSide, double secondSide, double thirdSide) {
        this.sides = new double[]{firstSide, secondSide, thirdSide};
    }

    @Override
    public double getArea() {
        double semiPerimeter = getPerimeter() / 2;
        double result = semiPerimeter;
        for (double side : sides) {
            result *= (semiPerimeter - side);
        }
        return sqrt(result);
    }

    private double getPerimeter() {
        double result = 0;
        for (double side : sides) {
            result += side;
        }
        return result;
    }
}
