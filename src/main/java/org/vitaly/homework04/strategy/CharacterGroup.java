package org.vitaly.homework04.strategy;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.vitaly.util.InputChecker.requireNonNull;
import static org.vitaly.util.InputChecker.requirePositiveDouble;

/**
 * Created by vitaly on 2017-03-09.
 */
public class CharacterGroup {
    private Set<Character> characters;
    private Set<Character> magicians;
    private TravelStrategy travelStrategy;
    private double groupMoveSpeed;

    public CharacterGroup() {
        characters = new HashSet<>();
        magicians = new HashSet<>();
        travelStrategy = Walking.getInstance();
    }

    public Set<Character> getCharacters() {
        return Collections.unmodifiableSet(characters);
    }

    public TravelStrategy getTravelStrategy() {
        return travelStrategy;
    }

    public double getGroupMoveSpeed() {
        return groupMoveSpeed;
    }

    public void addToGroup(Character character) {
        requireNonNull(character, "Character must not be null!");

        if (character.getRace().isMagician()) {
            travelStrategy = FlyingAndWalking.getInstance();
            magicians.add(character);
        }
        if (characters.isEmpty() || character.getMoveSpeed() < groupMoveSpeed) {
            groupMoveSpeed = character.getMoveSpeed();
        }
        characters.add(character);
    }

    public void removeFromGroup(Character character) {
        requireNonNull(character, "Character must not be null!");

        characters.remove(character);
        magicians.remove(character);
        if (magicians.isEmpty()) {
            travelStrategy = Walking.getInstance();
        }
        if (characters.isEmpty()) {
            groupMoveSpeed = 0;
        } else {
            double newGroupSpeed = Double.POSITIVE_INFINITY;
            for (Character ch : characters) {
                newGroupSpeed = Math.min(newGroupSpeed, ch.getMoveSpeed());
            }
            groupMoveSpeed = newGroupSpeed;
        }
    }

    public double computeTravelTime(double distance) {
        if (distance == 0) {
            return 0;
        } else {
            requirePositiveDouble(distance, "Distance");

            double travelSpeed = travelStrategy.computeTravelSpeed(groupMoveSpeed);
            return distance / travelSpeed;
        }
    }
}
