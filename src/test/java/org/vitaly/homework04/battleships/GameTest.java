package org.vitaly.homework04.battleships;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

/**
 * Created by vitaly on 2017-03-12.
 */
public class GameTest {
    private Game game;

    @Before
    public void setUp() throws Exception {
        game = new Game();

        Ship submarine1 = Ship.createShip(1, 1, Direction.RIGHT, ShipType.SUBMARINE);
        Ship submarine2 = Ship.createShip(1, 3, Direction.RIGHT, ShipType.SUBMARINE);
        Ship submarine3 = Ship.createShip(1, 5, Direction.RIGHT, ShipType.SUBMARINE);
        Ship submarine4 = Ship.createShip(1, 7, Direction.RIGHT, ShipType.SUBMARINE);
        Ship destroyer1 = Ship.createShip(3, 1, Direction.RIGHT, ShipType.DESTROYER);
        Ship destroyer2 = Ship.createShip(3, 4, Direction.RIGHT, ShipType.DESTROYER);
        Ship destroyer3 = Ship.createShip(3, 7, Direction.RIGHT, ShipType.DESTROYER);
        Ship cruiser1 = Ship.createShip(5, 1, Direction.RIGHT, ShipType.CRUISER);
        Ship cruiser2 = Ship.createShip(5, 5, Direction.RIGHT, ShipType.CRUISER);
        Ship battleship = Ship.createShip(7, 1, Direction.RIGHT, ShipType.BATTLESHIP);

        Fleet fleet = new Fleet();
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

        Field firstPlayerField = new Field();
        firstPlayerField.fillWithShips(fleet);

        Field secondPlayerField = new Field();
        secondPlayerField.fillWithShips(fleet);

        game.getFirstPlayer().fill(firstPlayerField);
        game.getSecondPlayer().fill(secondPlayerField);
    }

    @Test
    public void newGameCurrentPlayerIsFirstPlayer() throws Exception {
        assertEquals(game.getFirstPlayer(), game.getCurrentPlayer());
    }

    @Test
    public void newGameNextPlayerIsSecondPlayer() throws Exception {
        assertEquals(game.getSecondPlayer(), game.getNextPlayer());
    }

    @Test
    public void playersHaveDifferentFieldInstances() throws Exception {
        Field firstPlayerOwnField = game.getFirstPlayer().getOwnField();
        Field secondPlayerOwnField = game.getSecondPlayer().getOwnField();

        assertThat(firstPlayerOwnField, not(sameInstance(secondPlayerOwnField)));
    }

    @Test
    public void newGameIsNotOver() throws Exception {
        assertFalse(game.isOver());
    }

    @Test
    public void turnNotHittingShipSwapsPlayers() throws Exception {
        game.turn(0, 0);

        assertEquals(game.getSecondPlayer(), game.getCurrentPlayer());
    }

    @Test
    public void turnHittingShipDoesNotSwapPlayers() throws Exception {
        game.turn(1, 1);

        assertEquals(game.getFirstPlayer(), game.getCurrentPlayer());
    }

    @Test
    public void notHittingUpdatesOpponentFieldOfCurrentPlayer() throws Exception {
        int row = 0;
        int column = 0;
        game.turn(row, column);

        Field firstPlayerOpponentField = game.getFirstPlayer().getOpponentField();

        assertThat(firstPlayerOpponentField.getCells()[row][column], allOf(
                not(equalTo(Cell.EMPTY)),
                equalTo(Cell.FIRED)));
    }

    @Test
    public void notHittingUpdatesOwnFieldOfNextPlayer() throws Exception {
        int row = 0;
        int column = 0;
        game.turn(row, column);

        Field secondPlayerOwnField = game.getSecondPlayer().getOwnField();

        assertThat(secondPlayerOwnField.getCells()[row][column], allOf(
                not(equalTo(Cell.EMPTY)),
                equalTo(Cell.FIRED)));
    }

    @Test
    public void hittingUpdatesOpponentFieldOfCurrentPlayer() throws Exception {
        int row = 1;
        int column = 1;
        game.turn(row, column);

        Field firstPlayerOpponentField = game.getFirstPlayer().getOpponentField();

        assertThat(firstPlayerOpponentField.getCells()[row][column], allOf(
                not(equalTo(Cell.EMPTY)),
                equalTo(Cell.FIRED_SHIP)));
    }

    @Test
    public void hittingUpdatesOwnFieldOfNextPlayer() throws Exception {
        int row = 1;
        int column = 1;
        game.turn(row, column);

        Field secondPlayerOwnField = game.getSecondPlayer().getOwnField();

        assertThat(secondPlayerOwnField.getCells()[row][column], allOf(
                not(equalTo(Cell.EMPTY)),
                equalTo(Cell.FIRED_SHIP)));
    }

    @Test
    public void hittingSameCellBySamePlayerDoesNotChangeValue() throws Exception {
        int row = 0;
        int column = 0;

        game.turn(row, column);
        Cell cellAfterFirstHit = game.getSecondPlayer().getOwnField().getCells()[row][column];
        game.turn(row, column);
        game.turn(row, column);
        Cell cellAfterSecondHit = game.getSecondPlayer().getOwnField().getCells()[row][column];

        assertThat(cellAfterSecondHit, equalTo(cellAfterFirstHit));
    }

    @Test
    public void firingOpponentsShipsOversGame() throws Exception {
        game.turn(1, 1);

        game.turn(1, 3);

        game.turn(1, 5);

        game.turn(1, 7);

        game.turn(3, 1);
        game.turn(3, 2);

        game.turn(3, 4);
        game.turn(3, 5);

        game.turn(3, 7);
        game.turn(3, 8);

        game.turn(5, 1);
        game.turn(5, 2);
        game.turn(5, 3);

        game.turn(5, 5);
        game.turn(5, 6);
        game.turn(5, 7);

        game.turn(7, 1);
        game.turn(7, 2);
        game.turn(7, 3);
        game.turn(7, 4);

        assertTrue(game.isOver());
    }
}