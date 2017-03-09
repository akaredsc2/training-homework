package org.vitaly.homework04.memento;

import static org.vitaly.homework04.memento.Field.FIELD_SIZE;
import static org.vitaly.util.InputChecker.requireNonNull;

/**
 * Created by vitaly on 2017-03-09.
 */
public class TicTacToe {
    private Player currentPlayer;
    private Field field;

    public TicTacToe() {
        currentPlayer = Player.FIRST;
        field = new Field();
    }

    public Memento turn(int row, int column) {
        Memento previousTurn = null;
        if (indexInBounds(row) && indexInBounds(column) && field.getCells()[row][column] == Cell.EMPTY) {
            previousTurn = createMemento();
            field.setCell(currentPlayer.getMark(), row, column);
            currentPlayer = currentPlayer == Player.FIRST ? Player.SECOND : Player.FIRST;
        }
        return previousTurn;
    }

    private boolean indexInBounds(int index) {
        return index >= 0 && index < FIELD_SIZE;
    }

    public Field getField() {
        return field.copy();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void restoreFromMemento(Memento memento) {
        requireNonNull(memento, "Memento must not be null!");

        currentPlayer = memento.getCurrentPlayer();
        field = memento.getField();
    }

    private Memento createMemento() {
        return new Memento(currentPlayer, field);
    }

    public boolean isOver() {
        Cell[][] cells = field.getCells();

        for (int i = 0; i < FIELD_SIZE; i++) {
            if (isRowFilledWithSameNonEmptyCells(cells[i])) {
                return true;
            }
        }

        for (int j = 0; j < FIELD_SIZE; j++) {
            if (isColumnFilledWithSameNonEmptyCells(cells, j)) {
                return true;
            }
        }

        if (isMainDiagonalFilledWithSameNonEmptyCells(cells)) {
            return true;
        }

        if (isSecondDiagonalFilledWithSameNonEmptyCells(cells)) {
            return true;
        }
        return false;
    }

    private boolean isSecondDiagonalFilledWithSameNonEmptyCells(Cell[][] cells) {
        return cells[1][1] != Cell.EMPTY && cells[2][0] == cells[1][1] && cells[1][1] == cells[0][2];
    }

    private boolean isMainDiagonalFilledWithSameNonEmptyCells(Cell[][] cells) {
        return cells[1][1] != Cell.EMPTY && cells[0][0] == cells[1][1] && cells[1][1] == cells[2][2];
    }

    private boolean isColumnFilledWithSameNonEmptyCells(Cell[][] cells, int j) {
        return cells[0][j] != Cell.EMPTY && cells[0][j] == cells[1][j] && cells[1][j] == cells[2][j];
    }

    private boolean isRowFilledWithSameNonEmptyCells(Cell[] cell) {
        return cell[0] != Cell.EMPTY && cell[0] == cell[1] && cell[1] == cell[2];
    }
}
