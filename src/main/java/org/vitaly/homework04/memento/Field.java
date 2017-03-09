package org.vitaly.homework04.memento;

import java.util.Arrays;

import static org.vitaly.util.InputChecker.*;

/**
 * Created by vitaly on 2017-03-09.
 */
public class Field {
    public static final int FIELD_SIZE = 3;
    private Cell[][] cells;

    public Field() {
        this.cells = new Cell[FIELD_SIZE][FIELD_SIZE];
        for (Cell[] cell : cells) {
            Arrays.fill(cell, Cell.EMPTY);
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCell(Cell value, int row, int column) {
        requireNonNull(value, "Cell value must not be null!");
        requireInRange(row, 0, FIELD_SIZE, ROW_NUMBER_MUST_BE_BETWEEN_0_INCLUSIVE_AND_3_EXCLUSIVE);
        requireInRange(column, 0, FIELD_SIZE, COLUMN_NUMBER_MUST_BE_BETWEEN_0_INCLUSIVE_AND_3_EXCLUSIVE);

        cells[row][column] = value;
    }

    public Field copy() {
        Field copy = new Field();
        for (int i = 0; i < FIELD_SIZE; i++) {
            System.arraycopy(this.cells[i], 0, copy.cells[i], 0, FIELD_SIZE);
        }
        return copy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Field field = (Field) o;

        return Arrays.deepEquals(getCells(), field.getCells());
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(getCells());
    }
}
