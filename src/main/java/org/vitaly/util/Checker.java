package org.vitaly.util;

/**
 * Created by vitaly on 2017-02-16.
 */
public class Checker {
    public static final String LIST_IS_EMPTY = "List is empty!";
    public static final String POSITION_IS_LESS_THAN_ZERO = "Position is less than zero!";
    public static final String POSITION_IS_GREATER_THAN_OR_EQUALS_TO_LIST_SIZE
            = "Position is greater than or equals to list size!";

    private Checker() {
    }

    public static void confirmGreaterThanOrEqualsTo(int position, int value, String errorMessage) {
        if (position < value) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void confirmLessThan(int position, int value, String errorMessage) {
        if (position >= value) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
