package org.vitaly.homework04.strategy;

import java.util.HashSet;
import java.util.Set;

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

    public void addToGroup(Character character) {
        if (character.getRace().isMagician()) {
            travelStrategy = FlyingAndWalking.getInstance();
            magicians.add(character);
        }
        if (character.getMoveSpeed() < groupMoveSpeed) {
            groupMoveSpeed = character.getMoveSpeed();
        }
        characters.add(character);
    }

    public void removeFromGroup(Character character) {
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

    public boolean isEmpty() {
        return characters.isEmpty();
    }

    public double computeTravelTime(double distance) {
        double travelSpeed = travelStrategy.computeTravelSpeed(groupMoveSpeed);
        return distance / travelSpeed;
    }
}
