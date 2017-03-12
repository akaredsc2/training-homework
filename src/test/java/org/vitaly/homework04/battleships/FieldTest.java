package org.vitaly.homework04.battleships;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.empty;
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
    public void newFieldIsNotFilledWithShips() throws Exception {
        assertFalse(field.isFilledWithShips());
    }

    @Test
    public void newFieldShipListIsEmpty() throws Exception {
        assertThat(field.getShips(), empty());
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
    public void filledFieldIsFilled() throws Exception {
        fillFieldWithFleet();

        assertTrue(field.isFilledWithShips());
    }

    private void fillFieldWithFleet() {
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

        field.fillWithShips(fleet);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fillingFieldWithNullFleetShouldThrowException() throws Exception {
        field.fillWithShips(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fillingFieldWithNotStaffedFleetShouldThrowException() throws Exception {
        field.fillWithShips(new Fleet());
    }

    @Test
    public void setCellChangesCellValue() throws Exception {
        Cell expectedCellValue = Cell.FIRED;
        int row = 0;
        int column = 0;

        field.setCell(row, column, expectedCellValue);
        Cell actualCellValue = field.getCells()[row][column];

        assertThat(actualCellValue, allOf(
                not(equalTo(Cell.EMPTY)),
                equalTo(expectedCellValue)));
    }

    @Test
    public void setCellDoesNotAffectsShip() throws Exception {
        fillFieldWithFleet();
        int row = 1;
        int column = 1;

        field.setCell(row, column, Cell.FIRED_SHIP);
        Ship ship = field.getShips().get(0);
        Cell actualCell = ship.getCells()[0];

        assertThat(actualCell, not(equalTo(Cell.FIRED_SHIP)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCellWithRowLessThanZeroShouldThrowException() throws Exception {
        field.setCell(-1, 0, Cell.FIRED);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCellWithColumnLessThanZeroShouldThrowException() throws Exception {
        field.setCell(Field.FIELD_SIZE, 0, Cell.FIRED);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCellWithRowGreaterThanOrEqualToFieldSizeShouldThrowException() throws Exception {
        field.setCell(0, -1, Cell.FIRED);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCellWithColumnGreaterThanOrEqualToFieldSizeShouldThrowException() throws Exception {
        field.setCell(0, Field.FIELD_SIZE, Cell.FIRED);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNullCellShouldThrowException() throws Exception {
        field.setCell(0, 0, null);
    }

    @Test
    public void newFieldHasZeroFiredShips() throws Exception {
        assertEquals(0, field.getFiredShipCount());
    }

    @Test
    public void unfiredFieldHasZeroFiredShips() throws Exception {
        fillFieldWithFleet();

        assertEquals(0, field.getFiredShipCount());
    }

    @Test
    public void firingShipsChangeFiredCount() throws Exception {
        fillFieldWithFleet();

        int row = 1;
        int column = 1;
        field.setCell(row, column, Cell.FIRED_SHIP);

        int expected = 1;
        assertEquals(expected, field.getFiredShipCount());
    }
}