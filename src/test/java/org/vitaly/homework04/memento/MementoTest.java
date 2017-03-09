package org.vitaly.homework04.memento;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by vitaly on 2017-03-09.
 */
public class MementoTest {
    private Player player;
    private Field field;
    private Memento memento;

    @Before
    public void setUp() throws Exception {
        player = Player.FIRST;
        field = new Field();
        memento = new Memento(player, field);
    }

    @Test
    public void changingPlayerDoesNotAffectMemento() throws Exception {
        player = Player.SECOND;

        assertThat(player, allOf(
                not(sameInstance(memento.getCurrentPlayer())),
                not(equalTo(memento.getCurrentPlayer()))));
    }

    @Test
    public void mementoFieldIsDifferentInstanceFromOriginalField() throws Exception {
        int row = 1;
        int column = 1;
        field.setCell(Cell.DONUT, row, column);

        Cell[][] mementoCells = memento.getField().getCells();
        assertThat(mementoCells, not(sameInstance(field.getCells())));
    }

    @Test
    public void changingFieldDoesNotAffectMemento() throws Exception {
        int row = 1;
        int column = 1;
        field.setCell(Cell.DONUT, row, column);

        Cell mementoCell = memento.getField().getCells()[row][column];
        assertThat(mementoCell, allOf(
                not(equalTo(Cell.DONUT)),
                equalTo(Cell.EMPTY)));
    }
}