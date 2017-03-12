package org.vitaly.homework04.battleships;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;

/**
 * Created by vitaly on 2017-03-12.
 */

public class FleetTest {
    private Ship submarine1;
    private Ship submarine2;
    private Ship submarine3;
    private Ship submarine4;

    private Ship destroyer1;
    private Ship destroyer2;
    private Ship destroyer3;

    private Ship cruiser1;
    private Ship cruiser2;

    private Ship battleship;

    private Fleet fleet;

    @Before
    public void setUp() throws Exception {
        fleet = new Fleet();
        submarine1 = Ship.createShip(1, 1, Direction.RIGHT, ShipType.SUBMARINE);
        submarine2 = Ship.createShip(1, 3, Direction.RIGHT, ShipType.SUBMARINE);
        submarine3 = Ship.createShip(1, 5, Direction.RIGHT, ShipType.SUBMARINE);
        submarine4 = Ship.createShip(1, 7, Direction.RIGHT, ShipType.SUBMARINE);
        destroyer1 = Ship.createShip(3, 1, Direction.RIGHT, ShipType.DESTROYER);
        destroyer2 = Ship.createShip(3, 4, Direction.RIGHT, ShipType.DESTROYER);
        destroyer3 = Ship.createShip(3, 7, Direction.RIGHT, ShipType.DESTROYER);
        cruiser1 = Ship.createShip(5, 1, Direction.RIGHT, ShipType.CRUISER);
        cruiser2 = Ship.createShip(5, 5, Direction.RIGHT, ShipType.CRUISER);
        battleship = Ship.createShip(7, 1, Direction.RIGHT, ShipType.BATTLESHIP);
    }

    @Test
    public void newFleetIsNotStaffed() throws Exception {
        assertFalse(fleet.isStaffed());
    }

    @Test
    public void staffedFleetIsStaffed() throws Exception {
        staffShips();

        assertTrue(fleet.isStaffed());
    }

    @Test
    public void addingShipsAfterFleetIsStaffedFails() throws Exception {
        Ship oneMoreShip = Ship.createShip(9, 0, Direction.LEFT, ShipType.SUBMARINE);

        staffShips();

        assertFalse(fleet.addToFleet(oneMoreShip));
    }

    @Test
    public void staffedFleetContainsAllSuppliedShips() throws Exception {
        staffShips();

        assertThat(fleet.getShips(), hasItems(
                submarine1, submarine2, submarine3, submarine4,
                destroyer1, destroyer2, destroyer3,
                cruiser1, cruiser2,
                battleship));
    }

