package org.vitaly.homework03.merge;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by vitaly on 2017-02-19.
 */
public class MergerTest {
    @Test
    public void mergeUnsorted() throws Exception {
        List<Integer> firstList = Arrays.asList(2, 3, 4, 5);
        List<Integer> secondList = Arrays.asList(9, 8, 7, 6);

        List<Integer> actual = Merger.mergeUnsorted(firstList, secondList);

        List<Integer> expected = Arrays.asList(2, 3, 4, 5, 9, 8, 7, 6);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void mergeUnsortedSameList() throws Exception {
        List<Integer> list = Arrays.asList(2, 3, 4, 5);

        List<Integer> actual = Merger.mergeUnsorted(list, list);

        List<Integer> expected = Arrays.asList(2, 3, 4, 5, 2, 3, 4, 5);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void mergeUnsortedFirstListEmpty() throws Exception {
        List<Integer> list = Arrays.asList(2, 3, 4, 5);

        List<Integer> actual = Merger.mergeUnsorted(new ArrayList<>(), list);

        List<Integer> expected = Arrays.asList(2, 3, 4, 5);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void mergeUnsortedSecondListEmpty() throws Exception {
        List<Integer> list = Arrays.asList(2, 3, 4, 5);

        List<Integer> actual = Merger.mergeUnsorted(list, new ArrayList<>());

        List<Integer> expected = Arrays.asList(2, 3, 4, 5);
        assertThat(actual, equalTo(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void mergeUnsortedFirstListNull() throws Exception {
        List<Integer> list = Arrays.asList(2, 3, 4, 5);
        Merger.mergeUnsorted(null, list);
    }

    @Test(expected = IllegalArgumentException.class)
    public void mergeUnsortedSecondListNull() throws Exception {
        List<Integer> list = Arrays.asList(2, 3, 4, 5);
        Merger.mergeUnsorted(list, null);
    }

    @Test
    public void mergeAndSort() throws Exception {
        List<Integer> firstList = Arrays.asList(2, 3, 4, 5);
        List<Integer> secondList = Arrays.asList(9, 8, 7, 6);

        List<Integer> actual = Merger.mergeAndSort(firstList, secondList, Integer::compareTo);

        List<Integer> expected = Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void mergeAndSortSameList() throws Exception {
        List<Integer> list = Arrays.asList(2, 3, 4, 5);

        List<Integer> actual = Merger.mergeAndSort(list, list, Integer::compareTo);

        List<Integer> expected = Arrays.asList(2, 2, 3, 3, 4, 4, 5, 5);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void mergeAndSortEmptyFirstList() throws Exception {
        List<Integer> list = Arrays.asList(2, 3, 4, 5);

        List<Integer> actual = Merger.mergeAndSort(list, new ArrayList<>(), Integer::compareTo);

        List<Integer> expected = list;
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void mergeAndSortEmptySecondList() throws Exception {
        List<Integer> list = Arrays.asList(2, 3, 4, 5);

        List<Integer> actual = Merger.mergeAndSort(new ArrayList<>(), list, Integer::compareTo);

        List<Integer> expected = list;
        assertThat(actual, equalTo(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void mergeAndSortNullFirstList() throws Exception {
        List<Integer> list = Arrays.asList(2, 3, 4, 5);

        Merger.mergeAndSort(list, null, Integer::compareTo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void mergeAndSortNullSecondList() throws Exception {
        List<Integer> list = Arrays.asList(2, 3, 4, 5);

        Merger.mergeAndSort(null, list, Integer::compareTo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void mergeAndSortNullComparator() throws Exception {
        Merger.mergeAndSort(null, new ArrayList<>(), Integer::compareTo);
    }

    @Test
    public void mergeSortedFirstLongerThanSecond() throws Exception {
        List<Integer> firstList = Arrays.asList(2, 3, 4, 5);
        List<Integer> secondList = Arrays.asList(6, 7, 8);

        List<Integer> actual = Merger.mergeSorted(firstList, secondList, Integer::compareTo);

        List<Integer> expected = Arrays.asList(2, 3, 4, 5, 6, 7, 8);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void mergeSortedSecondLongerThanFirst() throws Exception {
        List<Integer> firstList = Arrays.asList(2, 3, 4);
        List<Integer> secondList = Arrays.asList(6, 7, 8, 9);

        List<Integer> actual = Merger.mergeSorted(firstList, secondList, Integer::compareTo);

        List<Integer> expected = Arrays.asList(2, 3, 4, 6, 7, 8, 9);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void mergeSortedSameList() throws Exception {
        List<Integer> list = Arrays.asList(2, 3, 4);

        List<Integer> actual = Merger.mergeSorted(list, list, Integer::compareTo);

        List<Integer> expected = Arrays.asList(2, 2, 3, 3, 4, 4);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void mergeSortedFirstEmpty() throws Exception {
        List<Integer> list = Arrays.asList(6, 7, 8);

        List<Integer> actual = Merger.mergeSorted(new ArrayList<>(), list, Integer::compareTo);

        List<Integer> expected = Arrays.asList(6, 7, 8);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void mergeSortedSecondEmpty() throws Exception {
        List<Integer> list = Arrays.asList(2, 3, 4);

        List<Integer> actual = Merger.mergeSorted(list, new ArrayList<>(), Integer::compareTo);

        List<Integer> expected = Arrays.asList(2, 3, 4);
        assertThat(actual, equalTo(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void mergeSortedFirstUnsorted() throws Exception {
        List<Integer> list = Arrays.asList(2, 4, 3);

        List<Integer> actual = Merger.mergeSorted(list, new ArrayList<>(), Integer::compareTo);

        List<Integer> expected = Arrays.asList(2, 3, 4);
        assertThat(actual, equalTo(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void mergeSortedSecondUnsorted() throws Exception {
        List<Integer> list = Arrays.asList(2, 4, 3);

        List<Integer> actual = Merger.mergeSorted(new ArrayList<>(), list, Integer::compareTo);

        List<Integer> expected = Arrays.asList(2, 3, 4);
        assertThat(actual, equalTo(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void mergeSortedFirstNull() throws Exception {
        List<Integer> list = Arrays.asList(2, 3, 4);
        Merger.mergeSorted(null, list, Integer::compareTo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void mergeSortedSecondNull() throws Exception {
        List<Integer> list = Arrays.asList(2, 3, 4);
        Merger.mergeSorted(list, null, Integer::compareTo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void mergeSortedComparatorNull() throws Exception {
        List<Integer> list = Arrays.asList(2, 3, 4);
        Merger.mergeSorted(list, list, null);
    }

    @Test
    public void sortAndMergeFirstLongerThanSecond() throws Exception {
        List<Integer> firstList = Arrays.asList(2, 4, 5, 3);
        List<Integer> secondList = Arrays.asList(7, 6, 8);

        List<Integer> actual = Merger.sortAndMerge(firstList, secondList, Integer::compareTo);

        List<Integer> expected = Arrays.asList(2, 3, 4, 5, 6, 7, 8);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void sortAndMergeSecondLongerThanFirst() throws Exception {
        List<Integer> firstList = Arrays.asList(4, 2, 3);
        List<Integer> secondList = Arrays.asList(7, 6, 9, 8);

        List<Integer> actual = Merger.sortAndMerge(firstList, secondList, Integer::compareTo);

        List<Integer> expected = Arrays.asList(2, 3, 4, 6, 7, 8, 9);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void sortAndMergeSameList() throws Exception {
        List<Integer> list = Arrays.asList(2, 4, 3);

        List<Integer> actual = Merger.sortAndMerge(list, list, Integer::compareTo);

        List<Integer> expected = Arrays.asList(2, 2, 3, 3, 4, 4);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void sortAndMergeFirstEmpty() throws Exception {
        List<Integer> list = Arrays.asList(7, 6, 8);

        List<Integer> actual = Merger.sortAndMerge(new ArrayList<>(), list, Integer::compareTo);

        List<Integer> expected = Arrays.asList(6, 7, 8);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void sortAndMergeSecondEmpty() throws Exception {
        List<Integer> list = Arrays.asList(4, 2, 3);

        List<Integer> actual = Merger.sortAndMerge(list, new ArrayList<>(), Integer::compareTo);

        List<Integer> expected = Arrays.asList(2, 3, 4);
        assertThat(actual, equalTo(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void sortAndMergeFirstNull() throws Exception {
        List<Integer> list = Arrays.asList(2, 4, 3);
        Merger.sortAndMerge(null, list, Integer::compareTo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sortAndMergeSecondNull() throws Exception {
        List<Integer> list = Arrays.asList(3, 2, 4);
        Merger.sortAndMerge(list, null, Integer::compareTo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sortAndMergeComparatorNull() throws Exception {
        List<Integer> list = Arrays.asList(2, 4, 3);
        Merger.sortAndMerge(list, list, null);
    }
}