package org.vitaly.homework04.memento;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by vitaly on 2017-03-09.
 */
public class TicTacToeTest {
    private TicTacToe game;

    @Before
    public void setUp() throws Exception {
        game = new TicTacToe();
    }

    @Test
    public void successfulTurnChangesCurrentPlayer() throws Exception {
        Player previousPlayer = game.getCurrentPlayer();

        game.turn(0, 0);

        Player currentPlayer = game.getCurrentPlayer();
        assertThat(previousPlayer, not(equalTo(currentPlayer)));
    }

    @Test
    public void successfulTurnChangesField() throws Exception {
        Player previousPlayer = game.getCurrentPlayer();

        game.turn(0, 0);

        assertThat(game.getField().getCells()[0][0], allOf(
                not(equalTo(Cell.EMPTY)),
                equalTo(previousPlayer.getMark())
        ));
    }

    @Test
    public void successfulTurnReturnsNotNullMemento() throws Exception {
        Memento memento = game.turn(0, 0);

        assertThat(memento, notNullValue());
    }

    @Test
    public void failedTurnDoesNotChangeCurrentPlayer() throws Exception {
        Player previousPlayer = game.getCurrentPlayer();

        game.turn(-1, -1);

        Player currentPlayer = game.getCurrentPlayer();
        assertThat(previousPlayer, equalTo(currentPlayer));
    }

    @Test
    public void failedTurnDoesNotChangesField() throws Exception {
        Player previousPlayer = game.getCurrentPlayer();

        game.turn(0, 0);
        game.turn(0, 0);

        assertThat(game.getField().getCells()[0][0], allOf(
                not(equalTo(Cell.EMPTY)),
                not(equalTo(game.getCurrentPlayer().getMark())),
                equalTo(previousPlayer.getMark())
        ));
    }

    @Test
    public void failedTurnReturnsNullMemento() throws Exception {
        game.turn(0, 0);
        Memento memento = game.turn(0, 0);

        assertThat(memento, nullValue());
    }

    @Test
    public void restoreFromMemento() throws Exception {
        Field beforeTurnField = game.getField();
        Memento memento = game.turn(0, 0);
        Field currentField = game.getField();
        game.restoreFromMemento(memento);

        Field restoredField = game.getField();

        assertThat(restoredField, allOf(
                not(sameInstance(currentField)),
                not(equalTo(currentField)),
                equalTo(beforeTurnField)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void restoreFromNullMementoShouldThrowException() throws Exception {
        game.restoreFromMemento(null);
    }

    @Test
    public void isOverByRow() throws Exception {
        game.turn(0, 0);
        game.turn(0, 1);
        game.turn(1, 0);
        game.turn(1, 1);
        game.turn(2, 0);

        assertTrue(game.isOver());
    }

    @Test
    public void isOverByColumn() throws Exception {
        game.turn(0, 0);
        game.turn(1, 0);
        game.turn(0, 1);
        game.turn(1, 1);
        game.turn(0, 2);

        assertTrue(game.isOver());
    }

    @Test
    public void isOverByMainDiagonal() throws Exception {
        game.turn(0, 0);
        game.turn(1, 0);
        game.turn(1, 1);
        game.turn(2, 0);
        game.turn(2, 2);

        assertTrue(game.isOver());
    }

    @Test
    public void isOverBySecondDiagonal() throws Exception {
        game.turn(0, 2);
        game.turn(1, 0);
        game.turn(1, 1);
        game.turn(2, 2);
        game.turn(2, 0);

        assertTrue(game.isOver());
    }
}