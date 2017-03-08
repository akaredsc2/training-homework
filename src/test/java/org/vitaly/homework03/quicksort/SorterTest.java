package org.vitaly.homework03.quicksort;

import org.junit.Test;
import org.vitaly.homework02.figures.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by vitaly on 2017-02-19.
 */
public class SorterTest {
    @Test
    public void doPartition() throws Exception {
        List<Integer> actual = Arrays.asList(2, 8, 7, 1, 3, 5, 6, 4);

        int result = Sorter.doPartition(actual, 0, actual.size() - 1, Integer::compareTo);

        assertThat(result, equalTo(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void doPartitionLowerBoundLessThanZero() throws Exception {
        List<Integer> actual = Arrays.asList(2, 8, 7, 1, 3, 5, 6, 4);

        Sorter.doPartition(actual, -1, actual.size() - 1, Integer::compareTo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void doPartitionHigherBoundLessThanZero() throws Exception {
        List<Integer> actual = Arrays.asList(2, 8, 7, 1, 3, 5, 6, 4);

        Sorter.doPartition(actual, 0, -1, Integer::compareTo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void doPartitionLowerBoundGreaterThanListSize() throws Exception {
        List<Integer> actual = Arrays.asList(2, 8, 7, 1, 3, 5, 6, 4);

        Sorter.doPartition(actual, 10, actual.size() - 1, Integer::compareTo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void doPartitionHigherBoundGreaterThanListSize() throws Exception {
        List<Integer> actual = Arrays.asList(2, 8, 7, 1, 3, 5, 6, 4);

        Sorter.doPartition(actual, 0, 10, Integer::compareTo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void doPartitionOnNullList() throws Exception {
        List<Integer> actual = null;
        Sorter.doPartition(actual, 0, 0, Integer::compareTo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void doPartitionOnNullComparator() throws Exception {
        List<Integer> actual = new ArrayList<>();
        Sorter.doPartition(actual, 0, 0, null);
    }

    @Test
    public void doQuickSortOfSameClassObjects() throws Exception {
        List<Integer> actual = Arrays.asList(2, 8, 7, 1, 3, 5, 6, 4);

        Sorter.doQuickSort(actual, Integer::compareTo);

        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void doQuickSortOnSortedArrayOfSameClassObjects() throws Exception {
        List<Integer> actual = Arrays.asList(1, 2, 3, 4);

        Sorter.doQuickSort(actual, Integer::compareTo);

        List<Integer> expected = Arrays.asList(1, 2, 3, 4);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void doQuickSortOfDifferentClassObjects() throws Exception {
        Ring ring = Ring.newRing(50);
        Parallelogram parallelogram = Parallelogram.newParallelogram(40, 40, 90);
        Triangle triangle = Triangle.newTriangle(20, 30, 40);
        Trapezium trapezium = Trapezium.newTrapezium(40, 40, 50);
        List<Shape> actual = Arrays.asList(
                ring,
                parallelogram,
                triangle,
                trapezium
        );

        Sorter.doQuickSort(actual, Comparator.comparingDouble(Shape::getArea));

        List<Shape> expected = Arrays.asList(
                triangle,
                parallelogram,
                trapezium,
                ring
        );
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void doQuickSortOnEmptyArray() throws Exception {
        List<Integer> actual = new ArrayList<>();

        Sorter.doQuickSort(actual, Integer::compareTo);

        List<Integer> expected = new ArrayList<>();
        assertThat(actual, equalTo(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void doQuickSortOnNullList() throws Exception {
        List<Integer> actual = null;
        Sorter.doQuickSort(actual, Integer::compareTo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void doQuickSortOnNullComparator() throws Exception {
        List<Integer> actual = new ArrayList<>();
        Sorter.doQuickSort(actual, null);
    }

    @Test
    public void isSorted() throws Exception {
        List<Integer> actual = Arrays.asList(1, 2, 3, 4);

        boolean result = Sorter.isSorted(actual, Integer::compareTo);

        assertTrue(result);
    }

    @Test
    public void isNotSorted() throws Exception {
        List<Integer> actual = Arrays.asList(1, 5, 3, 4);

        boolean result = Sorter.isSorted(actual, Integer::compareTo);

        assertFalse(result);
    }

    @Test
    public void isSortedEmptyList() throws Exception {
        List<Integer> actual = new ArrayList<>();

        boolean result = Sorter.isSorted(actual, Integer::compareTo);

        assertTrue(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isSortedNullList() throws Exception {
        List<Integer> actual = null;

        Sorter.isSorted(actual, Integer::compareTo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isSortedNullComparator() throws Exception {
        List<Integer> actual = Arrays.asList(1, 2, 3, 4);

        Sorter.isSorted(actual, null);
    }
}