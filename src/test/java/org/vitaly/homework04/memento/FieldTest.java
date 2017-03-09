package org.vitaly.homework04.memento;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Created by vitaly on 2017-03-09.
 */
public class FieldTest {
    private Field field;

    @Before
    public void setUp() throws Exception {
        field = new Field();
    }

    @Test
    public void allNewFieldCellsAreEmpty() throws Exception {
        Cell[][] cells = field.getCells();

        for (Cell[] row : cells) {
            for (Cell cell : row) {
                assertEquals(cell, Cell.EMPTY);
            }
        }
    }

    @Test
    public void setCellChangesCellValue() throws Exception {
        int row = 0;
        int column = 0;
        field.setCell(Cell.CROSS, row, column);

        Cell actualCell = field.getCells()[row][column];

        assertThat(actualCell, equalTo(Cell.CROSS));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNullCellShouldThrowException() throws Exception {
        field.setCell(null, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCellAtNegativeRowShouldThrowException() throws Exception {
        field.setCell(Cell.CROSS, -1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCellAtNegativeColumnShouldThrowException() throws Exception {
        field.setCell(Cell.CROSS, 0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCellAtRowGreaterThanOrEqualToFieldSizeShouldThrowException() throws Exception {
        field.setCell(Cell.CROSS, Field.FIELD_SIZE, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCellAtColumnGreaterThanOrEqualToFieldSizeShouldThrowException() throws Exception {
        field.setCell(Cell.CROSS, 0, Field.FIELD_SIZE);
    }

    @Test
    public void copyReturnsNewField() throws Exception {
        Field copy = field.copy();

        assertThat(copy.getCells(), not(sameInstance(field.getCells())));
    }

    @Test
    public void copiedFieldContainsSameCells() throws Exception {
        field.setCell(Cell.CROSS, 0, 0);
        field.setCell(Cell.DONUT, 1, 1);
        field.setCell(Cell.CROSS, 2, 2);
        Field copy = field.copy();
        Cell[][] actualCells = copy.getCells();

        Cell[][] expectedCells = field.getCells();
        for (int i = 0; i < actualCells.length; i++) {
            for (int j = 0; j < actualCells[i].length; j++) {
                assertEquals(expectedCells[i][j], actualCells[i][j]);
            }
        }
    }

    @Test
    public void changingCopyDoesNotAffectOriginal() throws Exception {
        Field copy = field.copy();
        int row = 1;
        int column = 1;
        field.setCell(Cell.DONUT, row, column);
        Cell actualCell = copy.getCells()[row][column];

        assertThat(actualCell, allOf(
                equalTo(Cell.EMPTY),
                not(equalTo(Cell.DONUT))));
    }

    @Test
    public void equalsReturnsTrueOnFieldsWithSameContent() throws Exception {
        assertEquals(field, new Field());
    }
}