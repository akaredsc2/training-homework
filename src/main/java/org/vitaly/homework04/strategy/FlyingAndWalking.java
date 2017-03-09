package org.vitaly.homework04.strategy;

/**
 * Created by vitaly on 2017-03-09.
 */
public class FlyingAndWalking implements TravelStrategy {
    private static FlyingAndWalking instance = new FlyingAndWalking();

    private FlyingAndWalking() {
    }

    public static FlyingAndWalking getInstance() {
        return instance;
    }

    @Override
    public double computeTravelSpeed(double moveSpeed) {
        TravelStrategy walking = Walking.getInstance();
        TravelStrategy flying = Flying.getInstance();
        return Math.max(walking.computeTravelSpeed(moveSpeed), flying.computeTravelSpeed(moveSpeed));
    }
}
