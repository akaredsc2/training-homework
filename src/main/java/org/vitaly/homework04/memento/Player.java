package org.vitaly.homework04.memento;

/**
 * Created by vitaly on 2017-03-09.
 */
public enum Player {
    FIRST(Cell.CROSS),
    SECOND(Cell.DONUT);

    private Cell mark;

    Player(Cell mark) {
        this.mark = mark;
    }

    public Cell getMark() {
        return mark;
    }
}
