package org.vitaly.util;

/**
 * Created by vitaly on 01.03.17.
 */
public class InputChecker {
    public static final String POSITION_MUST_BE_BETWEEN_ZERO_AND_LIST_SIZE =
            "Position must be between zero inclusive and list size exclusive!";
    public static final String LIST_IS_EMPTY = "List is empty!";
    public static final String LIST = "List";
    public static final String COMPARATOR = "Comparator";
    public static final String INDEX_IS_LESS_THAN_ZERO = "Index is less than zero!";
    public static final String TITLE = "Title";
    public static final String PRESS_ISSUE = "Press issue";
    public static final String ARTICLE = "Article";
    public static final String ROW_NUMBER_MUST_BE_BETWEEN_0_INCLUSIVE_AND_3_EXCLUSIVE =
            "Row number must be between 0 inclusive and 3 exclusive!";
    public static final String COLUMN_NUMBER_MUST_BE_BETWEEN_0_INCLUSIVE_AND_3_EXCLUSIVE =
            "Column number must be between 0 inclusive and 3 exclusive!";
    public static final String SHIP = "Ship";
    public static final String NAME = "Name";
    public static final String LANGUAGE = "Language";
    public static final String LEFT_COMPONENT = "Left component";
    public static final String RIGHT_COMPONENT = "Right component";
    public static final String NEXT_HANDLER = "Next handler";

    private InputChecker() {
    }

    public static void requireNonNull(Object object, String errorMessage) {
        if (object == null) {
            throw new IllegalArgumentException(errorMessage + " must not be null!");
        }
    }

    public static void requireNonEmptyString(String string, String errorMessage) {
        requireNonNull(string, errorMessage);
        if (string.isEmpty()) {
            throw new IllegalArgumentException(errorMessage + " must not be empty string!");
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

    public static void requireZeroOrPositiveInteger(int number, String errorMessage) {
        requireGreaterThanOrEqualsTo(number, 0, errorMessage);
    }

    public static void requireNumber(double number, String errorMessage) {
        if (Double.isNaN(number)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void requireFinite(double number, String errorMessage) {
        if (Double.isInfinite(number)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void requireGreaterThanZero(double number, String errorMessage) {
        if (number <= 0) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void requirePositiveDouble(double number, String variableName) {
        requireNumber(number, variableName + " must not be NaN!");
        requireFinite(number, variableName + " must be finite number!");
        requireGreaterThanZero(number, variableName + " must be greater than zero!");
    }

    public static void requireInRange(int number, int lowerBound, int higherBound, String errorMessage) {
        requireGreaterThanOrEqualsTo(number, lowerBound, errorMessage);
        requireLessThan(number, higherBound, errorMessage);
    }
}
