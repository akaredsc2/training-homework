package org.vitaly.homework04.battleships;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by vitaly on 2017-03-12.
 */
public class ShipTest {
    private Ship ship;

    @Before
    public void setUp() throws Exception {
        ship = null;
    }

    @Test
    public void createdShipConsistsOfShipCells() throws Exception {
        ship = Ship.createShip(0, 0, Direction.DOWN, ShipType.BATTLESHIP);
        Cell[] cells = ship.getCells();

        Cell expectedCell = Cell.SHIP;
        for (Cell cell : cells) {
            assertEquals(expectedCell, cell);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingShipWithNegativeOriginRowThrowsException() throws Exception {
        Ship.createShip(-1, 0, Direction.DOWN, ShipType.SUBMARINE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingShipWithOriginRowGreaterThanOrEqualsToFieldSizeThrowsException() throws Exception {
        Ship.createShip(Field.FIELD_SIZE, 0, Direction.DOWN, ShipType.SUBMARINE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingShipWithNegativeOriginColumnThrowsException() throws Exception {
        Ship.createShip(0, -1, Direction.DOWN, ShipType.SUBMARINE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingShipWithOriginColumnGreaterThanOrEqualsToFieldSizeThrowsException() throws Exception {
        Ship.createShip(0, Field.FIELD_SIZE, Direction.DOWN, ShipType.SUBMARINE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingShipWithNullDirectionShouldThrowException() throws Exception {
        Ship.createShip(0, 0, null, ShipType.CRUISER);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingShipWithNullShipTypeShouldThrowException() throws Exception {
        Ship.createShip(0, 0, Direction.UP, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingShipThatGoesBeyondBottomBorderShouldThrowException() throws Exception {
        Ship.createShip(Field.FIELD_SIZE - 1, 0, Direction.DOWN, ShipType.DESTROYER);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingShipThatGoesBeyondTopBorderShouldThrowException() throws Exception {
        Ship.createShip(0, 0, Direction.UP, ShipType.DESTROYER);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingShipThatGoesBeyondRightBorderShouldThrowException() throws Exception {
        Ship.createShip(0, Field.FIELD_SIZE - 1, Direction.RIGHT, ShipType.DESTROYER);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingShipThatGoesBeyondLeftBorderShouldThrowException() throws Exception {
        Ship.createShip(0, 0, Direction.LEFT, ShipType.DESTROYER);
    }
}