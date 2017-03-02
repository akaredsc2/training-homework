package org.vitaly.util;

/**
 * Created by vitaly on 01.03.17.
 */
public class InputChecker {
    private InputChecker() {
    }

    public static void requireNonNull(Object object, String errorMessage) {
        if (object == null) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void requireNonEmptyString(String string, String errorMessage) {
        if (string.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void requireGreaterThanOrEqualsTo(int number, int value, String errorMessage) {
        if (number < value) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void requireLessThan(int number, int value, String errorMessage) {
        if (number >= value) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void requirePositive(int number, String errorMessage) {
        requireGreaterThanOrEqualsTo(number, 0, errorMessage);
    }

    public static void requireInRange(int number, int lowerBound, int higherBound, String errorMessage) {
        requireGreaterThanOrEqualsTo(number, lowerBound, errorMessage);
        requireLessThan(number, higherBound, errorMessage);
    }
}
