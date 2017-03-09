package org.vitaly.homework04.memento;

/**
 * Created by vitaly on 2017-03-09.
 */
public class Memento {
    private Player currentPlayer;
    private Field field;

    public Memento(Player currentPlayer, Field field) {
        this.currentPlayer = currentPlayer;
        this.field = field.copy();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Field getField() {
        return field;
    }
}
