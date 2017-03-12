package org.vitaly.homework04.battleships;

/**
 * Created by vitaly on 2017-03-12.
 */
public enum ShipType {
    SUBMARINE(1),
    DESTROYER(2),
    CRUISER(3),
    BATTLESHIP(4);

    private final int funnelCount;

    ShipType(int funnelCount) {
        this.funnelCount = funnelCount;
    }

    public int getFunnelCount() {
        return funnelCount;
    }
}
