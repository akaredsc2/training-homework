package org.vitaly.week03.figures;

import static java.lang.Double.isInfinite;
import static java.lang.Double.isNaN;
import static java.lang.Math.sqrt;

/**
 * Created by vitaly on 2017-02-14.
 */
public class Triangle extends Shape {
    private final double[] sides;

    private Triangle(double firstSide, double secondSide, double thirdSide) {
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

    public static Triangle newTriangle(double firstSide, double secondSide, double thirdSide) {
        if (isNaN(firstSide) || isNaN(secondSide) || isNaN(thirdSide)) {
            throw new IllegalArgumentException("One of triangle sides is not a number!");
        }
        if (isInfinite(firstSide) || isInfinite(secondSide) || isInfinite(thirdSide)) {
            throw new IllegalArgumentException("One of triangle sides is infinite!");
        }
        if (firstSide < 0 || secondSide < 0 || thirdSide < 0) {
            throw new IllegalArgumentException("One of triangle sides is less than zero!");
        }
        if (!isTriangleInequalityCorrect(firstSide, secondSide, thirdSide)) {
            throw new IllegalArgumentException("Triangle inequality is not satisfied!");
        }
        return new Triangle(firstSide, secondSide, thirdSide);
    }

    public static boolean isTriangleInequalityCorrect(double firstSide, double secondSide, double thirdSide) {
        return firstSide + secondSide >= thirdSide
                && firstSide + thirdSide >= secondSide
                && secondSide + thirdSide >= firstSide;
    }
}
