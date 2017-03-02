package org.vitaly.util;

/**
 * Created by vitaly on 01.03.17.
 */
public class InputChecker {
    public static final String POSITION_MUST_BE_BETWEEN_ZERO_AND_LIST_SIZE =
            "Position must be between zero inclusive and list size exclusive!";
    public static final String LIST_IS_EMPTY = "List is empty!";
    public static final String LIST_MUST_NOT_BE_NULL = "List must not be null!";
    public static final String COMPARATOR_MUST_NOT_BE_NULL = "Comparator must not be null!";
    public static final String INDEX_IS_LESS_THAN_ZERO = "Index is less than zero!";
    public static final String TITLE_MUST_NOT_BE_NULL = "Title must not be null!";
    public static final String PRESS_ISSUE_MUST_NOT_BE_NULL = "Press issue must not be null!";

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
