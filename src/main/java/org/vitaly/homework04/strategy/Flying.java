package org.vitaly.homework04.strategy;

/**
 * Created by vitaly on 2017-03-09.
 */
public class Flying implements TravelStrategy {
    private static Flying instance = new Flying();

    private Flying() {
    }

    public static Flying getInstance() {
        return instance;
    }

    @Override
    public double computeTravelSpeed(double moveSpeed) {
        return moveSpeed * moveSpeed;
    }
}
