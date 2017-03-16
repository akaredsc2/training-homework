package org.vitaly.homework04.strategy;

import org.vitaly.util.InputChecker;

import static org.vitaly.util.InputChecker.requireNonEmptyString;
import static org.vitaly.util.InputChecker.requireNonNull;
import static org.vitaly.util.InputChecker.requirePositiveDouble;

/**
 * Created by vitaly on 2017-03-09.
 */
public class Character {
    private String name;
    private CharacterRace race;
    private double moveSpeed;

    private Character(String name, CharacterRace race, double moveSpeed) {
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
        if (Math.abs(distance) < 0.000_001) {
            return 0;
        } else {
            InputChecker.requirePositiveDouble(distance, "Distance");

            double travelSpeed = race.getTravelStrategy().computeTravelSpeed(moveSpeed);
            return distance / travelSpeed;
        }
    }

    public static Character createCharacter(String name, CharacterRace race, double moveSpeed) {
        requireNonEmptyString(name, "Name must not be empty string!");
        requireNonNull(race, "Race must not be null!");
        requirePositiveDouble(moveSpeed, "Move speed");

        return new Character(name, race, moveSpeed);
    }
}
