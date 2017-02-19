package org.vitaly.week04.util;

import java.util.Comparator;
import java.util.List;

/**
 * Created by vitaly on 2017-02-19.
 */
public class Checker {
    public static <T> void checkList(List<T> list) {
        if (list == null) throw new IllegalArgumentException("List must not be null!");
    }

    public static <T> void checkArguments(List<T> list, Comparator<? super T> comparator) {
        checkList(list);
        if (comparator == null) throw new IllegalArgumentException("Comparator must not be null!");
    }

    public static <T> void checkArguments(List<T> list, int lowerBound, int higherBound,
                                          Comparator<? super T> comparator) {
        checkArguments(list, comparator);
        if (lowerBound < 0) {
            throw new IllegalArgumentException("Lower bound must be greater than or equal to zero!");
        }
        if (higherBound < 0) {
            throw new IllegalArgumentException("Higher bound must be greater than or equal to zero!");
        }
        if (lowerBound >= list.size()) {
            throw new IllegalArgumentException("Lower bound must be less than list size!");
        }
        if (higherBound >= list.size()) {
            throw new IllegalArgumentException("Higher bound must be less than list size!");
        }
    }
}
