package org.vitaly.homework04.strategy;

/**
 * Created by vitaly on 2017-03-09.
 */
public class Character {
    private String name;
    private CharacterRace race;
    private double moveSpeed;

    public Character(String name, CharacterRace race, double moveSpeed) {
        this.name = name;
        this.race = race;
        this.moveSpeed = moveSpeed;
    }

    public String getName() {
        return name;
    }

    public CharacterRace getRace() {
        return race;
    }

    public double getMoveSpeed() {
        return moveSpeed;
    }

    public double computeTravelTime(double distance) {
        double travelSpeed =  race.getTravelStrategy().computeTravelSpeed(moveSpeed);
        return distance / travelSpeed;
    }
}
