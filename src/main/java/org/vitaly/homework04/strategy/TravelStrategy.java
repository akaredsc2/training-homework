package org.vitaly.homework04.strategy;

/**
 * Created by vitaly on 2017-03-09.
 */
@FunctionalInterface
public interface TravelStrategy {
    double computeTravelSpeed(double moveSpeed);
}
