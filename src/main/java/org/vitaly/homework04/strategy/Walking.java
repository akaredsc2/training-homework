package org.vitaly.homework04.strategy;

/**
 * Created by vitaly on 2017-03-09.
 */
public class Walking implements TravelStrategy {
    private static Walking instance = new Walking();

    private Walking() {
    }

    public static Walking getInstance() {
        return instance;
    }

    @Override
    public double computeTravelSpeed(double moveSpeed) {
        return moveSpeed;
    }
}
