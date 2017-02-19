package org.vitaly.week04.quicksort;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.swap;

/**
 * Created by vitaly on 2017-02-19.
 */
public class Sorter {
    private Sorter() {

    }

    public static <T> void doQuickSort(List<T> list, Comparator<T> comparator) {
        checkArguments(list, comparator);
        doQuickSort(list, 0, list.size() - 1, comparator);
    }

    public static <T> void doQuickSort(List<T> list, int lowerBound, int higherBound,
                                       Comparator<T> comparator) {
        checkArguments(list, comparator);

        if (lowerBound < higherBound) {
            int partitionIndex = doPartition(list, lowerBound, higherBound, comparator);
            doQuickSort(list, lowerBound, partitionIndex - 1, comparator);
            doQuickSort(list, partitionIndex + 1, higherBound, comparator);
        }
    }

    public static <T> int doPartition(List<T> list, int lowerBound, int higherBound,
                                      Comparator<T> comparator) {
        checkArguments(list, comparator);

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

    private static <T> void checkArguments(List<T> list, Comparator<T> comparator) {
        Objects.requireNonNull(list, "List must not be null!");
        Objects.requireNonNull(comparator, "Comparator must not be null!");
    }
}