    private void staffShips() {
        fleet.addToFleet(submarine1);
        fleet.addToFleet(submarine2);
        fleet.addToFleet(submarine3);
        fleet.addToFleet(submarine4);

        fleet.addToFleet(destroyer1);
        fleet.addToFleet(destroyer2);
        fleet.addToFleet(destroyer3);

        fleet.addToFleet(cruiser1);
        fleet.addToFleet(cruiser2);

        fleet.addToFleet(battleship);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullShipShouldThrowException() throws Exception {
        fleet.addToFleet(null);
    }

    @Test
    public void acceptingSubmarineAsSubmarineIsSuccessful() throws Exception {
        assertTrue(fleet.acceptAsSubmarine(submarine1));
    }

    @Test
    public void acceptingSubmarineAfterSubmarinesAreStaffedFails() throws Exception {
        Ship submarine5 = Ship.createShip(9, 0, Direction.RIGHT, ShipType.SUBMARINE);

        fleet.acceptAsSubmarine(submarine1);
        fleet.acceptAsSubmarine(submarine2);
        fleet.acceptAsSubmarine(submarine3);
        fleet.acceptAsSubmarine(submarine4);

        assertFalse(fleet.acceptAsSubmarine(submarine5));
    }

    @Test
    public void acceptingDestroyerAsSubmarineIsNotSuccessful() throws Exception {
        assertFalse(fleet.acceptAsSubmarine(destroyer1));
    }

    @Test
    public void acceptingCruiserAsSubmarineIsNotSuccessful() throws Exception {
        assertFalse(fleet.acceptAsSubmarine(cruiser1));
    }

    @Test
    public void acceptingBattleshipAsSubmarineIsNotSuccessful() throws Exception {
        assertFalse(fleet.acceptAsSubmarine(battleship));
    }

    @Test(expected = IllegalArgumentException.class)
    public void acceptingNullShipAsSubmarineShouldThrowException() throws Exception {
        fleet.acceptAsSubmarine(null);
    }

    @Test
    public void acceptingSubmarineAsDestroyerIsNotSuccessful() throws Exception {
        assertFalse(fleet.acceptAsDestroyer(submarine1));
    }

    @Test
    public void acceptingDestroyerAsDestroyerIsSuccessful() throws Exception {
        assertTrue(fleet.acceptAsDestroyer(destroyer1));
    }

    @Test
    public void acceptingDestroyerAfterDestroyersAreStaffedFails() throws Exception {
        Ship destroyer4 = Ship.createShip(9, 0, Direction.RIGHT, ShipType.DESTROYER);

        fleet.acceptAsSubmarine(destroyer1);
        fleet.acceptAsSubmarine(destroyer2);
        fleet.acceptAsSubmarine(destroyer3);

        assertFalse(fleet.acceptAsSubmarine(destroyer4));
    }

    @Test
    public void acceptingCruiserAsDestroyerIsNotSuccessful() throws Exception {
        assertFalse(fleet.acceptAsDestroyer(cruiser1));
    }

    @Test
    public void acceptingBattleshipAsDestroyerIsNotSuccessful() throws Exception {
        assertFalse(fleet.acceptAsDestroyer(battleship));
    }

    @Test(expected = IllegalArgumentException.class)
    public void acceptingNullShipAsDestroyerShouldThrowException() throws Exception {
        fleet.acceptAsDestroyer(null);
    }

    @Test
    public void acceptingSubmarineAsCruiserIsNotSuccessful() throws Exception {
        assertFalse(fleet.acceptAsCruiser(submarine1));
    }

    @Test
    public void acceptingDestroyerAsCruiserIsNotSuccessful() throws Exception {
        assertFalse(fleet.acceptAsCruiser(destroyer1));
    }

    @Test
    public void acceptingCruiserAsCruiserIsSuccessful() throws Exception {
        assertTrue(fleet.acceptAsCruiser(cruiser1));
    }

    @Test
    public void acceptingCruiserAfterCruisersAreStaffedFails() throws Exception {
        Ship cruiser3 = Ship.createShip(9, 0, Direction.RIGHT, ShipType.CRUISER);

        fleet.acceptAsSubmarine(cruiser1);
        fleet.acceptAsSubmarine(cruiser2);

        assertFalse(fleet.acceptAsSubmarine(cruiser3));
    }

    @Test
    public void acceptingBattleshipAsCruiserIsNotSuccessful() throws Exception {
        assertFalse(fleet.acceptAsCruiser(battleship));
    }

    @Test(expected = IllegalArgumentException.class)
    public void acceptingNullShipAsCruiserShouldThrowException() throws Exception {
        fleet.acceptAsCruiser(null);
    }

    @Test
    public void acceptingSubmarineAsBattleshipIsNotSuccessful() throws Exception {
        assertFalse(fleet.acceptAsBattleship(submarine1));
    }

    @Test
    public void acceptingBattleshipAsBattleshipIsSuccessful() throws Exception {
        assertTrue(fleet.acceptAsBattleship(battleship));
    }

    @Test
    public void acceptingDestroyerAsBattleshipIsNotSuccessful() throws Exception {
        assertFalse(fleet.acceptAsBattleship(destroyer1));
    }

    @Test
    public void acceptingCruiserAsBattleshipIsNotSuccessful() throws Exception {
        assertFalse(fleet.acceptAsBattleship(cruiser1));
    }

    @Test
    public void acceptingBattleshipAfterBattleshipsAreStaffedFails() throws Exception {
        Ship battleship2 = Ship.createShip(9, 0, Direction.RIGHT, ShipType.BATTLESHIP);

        fleet.acceptAsSubmarine(battleship);

        assertFalse(fleet.acceptAsSubmarine(battleship2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void acceptingNullShipAsBattleshipShouldThrowException() throws Exception {
        fleet.acceptAsBattleship(null);
    }
}