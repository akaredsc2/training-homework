package org.vitaly.homework04.battleships;

import static org.vitaly.util.InputChecker.requireNonNull;

/**
 * Created by vitaly on 2017-03-12.
 */
public class Player {
    private Field ownField;
    private Field opponentField;

    public Player() {
        this.ownField = new Field();
        this.opponentField = new Field();
    }

    public Field getOwnField() {
        return ownField;
    }

    public Field getOpponentField() {
        return opponentField;
    }

    public void fill(Field field) {
        requireNonNull(field, "Field must not be null!");
        if (!field.isFilledWithShips()) {
            throw new IllegalArgumentException("Supplied field is not filled with right amount of ships!");
        }

        this.ownField = field;
    }
}
