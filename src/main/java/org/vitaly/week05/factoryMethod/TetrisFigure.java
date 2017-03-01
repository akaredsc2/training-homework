package org.vitaly.week05.factoryMethod;

import java.util.Random;

/**
 * Created by vitaly on 01.03.17.
 */
public enum TetrisFigure {
    SQUARE,
    STICK,
    LEFT_STAIR,
    RIGHT_STAIR,
    PYRAMID,
    SUPER_SQUARE,
    SUPER_STAIR;

    private static final Random random = new Random();

    public static TetrisFigure nextTetrisFigure() {
        int seed = Math.abs(random.nextInt() % TetrisFigure.values().length);
        return TetrisFigure.values()[seed];
    }
}
