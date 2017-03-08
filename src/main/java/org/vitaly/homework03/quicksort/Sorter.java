package org.vitaly.homework03.quicksort;

import java.util.Comparator;
import java.util.List;

import static java.util.Collections.swap;
import static org.vitaly.util.InputChecker.*;

/**
 * Created by vitaly on 2017-02-19.
 */
public class Sorter {
    private Sorter() {
    }

    public static <T> void doQuickSort(List<T> list, Comparator<? super T> comparator) {
        requireNonNull(list, LIST_MUST_NOT_BE_NULL);
        requireNonNull(comparator, COMPARATOR_MUST_NOT_BE_NULL);

        doQuickSort(list, 0, list.size() - 1, comparator);
    }

    public static <T> void doQuickSort(List<T> list, int lowerBound, int higherBound,
                                       Comparator<? super T> comparator) {
        requireNonNull(list, LIST_MUST_NOT_BE_NULL);
        requireNonNull(comparator, COMPARATOR_MUST_NOT_BE_NULL);

        if (lowerBound < higherBound) {
            int partitionIndex = doPartition(list, lowerBound, higherBound, comparator);
            doQuickSort(list, lowerBound, partitionIndex - 1, comparator);
            doQuickSort(list, partitionIndex + 1, higherBound, comparator);
        }
    }

    public static <T> int doPartition(List<T> list, int lowerBound, int higherBound,
                                      Comparator<? super T> comparator) {
        requireNonNull(list, LIST_MUST_NOT_BE_NULL);
        requireNonNull(comparator, COMPARATOR_MUST_NOT_BE_NULL);
        int listSize = list.size();
        requireInRange(lowerBound, 0, listSize, "Lower bound must be between zero inclusive and list size exclusive!");
        requireInRange(higherBound, 0, listSize, "Upper bound must be between zero inclusive and list size exclusive!");

        T pivot = list.get(higherBound);
        int result = lowerBound - 1;

        for (int j = lowerBound; j < higherBound; j++) {
            T nextElement = list.get(j);
            if (comparator.compare(nextElement, pivot) < 0) {
                result += 1;
                swap(list, j, result);
            }
        }
        result = result + 1;
        swap(list, result, higherBound);
        return result;
    }

    public static <T> boolean isSorted(List<T> list, Comparator<? super T> comparator) {
        requireNonNull(list, LIST_MUST_NOT_BE_NULL);
        requireNonNull(comparator, COMPARATOR_MUST_NOT_BE_NULL);

        for (int i = 0; i < list.size() - 1; i++) {
            if (comparator.compare(list.get(i), list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }
}
