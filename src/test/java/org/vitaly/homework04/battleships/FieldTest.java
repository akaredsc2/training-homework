package org.vitaly.homework04.battleships;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vitaly on 2017-03-12.
 */
public class FieldTest {
    private Field field;
    private Fleet fleet;
    private Ship ship1;

    @Before
    public void setUp() throws Exception {
        field = new Field();
        ship1 = Ship.createShip(3, 4, Direction.RIGHT, ShipType.CRUISER);
        fleet = new Fleet();

        fleet.addToFleet(Ship.createShip(1, 1, Direction.RIGHT, ShipType.SUBMARINE));
        fleet.addToFleet(Ship.createShip(1, 3, Direction.RIGHT, ShipType.SUBMARINE));
        fleet.addToFleet(Ship.createShip(1, 5, Direction.RIGHT, ShipType.SUBMARINE));
        fleet.addToFleet(Ship.createShip(1, 7, Direction.RIGHT, ShipType.SUBMARINE));
        fleet.addToFleet(Ship.createShip(3, 1, Direction.RIGHT, ShipType.DESTROYER));
        fleet.addToFleet(Ship.createShip(3, 4, Direction.RIGHT, ShipType.DESTROYER));
        fleet.addToFleet(Ship.createShip(3, 7, Direction.RIGHT, ShipType.DESTROYER));
        fleet.addToFleet(Ship.createShip(5, 1, Direction.RIGHT, ShipType.CRUISER));
        fleet.addToFleet(Ship.createShip(5, 5, Direction.RIGHT, ShipType.CRUISER));
        fleet.addToFleet(Ship.createShip(7, 1, Direction.RIGHT, ShipType.BATTLESHIP));
    }

