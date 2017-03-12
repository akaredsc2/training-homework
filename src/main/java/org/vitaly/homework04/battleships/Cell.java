package org.vitaly.homework04.battleships;

/**
 * Created by vitaly on 2017-03-12.
 */
public enum Cell {
    EMPTY('0'),
    FIRED('*'),
    SHIP('#'),
    FIRED_SHIP('X');
    private final char symbol;

    Cell(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
