package org.vitaly.homework04.battleships;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.vitaly.util.InputChecker.SHIP_MUST_NOT_BE_NULL;
import static org.vitaly.util.InputChecker.requireNonNull;

/**
 * Created by vitaly on 2017-03-12.
 */
public class Fleet {
    public static final int SUBMARINE_MAX_COUNT = 4;
    public static final int DESTROYER_MAX_COUNT = 3;
    public static final int CRUISER_MAX_COUNT = 2;
    public static final int BATTLESHIP_MAX_COUNT = 1;

    private Field currentArrangement;
    private List<Ship> ships;
    private int submarineCount;
    private int destroyerCount;
    private int cruiserCount;
    private int battleshipCount;

    public Fleet() {
        this.currentArrangement = new Field();
        this.ships = new ArrayList<>();
    }

    public List<Ship> getShips() {
        return Collections.unmodifiableList(ships);
    }

    public boolean addToFleet(Ship ship) {
        requireNonNull(ship, SHIP_MUST_NOT_BE_NULL);

        boolean addedAsSubmarine = acceptAsSubmarine(ship);
        boolean addedAsDestroyer = addedAsSubmarine || acceptAsDestroyer(ship);
        boolean addedAsCruiser = addedAsDestroyer || acceptAsCruiser(ship);
        boolean addedAsShip = addedAsCruiser || acceptAsBattleship(ship);

        return !isStaffed() && addedAsShip;
    }

    public boolean acceptAsSubmarine(Ship ship) {
        requireNonNull(ship, SHIP_MUST_NOT_BE_NULL);

        if (ship.getShipType() == ShipType.SUBMARINE && submarineCount + 1 <= SUBMARINE_MAX_COUNT
                && currentArrangement.canBePlacedOnField(ship)) {
            submarineCount += 1;
            acceptShip(ship);
            return true;
        }
        return false;
    }

    public boolean acceptAsDestroyer(Ship ship) {
        requireNonNull(ship, SHIP_MUST_NOT_BE_NULL);

        if (ship.getShipType() == ShipType.DESTROYER && destroyerCount + 1 <= DESTROYER_MAX_COUNT
                && currentArrangement.canBePlacedOnField(ship)) {
            destroyerCount += 1;
            acceptShip(ship);
            return true;
        }
        return false;
    }

    public boolean acceptAsCruiser(Ship ship) {
        requireNonNull(ship, SHIP_MUST_NOT_BE_NULL);

        if (ship.getShipType() == ShipType.CRUISER && cruiserCount + 1 <= CRUISER_MAX_COUNT
                && currentArrangement.canBePlacedOnField(ship)) {
            cruiserCount += 1;
            acceptShip(ship);
            return true;
        }
        return false;
    }

    public boolean acceptAsBattleship(Ship ship) {
        requireNonNull(ship, SHIP_MUST_NOT_BE_NULL);

        if (ship.getShipType() == ShipType.BATTLESHIP && battleshipCount + 1 <= BATTLESHIP_MAX_COUNT
                && currentArrangement.canBePlacedOnField(ship)) {
            battleshipCount += 1;
            acceptShip(ship);
            return true;
        }
        return false;
    }

    private void acceptShip(Ship ship) {
        ships.add(ship);
        currentArrangement.placeShip(ship);
    }

    public boolean isStaffed() {
        return (submarineCount + destroyerCount + cruiserCount + battleshipCount)
                == (SUBMARINE_MAX_COUNT + DESTROYER_MAX_COUNT + CRUISER_MAX_COUNT + BATTLESHIP_MAX_COUNT);
    }
}
