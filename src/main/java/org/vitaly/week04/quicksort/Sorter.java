package org.vitaly.week04.quicksort;

import java.util.Comparator;
import java.util.List;

import static java.util.Collections.swap;
import static org.vitaly.week04.util.Checker.checkArguments;

/**
 * Created by vitaly on 2017-02-19.
 */
public class Sorter {
    private Sorter() {
    }

    public static <T> void doQuickSort(List<T> list, Comparator<? super T> comparator) {
        checkArguments(list, comparator);
        doQuickSort(list, 0, list.size() - 1, comparator);
    }

    public static <T> void doQuickSort(List<T> list, int lowerBound, int higherBound,
                                       Comparator<? super T> comparator) {
        checkArguments(list, comparator);

        if (lowerBound < higherBound) {
            int partitionIndex = doPartition(list, lowerBound, higherBound, comparator);
            doQuickSort(list, lowerBound, partitionIndex - 1, comparator);
            doQuickSort(list, partitionIndex + 1, higherBound, comparator);
        }
    }

    public static <T> int doPartition(List<T> list, int lowerBound, int higherBound,
                                      Comparator<? super T> comparator) {
        checkArguments(list, lowerBound, higherBound, comparator);

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
        checkArguments(list, comparator);
        for (int i = 0; i < list.size() - 1; i++) {
            if (comparator.compare(list.get(i), list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }
}
