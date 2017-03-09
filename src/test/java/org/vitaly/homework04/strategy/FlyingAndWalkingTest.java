package org.vitaly.homework04.strategy;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.*;

/**
 * Created by vitaly on 2017-03-09.
 */
public class FlyingAndWalkingTest {
    private FlyingAndWalking flyingAndWalking;
    private double error;

    @Before
    public void setUp() throws Exception {
        flyingAndWalking = FlyingAndWalking.getInstance();
        error = 0.000_001;
    }

    @Test
    public void computeTravelSpeedEqualToFlyingForHighSpeed() throws Exception {
        TravelStrategy flying = Flying.getInstance();
        double moveSpeed = 100;

        double actualSpeed = flyingAndWalking.computeTravelSpeed(moveSpeed);

        double expectedSpeed = flying.computeTravelSpeed(moveSpeed);
        assertThat(actualSpeed, closeTo(expectedSpeed, error));
    }

    @Test
    public void computeTravelSpeedEqualToWalkingForLowSpeed() throws Exception {
        TravelStrategy walking = Walking.getInstance();
        double moveSpeed = 0.5;

        double actualSpeed = flyingAndWalking.computeTravelSpeed(moveSpeed);

        double expectedSpeed = walking.computeTravelSpeed(moveSpeed);
        assertThat(actualSpeed, closeTo(expectedSpeed, error));
    }
}