    @Test
    public void newFieldFilledWithEmptyCells() throws Exception {
        Cell[][] cells = field.getCells();

        Cell expectedCell = Cell.EMPTY;
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                assertEquals(expectedCell, cell);
            }
        }
    }

    @Test
    public void anyShipCanBePlacedOnEmptyField() throws Exception {
        assertTrue(field.canBePlacedOnField(ship1));
    }

    @Test
    public void shipCannotBePlacedOnTopOfItself() throws Exception {
        field.placeShip(ship1);

        assertFalse(field.canBePlacedOnField(ship1));
    }

    @Test
    public void shipCannotTouchOtherShipFromLeftSide() throws Exception {
        Ship touchingShip = Ship.createShip(4, 3, Direction.UP, ShipType.BATTLESHIP);

        field.placeShip(ship1);

        assertFalse(field.canBePlacedOnField(touchingShip));
    }

    @Test
    public void shipCannotTouchOtherShipFromRightSide() throws Exception {
        Ship touchingShip = Ship.createShip(4, 7, Direction.UP, ShipType.BATTLESHIP);

        field.placeShip(ship1);

        assertFalse(field.canBePlacedOnField(touchingShip));
    }

    @Test
    public void shipCannotTouchOtherShipFromTopSide() throws Exception {
        Ship touchingShip = Ship.createShip(2, 5, Direction.LEFT, ShipType.SUBMARINE);

        field.placeShip(ship1);

        assertFalse(field.canBePlacedOnField(touchingShip));
    }

    @Test
    public void shipCannotTouchOtherShipFromBottomSide() throws Exception {
        Ship touchingShip = Ship.createShip(4, 6, Direction.RIGHT, ShipType.SUBMARINE);

        field.placeShip(ship1);

        assertFalse(field.canBePlacedOnField(touchingShip));
    }

    @Test
    public void shipCannotTouchOtherShipFromUpperLeftCorner() throws Exception {
        Ship touchingShip = Ship.createShip(2, 3, Direction.DOWN, ShipType.SUBMARINE);

        field.placeShip(ship1);

        assertFalse(field.canBePlacedOnField(touchingShip));
    }

    @Test
    public void shipCannotTouchOtherShipFromUpperRightCorner() throws Exception {
        Ship touchingShip = Ship.createShip(2, 7, Direction.UP, ShipType.SUBMARINE);

        field.placeShip(ship1);

        assertFalse(field.canBePlacedOnField(touchingShip));
    }

    @Test
    public void shipCannotTouchOtherShipFromLowerLeftCorner() throws Exception {
        Ship touchingShip = Ship.createShip(4, 3, Direction.DOWN, ShipType.SUBMARINE);

        field.placeShip(ship1);

        assertFalse(field.canBePlacedOnField(touchingShip));
    }

    @Test
    public void shipCannotTouchOtherShipFromLowerRightCorner() throws Exception {
        Ship touchingShip = Ship.createShip(4, 7, Direction.UP, ShipType.SUBMARINE);

        field.placeShip(ship1);

        assertFalse(field.canBePlacedOnField(touchingShip));
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkingIfNullShipCanBePlacedShouldThrowException() throws Exception {
        field.canBePlacedOnField(null);
    }

    @Test
    public void placeShipBetweenManyOtherShipsAndLeftBorder() throws Exception {
        Ship otherShip1 = Ship.createShip(2, 1, Direction.RIGHT, ShipType.SUBMARINE);
        Ship otherShip2 = Ship.createShip(3, 3, Direction.RIGHT, ShipType.SUBMARINE);
        Ship otherShip3 = Ship.createShip(6, 1, Direction.RIGHT, ShipType.SUBMARINE);
        Ship targetShip = Ship.createShip(4, 0, Direction.RIGHT, ShipType.DESTROYER);

        field.placeShip(otherShip1);
        field.placeShip(otherShip2);
        field.placeShip(otherShip3);

        assertTrue(field.canBePlacedOnField(targetShip));
    }

    @Test
    public void placeShipBetweenManyOtherShipsAndTopBorder() throws Exception {
        Ship otherShip1 = Ship.createShip(1, 2, Direction.DOWN, ShipType.SUBMARINE);
        Ship otherShip2 = Ship.createShip(3, 3, Direction.DOWN, ShipType.SUBMARINE);
        Ship otherShip3 = Ship.createShip(1, 6, Direction.DOWN, ShipType.SUBMARINE);
        Ship targetShip = Ship.createShip(0, 4, Direction.DOWN, ShipType.DESTROYER);

        field.placeShip(otherShip1);
        field.placeShip(otherShip2);
        field.placeShip(otherShip3);

        assertTrue(field.canBePlacedOnField(targetShip));
    }

    @Test
    public void placeShipBetweenManyOtherShipsAndDownBorder() throws Exception {
        Ship otherShip1 = Ship.createShip(8, 7, Direction.UP, ShipType.SUBMARINE);
        Ship otherShip2 = Ship.createShip(6, 6, Direction.UP, ShipType.SUBMARINE);
        Ship otherShip3 = Ship.createShip(8, 3, Direction.UP, ShipType.SUBMARINE);
        Ship targetShip = Ship.createShip(9, 5, Direction.UP, ShipType.DESTROYER);

        field.placeShip(otherShip1);
        field.placeShip(otherShip2);
        field.placeShip(otherShip3);

        assertTrue(field.canBePlacedOnField(targetShip));
    }

    @Test
    public void placeShipBetweenManyOtherShipsAndRightBorder() throws Exception {
        Ship otherShip1 = Ship.createShip(7, 8, Direction.LEFT, ShipType.SUBMARINE);
        Ship otherShip2 = Ship.createShip(6, 6, Direction.LEFT, ShipType.SUBMARINE);
        Ship otherShip3 = Ship.createShip(3, 8, Direction.LEFT, ShipType.SUBMARINE);
        Ship targetShip = Ship.createShip(5, 9, Direction.LEFT, ShipType.DESTROYER);

        field.placeShip(otherShip1);
        field.placeShip(otherShip2);
        field.placeShip(otherShip3);

        assertTrue(field.canBePlacedOnField(targetShip));
    }

    @Test(expected = IllegalArgumentException.class)
    public void placingNullShipShouldThrowException() throws Exception {
        field.placeShip(null);
    }

    @Test
    public void filledFieldContainsShips() throws Exception {
        field.fillWithShips(fleet);
        Cell[][] cells = field.getCells();
        int actualShipCellCount = 0;
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if (cell == Cell.SHIP) {
                    actualShipCellCount += 1;
                }
            }
        }

        int expectedShipCellsCount = Fleet.BATTLESHIP_MAX_COUNT * ShipType.BATTLESHIP.getFunnelCount()
                + Fleet.CRUISER_MAX_COUNT * ShipType.CRUISER.getFunnelCount()
                + Fleet.DESTROYER_MAX_COUNT * ShipType.DESTROYER.getFunnelCount()
                + Fleet.SUBMARINE_MAX_COUNT * ShipType.SUBMARINE.getFunnelCount();

        assertEquals(expectedShipCellsCount, actualShipCellCount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fillingFieldWithNullFleetShouldThrowException() throws Exception {
        field.fillWithShips(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fillingFieldWithNotStaffedFleetShouldThrowException() throws Exception {
        field.fillWithShips(new Fleet());
    }
}