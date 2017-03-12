package org.vitaly.homework04.battleships;

import java.util.Arrays;
import java.util.List;

import static org.vitaly.util.InputChecker.SHIP_MUST_NOT_BE_NULL;
import static org.vitaly.util.InputChecker.requireNonNull;

/**
 * Created by vitaly on 2017-03-12.
 */
public class Field {
    public static final int FIELD_SIZE = 10;
    private Cell[][] cells;

    public Field() {
        this.cells = new Cell[FIELD_SIZE][];
        for (int i = 0; i < cells.length; i += 1) {
            cells[i] = new Cell[FIELD_SIZE];
            Arrays.fill(cells[i], Cell.EMPTY);
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void fillWithShips(Fleet fleet) {
        requireNonNull(fleet, "Fleet must not be null!");
        if (!fleet.isStaffed()) {
            throw new IllegalArgumentException("Fleet must be staffed before filling field!");
        }

        List<Ship> ships = fleet.getShips();

        for (Ship ship : ships) {
            if (canBePlacedOnField(ship)) {
                placeShip(ship);
            }
        }
    }

    public boolean canBePlacedOnField(Ship ship) {
        requireNonNull(ship, SHIP_MUST_NOT_BE_NULL);

        int checkFromRow = -100;
        int checkFromColumn = -100;
        int checkToRow = -100;
        int checkToColumn = -100;

        if (ship.getDirection() == Direction.DOWN) {
            checkFromRow = ship.getOriginRow() - 1;
            checkFromColumn = ship.getOriginColumn() - 1;
            checkToRow = ship.getOriginRow() + ship.getCells().length;
            checkToColumn = ship.getOriginColumn() + 1;
        }
        if (ship.getDirection() == Direction.RIGHT) {
            checkFromRow = ship.getOriginRow() - 1;
            checkFromColumn = ship.getOriginColumn() - 1;
            checkToRow = ship.getOriginRow() + 1;
            checkToColumn = ship.getOriginColumn() + ship.getCells().length;
        }
        if (ship.getDirection() == Direction.UP) {
            checkFromRow = ship.getOriginRow() - ship.getCells().length;
            checkFromColumn = ship.getOriginColumn() - 1;
            checkToRow = ship.getOriginRow() + 1;
            checkToColumn = ship.getOriginColumn() + 1;
        }
        if (ship.getDirection() == Direction.LEFT) {
            checkFromRow = ship.getOriginRow() - 1;
            checkFromColumn = ship.getOriginColumn() - ship.getCells().length;
            checkToRow = ship.getOriginRow() + 1;
            checkToColumn = ship.getOriginColumn() + 1;
        }

        for (int i = checkFromRow; i <= checkToRow; i++) {
            for (int j = checkFromColumn; j <= checkToColumn; j++) {
                if (inField(i) && inField(j)) {
                    if (cells[i][j] != Cell.EMPTY) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean inField(int index) {
        return index >= 0 && index < FIELD_SIZE;
    }

    public void placeShip(Ship ship) {
        requireNonNull(ship, SHIP_MUST_NOT_BE_NULL);
        if (!canBePlacedOnField(ship)) {
            throw new IllegalArgumentException("Ship must not overlap other ships!");
        }

        int originRow = ship.getOriginRow();
        int originColumn = ship.getOriginColumn();
        int funnelCount = ship.getCells().length;
        Direction direction = ship.getDirection();
        Cell[] shipCells = ship.getCells();

        if (direction == Direction.DOWN) {
            for (int i = 0; i < funnelCount; i++) {
                this.cells[originRow + i][originColumn] = shipCells[i];
            }
        }
        if (direction == Direction.UP) {
            for (int i = 0; i < funnelCount; i++) {
                this.cells[originRow - i][originColumn] = shipCells[i];
            }
        }
        if (direction == Direction.RIGHT) {
            for (int i = 0; i < funnelCount; i++) {
                this.cells[originRow][originColumn + i] = shipCells[i];
            }
        }
        if (direction == Direction.LEFT) {
            for (int i = 0; i < funnelCount; i++) {
                this.cells[originRow][originColumn - i] = shipCells[i];
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                builder.append(cell.getSymbol());
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }
}
