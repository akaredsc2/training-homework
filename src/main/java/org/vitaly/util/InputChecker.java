package org.vitaly.util;

/**
 * Created by vitaly on 01.03.17.
 */
public class InputChecker {
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
}
