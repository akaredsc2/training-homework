package org.vitaly.homework04.battleships;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by vitaly on 2017-03-12.
 */
public class PlayerTest {
    private Fleet fleet;
    private Field field;
    private Player player;

    @Before
    public void setUp() throws Exception {
        player = new Player();
        field = new Field();
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
    public void fillingFilledFieldMakeOwnFieldNotNew() throws Exception {
        field.fillWithShips(fleet);

        player.fill(field);

        assertNotEquals(new Field(), field);
    }

    @Test
    public void fillingFilledFieldMakeOwnFieldFilledWithShips() throws Exception {
        field.fillWithShips(fleet);

        player.fill(field);

        assertTrue(player.getOwnField().isFilledWithShips());
    }

    @Test(expected = IllegalArgumentException.class)
    public void fillNullFieldShouldThrowException() throws Exception {
        player.fill(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fillNotFilledFieldShouldThrowException() throws Exception {
        player.fill(field);
    }

    @Test
    public void newPlayerOwnFilledIsNew() throws Exception {
        assertEquals(field, player.getOwnField());
    }

    @Test
    public void newPlayerOpponentFilledIsNew() throws Exception {
        assertEquals(field, player.getOpponentField());
    }
}