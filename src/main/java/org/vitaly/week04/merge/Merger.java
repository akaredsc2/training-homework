package org.vitaly.week04.merge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.vitaly.week04.quicksort.Sorter.doQuickSort;
import static org.vitaly.week04.quicksort.Sorter.isSorted;
import static org.vitaly.week04.util.Checker.checkArguments;
import static org.vitaly.week04.util.Checker.checkList;

/**
 * Created by vitaly on 2017-02-19.
 */
public class Merger {
    private Merger() {
    }

    public static <T> List<T> sortAndMerge(List<T> firstList, List<T> secondList, Comparator<? super T> comparator) {
        checkList(firstList);
        checkArguments(secondList, comparator);

        if (!isSorted(firstList, comparator)) {
            doQuickSort(firstList, comparator);
        }
        if (!isSorted(secondList, comparator)) {
            doQuickSort(secondList, comparator);
        }
        return mergeSorted(firstList, secondList, comparator);
    }

    public static <T> List<T> mergeSorted(List<T> firstList, List<T> secondList, Comparator<? super T> comparator) {
        checkList(firstList);
        checkArguments(secondList, comparator);
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
        checkList(firstList);
        checkArguments(secondList, comparator);

        List<T> result = mergeUnsorted(firstList, secondList);
        doQuickSort(result, comparator);
        return result;
    }

    public static <T> List<T> mergeUnsorted(List<T> firstList, List<T> secondList) {
        checkList(firstList);
        checkList(secondList);

        List<T> result = new ArrayList<>();
        for (T element : firstList) {
            result.add(element);
        }
        for (T element : secondList) {
            result.add(element);
        }
        return result;
    }
}
