package org.vitaly.homework04.battleships;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static org.vitaly.util.InputChecker.*;

/**
 * Created by vitaly on 2017-03-12.
 */
public class Field {
    public static final int FIELD_SIZE = 10;
    public static final int SHIP_CELLS_COUNT = Fleet.BATTLESHIP_MAX_COUNT * ShipType.BATTLESHIP.getFunnelCount()
            + Fleet.CRUISER_MAX_COUNT * ShipType.CRUISER.getFunnelCount()
            + Fleet.DESTROYER_MAX_COUNT * ShipType.DESTROYER.getFunnelCount()
            + Fleet.SUBMARINE_MAX_COUNT * ShipType.SUBMARINE.getFunnelCount();

    private Cell[][] cells;
    private List<Ship> ships;

    public Field() {
        this.cells = new Cell[FIELD_SIZE][];
        for (int i = 0; i < cells.length; i += 1) {
            cells[i] = new Cell[FIELD_SIZE];
            Arrays.fill(cells[i], Cell.EMPTY);
        }
        this.ships = new ArrayList<>();
    }

    public Cell[][] getCells() {
        return cells;
    }

    public List<Ship> getShips() {
        return Collections.unmodifiableList(ships);
    }

    public void setCell(int row, int column, Cell value) {
        requireInRange(row, 0, FIELD_SIZE, "Row number must be between 0 inclusive and " + FIELD_SIZE + " exclusive!");
        requireInRange(column, 0, FIELD_SIZE,
                "Column number must be between 0 inclusive and " + FIELD_SIZE + " exclusive!");
        requireNonNull(value, "Cell value must not be null!");

        cells[row][column] = value;
    }

    public void fillWithShips(Fleet fleet) {
        requireNonNull(fleet, "Fleet must not be null!");
        if (!fleet.isStaffed()) {
            throw new IllegalArgumentException("Fleet must be staffed before filling field!");
        }

        ships = fleet.getShips();

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

        int adjustedCheckFromRow = max(0, checkFromRow);
        int adjustedCheckFromColumn = max(0, checkFromColumn);
        int adjustedCheckToRow = min(checkToRow, FIELD_SIZE - 1);
        int adjustedCheckToColumn = min(checkToColumn, FIELD_SIZE - 1);

        for (int i = adjustedCheckFromRow; i <= adjustedCheckToRow; i++) {
            for (int j = adjustedCheckFromColumn; j <= adjustedCheckToColumn; j++) {
                if (cells[i][j] != Cell.EMPTY) {
                    return false;
                }
            }
        }

        return true;
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

    public boolean isFilledWithShips() {
        int actualShipCellCount = 0;
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if (cell == Cell.SHIP || cell == Cell.FIRED_SHIP) {
                    actualShipCellCount += 1;
                }
            }
        }

        return actualShipCellCount == SHIP_CELLS_COUNT;
    }

    public int getFiredShipCount() {
        int firedShipCellCount = 0;
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if (cell == Cell.FIRED_SHIP) {
                    firedShipCellCount += 1;
                }
            }
        }
        return firedShipCellCount;
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

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(getCells());
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
}
