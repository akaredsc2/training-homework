package org.vitaly.homework04.strategy;

/**
 * Created by vitaly on 2017-03-09.
 */
public enum CharacterRace {
    ORC(Walking.getInstance(), false),
    TROLL(Walking.getInstance(), false),
    PEGASUS(FlyingAndWalking.getInstance(), false),
    ELF(Walking.getInstance(), false),
    VAMPIRE(FlyingAndWalking.getInstance(), true),
    HARPY(Flying.getInstance(), false);

    private TravelStrategy travelStrategy;
    private boolean isMagician;

    CharacterRace(TravelStrategy travelStrategy, boolean isMagician) {
        this.travelStrategy = travelStrategy;
        this.isMagician = isMagician;
    }

    public TravelStrategy getTravelStrategy() {
        return travelStrategy;
    }

    public boolean isMagician() {
        return isMagician;
    }
}
