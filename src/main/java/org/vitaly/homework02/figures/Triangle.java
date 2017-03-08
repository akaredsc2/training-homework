package org.vitaly.homework02.figures;

import static java.lang.Double.isInfinite;
import static java.lang.Double.isNaN;
import static java.lang.Math.sqrt;

/**
 * Triangle objects
 *
 * @author vitaly
 */
public class Triangle implements Shape {
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
        if (hasNaNSide(firstSide, secondSide, thirdSide)) {
            throw new IllegalArgumentException("One of triangle sides is not a number!");
        }
        if (hasInfiniteSide(firstSide, secondSide, thirdSide)) {
            throw new IllegalArgumentException("One of triangle sides is infinite!");
        }
        if (hasNegativeSide(firstSide, secondSide, thirdSide)) {
            throw new IllegalArgumentException("One of triangle sides is less than zero!");
        }
        if (!isTriangleInequalityCorrect(firstSide, secondSide, thirdSide)) {
            throw new IllegalArgumentException("Triangle inequality is not satisfied!");
        }
        return new Triangle(firstSide, secondSide, thirdSide);
    }

    private static boolean hasNaNSide(double... sides) {
        for (double side : sides) {
            if (isNaN(side)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasInfiniteSide(double... sides) {
        for (double side : sides) {
            if (isInfinite(side)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasNegativeSide(double... sides) {
        for (double side : sides) {
            if (side < 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if for given sides the triangle inequality
     *
     * @param firstSide
     * @param secondSide
     * @param thirdSide
     * @return
     */
    public static boolean isTriangleInequalityCorrect(double firstSide, double secondSide, double thirdSide) {
        return firstSide + secondSide >= thirdSide
                && firstSide + thirdSide >= secondSide
                && secondSide + thirdSide >= firstSide;
    }
}
