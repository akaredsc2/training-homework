package org.vitaly.homework03.merge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.vitaly.util.InputChecker.COMPARATOR_MUST_NOT_BE_NULL;
import static org.vitaly.util.InputChecker.requireNonNull;
import static org.vitaly.homework03.quicksort.Sorter.doQuickSort;
import static org.vitaly.homework03.quicksort.Sorter.isSorted;

/**
 * Created by vitaly on 2017-02-19.
 */
public class Merger {
    private Merger() {
    }

    public static <T> List<T> sortAndMerge(List<T> firstList, List<T> secondList, Comparator<? super T> comparator) {
        requireNonNullLists(firstList, secondList);
        requireNonNull(comparator, COMPARATOR_MUST_NOT_BE_NULL);

        if (!isSorted(firstList, comparator)) {
            doQuickSort(firstList, comparator);
        }
        if (!isSorted(secondList, comparator)) {
            doQuickSort(secondList, comparator);
        }
        return mergeSorted(firstList, secondList, comparator);
    }

    public static <T> List<T> mergeSorted(List<T> firstList, List<T> secondList, Comparator<? super T> comparator) {
        requireNonNullLists(firstList, secondList);
        requireNonNull(comparator, COMPARATOR_MUST_NOT_BE_NULL);

        if (!isSorted(firstList, comparator)) {
            throw new IllegalArgumentException("First list is not sorted!");
        }
        if (!isSorted(secondList, comparator)) {
            throw new IllegalArgumentException("Second list is not sorted!");
        }

        List<T> result = new ArrayList<>();
        int firstListPosition = 0;
        int secondListPosition = 0;
        while (firstListPosition < firstList.size() && secondListPosition < secondList.size()) {
            T firstListElement = firstList.get(firstListPosition);
            T secondListElement = secondList.get(secondListPosition);

            if (comparator.compare(firstListElement, secondListElement) < 0) {
                result.add(firstListElement);
                firstListPosition += 1;
            } else {
                result.add(secondListElement);
                secondListPosition += 1;
            }
        }

        copyRemainingPartOfList(firstList, result, firstListPosition);
        copyRemainingPartOfList(secondList, result, secondListPosition);

        return result;
    }

    private static <T> void copyRemainingPartOfList(List<T> targetList, List<T> resultList, int positionFrom) {
        for (int i = positionFrom; i < targetList.size(); i++) {
            resultList.add(targetList.get(i));
        }
    }

    public static <T> List<T> mergeAndSort(List<T> firstList, List<T> secondList, Comparator<? super T> comparator) {
        requireNonNullLists(firstList, secondList);
        requireNonNull(comparator, COMPARATOR_MUST_NOT_BE_NULL);

        List<T> result = mergeUnsorted(firstList, secondList);
        doQuickSort(result, comparator);
        return result;
    }

    public static <T> List<T> mergeUnsorted(List<T> firstList, List<T> secondList) {
        requireNonNullLists(firstList, secondList);

        List<T> result = new ArrayList<>();
        for (T element : firstList) {
            result.add(element);
        }
        for (T element : secondList) {
            result.add(element);
        }
        return result;
    }

    private static <T> void requireNonNullLists(List<T> firstList, List<T> secondList) {
        requireNonNull(firstList, "List must not be null!");
        requireNonNull(secondList, "List must not be null!");
    }
}
