package org.vitaly.week05.factoryMethod;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

/**
 * Created by vitaly on 01.03.17.
 */
public class TetrisFigureTest {
    @Test
    public void gettingDifferentTetrisFigures() throws Exception {
        Set<TetrisFigure> tetrisFigureSet = new HashSet<>();

        for (int i = 0; i < 1000; i++) {
            tetrisFigureSet.add(TetrisFigure.nextTetrisFigure());
        }

        assertThat(tetrisFigureSet, containsInAnyOrder(TetrisFigure.values()));
    }
}