package org.vitaly.homework04.battleships;

import java.util.Arrays;

import static org.vitaly.util.InputChecker.requireInRange;
import static org.vitaly.util.InputChecker.requireNonNull;

/**
 * Created by vitaly on 2017-03-12.
 */
public class Ship {
    private Cell[] cells;
    private final int originRow;
    private final int originColumn;
    private Direction direction;

    private Ship(int originRow, int originColumn, Direction direction, ShipType shipType) {
        this.cells = new Cell[shipType.getFunnelCount()];
        Arrays.fill(cells, Cell.SHIP);
        this.originRow = originRow;
        this.originColumn = originColumn;
        this.direction = direction;
    }

    public Cell[] getCells() {
        return cells;
    }

    public int getOriginRow() {
        return originRow;
    }

    public int getOriginColumn() {
        return originColumn;
    }

    public Direction getDirection() {
        return direction;
    }

    public static Ship createShip(int originRow, int originColumn, Direction direction, ShipType shipType) {
        requireInRange(originRow, 0, Field.FIELD_SIZE,
                "Origin row must be between 0 inclusive and " + Field.FIELD_SIZE + " exclusive!");
        requireInRange(originColumn, 0, Field.FIELD_SIZE,
                "Origin column must be between 0 inclusive and " + Field.FIELD_SIZE + " exclusive!");
        requireNonNull(direction, "Direction must not be null!");
        requireNonNull(shipType, "Ship type must not be null!");

        if (direction == Direction.DOWN && originRow + shipType.getFunnelCount() - 1 >= Field.FIELD_SIZE) {
            throw new IllegalArgumentException("Ship goes beyond bottom border!");
        }

        if (direction == Direction.UP && originRow - shipType.getFunnelCount() + 1 < 0) {
            throw new IllegalArgumentException("Ship goes beyond top border!");
        }

        if (direction == Direction.RIGHT && originColumn + shipType.getFunnelCount() - 1 >= Field.FIELD_SIZE) {
            throw new IllegalArgumentException("Ship goes beyond right border!");
        }

        if (direction == Direction.LEFT && originColumn - shipType.getFunnelCount() + 1 < 0) {
            throw new IllegalArgumentException("Ship goes beyond left border!");
        }

        return new Ship(originRow, originColumn, direction, shipType);
    }
}
