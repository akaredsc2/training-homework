package org.vitaly.homework04.battleships;

/**
 * Created by vitaly on 2017-03-12.
 */
public class Game {
    private Player firstPlayer;
    private Player secondPlayer;
    private Player currentPlayer;
    private Player nextPlayer;

    public Game() {
        this.firstPlayer = new Player();
        this.secondPlayer = new Player();
        this.currentPlayer = firstPlayer;
        this.nextPlayer = secondPlayer;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public void turn(int row, int column) {
        Cell[][] currentPlayerCells = currentPlayer.getOpponentField().getCells();
        boolean isCurrentPlayerCellFired = currentPlayerCells[row][column] == Cell.FIRED
                || currentPlayerCells[row][column] == Cell.FIRED_SHIP;

        if (isInBound(row) && isInBound(column) && !isCurrentPlayerCellFired) {
            Cell[][] opponentCells = nextPlayer.getOwnField().getCells();

            if (opponentCells[row][column] == Cell.EMPTY) {
                currentPlayerCells[row][column] = Cell.FIRED;
                opponentCells[row][column] = Cell.FIRED;

                swapPlayers();
            } else if (opponentCells[row][column] == Cell.SHIP) {
                currentPlayerCells[row][column] = Cell.FIRED_SHIP;
                opponentCells[row][column] = Cell.FIRED_SHIP;
            }
        }
    }

    private void swapPlayers() {
        Player temp = currentPlayer;
        currentPlayer = nextPlayer;
        nextPlayer = temp;
    }

    private boolean isInBound(int row) {
        return row >= 0 && row < Field.FIELD_SIZE;
    }

    public boolean isOver() {
        return firstPlayer.getOwnField().getFiredShipCount() == Field.SHIP_CELLS_COUNT ||
                secondPlayer.getOwnField().getFiredShipCount() == Field.SHIP_CELLS_COUNT;
    }
}
