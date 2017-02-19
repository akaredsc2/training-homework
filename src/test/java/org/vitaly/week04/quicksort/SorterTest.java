package org.vitaly.week04.quicksort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by vitaly on 2017-02-19.
 */
public class SorterTest {
    @Test
    public void doQuickSort() throws Exception {
        List<Integer> actual = Arrays.asList(5, 3, 8, -10);

        Sorter.doQuickSort(actual, Comparator.comparingInt(x -> x));

        List<Integer> expected = Arrays.asList(-10, 3, 5, 8);
        assertThat(actual, is(expected));
    }

    @Test
    public void doQuickSortOnSortedArray() throws Exception {
        List<Integer> actual = Arrays.asList(1, 2, 3, 4);

        Sorter.doQuickSort(actual, Comparator.comparingInt(x -> x));

        List<Integer> expected = Arrays.asList(1, 2, 3, 4);
        assertThat(actual, is(expected));
    }

    @Test
    public void doQuickSortOnEmptyArray() throws Exception {
        List<Integer> actual = new ArrayList<>();

        Sorter.doQuickSort(actual, Comparator.comparingInt(x -> x));

        List<Integer> expected = new ArrayList<>();
        assertThat(actual, is(expected));
    }

    @Test(expected = NullPointerException.class)
    public void doQuickSortOnNullList() throws Exception {
        List<Integer> actual = null;
        Sorter.doQuickSort(actual, Comparator.comparingInt(x -> x));
    }

    @Test(expected = NullPointerException.class)
    public void doQuickSortOnNullComparator() throws Exception {
        List<Integer> actual = new ArrayList<>();
        Sorter.doQuickSort(actual, null);
    }


}