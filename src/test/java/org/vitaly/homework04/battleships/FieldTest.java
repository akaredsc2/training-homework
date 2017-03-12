package org.vitaly.homework04.battleships;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by vitaly on 2017-03-12.
 */
public class FieldTest {
    private Field field;
    private List<Ship> ships;
    private Ship ship1;

    @Before
    public void setUp() throws Exception {
        field = new Field();
        ships = new ArrayList<>();
        ship1 = Ship.createShip(3, 4, Direction.RIGHT, ShipType.CRUISER);
        ships.add(ship1);
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
}