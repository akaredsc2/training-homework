package org.vitaly.homework04.strategy;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by vitaly on 2017-03-09.
 */
public class CharacterGroupTest {
    private Character secondSlowest;
    private Character slowest;
    private Character magician;
    private Character fastest;
    private CharacterGroup group;
    private double error = 0.000_001;

    @Before
    public void setUp() throws Exception {
        secondSlowest = Character.createCharacter("Garrosh Hellscream", CharacterRace.ORC, 1);
        slowest = Character.createCharacter("Vol'jin", CharacterRace.TROLL, 0.8);
        magician = Character.createCharacter("Dracula", CharacterRace.VAMPIRE, 0.9);
        fastest = Character.createCharacter("Fury Shelda", CharacterRace.HARPY, 1.5);
        group = new CharacterGroup();
    }

    @Test
    public void newCharacterGroupHasWalkingTravelStrategy() throws Exception {
        assertThat(group.getTravelStrategy(), instanceOf(Walking.class));
    }

    @Test
    public void newCharacterGroupHasNoCharactersIn() throws Exception {
        assertThat(group.getCharacters(), empty());
    }

    @Test
    public void newCharacterGroupHasZeroMoveSpeed() throws Exception {
        assertThat(group.getGroupMoveSpeed(), closeTo(0, error));
    }

    @Test
    public void charactersCorrectlyAddingToGroup() throws Exception {
        group.addToGroup(secondSlowest);
        group.addToGroup(slowest);
        group.addToGroup(magician);
        group.addToGroup(fastest);

        Set<Character> characters = group.getCharacters();

        assertThat(characters, allOf(
                not(emptyIterable()),
                iterableWithSize(4),
                hasItems(secondSlowest, slowest, magician, fastest)));
    }

    @Test
    public void notAddingSameCharactersTwice() throws Exception {
        group.addToGroup(secondSlowest);
        group.addToGroup(secondSlowest);

        Set<Character> characters = group.getCharacters();

        assertThat(characters, allOf(
                not(emptyIterable()),
                iterableWithSize(1),
                hasItem(secondSlowest)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addingNullCharacterShouldThrowException() throws Exception {
        group.addToGroup(null);
    }

    @Test
    public void groupWithoutMagicianHasWalkingTravelStrategy() throws Exception {
        group.addToGroup(secondSlowest);
        group.addToGroup(slowest);
        group.addToGroup(fastest);

        TravelStrategy strategy = group.getTravelStrategy();

        assertThat(strategy, instanceOf(Walking.class));
    }

    @Test
    public void groupWithMagicianHasFlyingAndWalkingTravelStrategy() throws Exception {
        group.addToGroup(secondSlowest);
        group.addToGroup(magician);

        TravelStrategy strategy = group.getTravelStrategy();

        assertThat(strategy, instanceOf(FlyingAndWalking.class));
    }

    @Test
    public void groupSpeedIsEqualToSlowestMemberSpeed() throws Exception {
        group.addToGroup(fastest);
        group.addToGroup(slowest);
        group.addToGroup(secondSlowest);

        double groupMoveSpeed = group.getGroupMoveSpeed();

        assertThat(groupMoveSpeed, closeTo(slowest.getMoveSpeed(), error));
    }

    @Test
    public void removingSlowestMemberUpdatesGroupSpeed() throws Exception {
        group.addToGroup(secondSlowest);
        group.addToGroup(slowest);
        group.removeFromGroup(slowest);

        double groupMoveSpeed = group.getGroupMoveSpeed();

        assertThat(groupMoveSpeed, allOf(
                not(closeTo(slowest.getMoveSpeed(), error)),
                closeTo(secondSlowest.getMoveSpeed(), error)));
    }

    @Test
    public void removingNonSlowestMemberDoesNotUpdateGroupSpeed() throws Exception {
        group.addToGroup(secondSlowest);
        group.addToGroup(slowest);
        group.removeFromGroup(secondSlowest);

        double groupMoveSpeed = group.getGroupMoveSpeed();

        assertThat(groupMoveSpeed, closeTo(slowest.getMoveSpeed(), error));
    }

    @Test
    public void removingAllMagiciansFromGroupRevertTravelStrategyToWalking() throws Exception {
        group.addToGroup(secondSlowest);
        group.addToGroup(magician);
        group.removeFromGroup(magician);

        TravelStrategy travelStrategy = group.getTravelStrategy();

        assertThat(travelStrategy, instanceOf(Walking.class));
    }

    @Test
    public void removingEveryoneFromGroupSetsTravelStrategyToWalking() throws Exception {
        group.addToGroup(secondSlowest);
        group.removeFromGroup(secondSlowest);

        TravelStrategy travelStrategy = group.getTravelStrategy();

        assertThat(travelStrategy, instanceOf(Walking.class));
    }

    @Test
    public void removingEveryoneFromGroupSetsMoveSpeedToZero() throws Exception {
        group.addToGroup(secondSlowest);
        group.removeFromGroup(secondSlowest);

        double groupMoveSpeed = group.getGroupMoveSpeed();

        assertThat(groupMoveSpeed, closeTo(0, error));
    }

    @Test
    public void removingEveryoneFromGroupMakesGroupEmpty() throws Exception {
        group.addToGroup(fastest);
        group.removeFromGroup(fastest);

        assertThat(group.getCharacters(), empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void removingNullCharacterShouldThrowException() throws Exception {
        group.removeFromGroup(null);
    }

    @Test
    public void computeTravelTimeOfZeroDistance() throws Exception {
        group.addToGroup(fastest);
        double actualTravelTime = group.computeTravelTime(0);

        double expectedTravelTime = 0;
        MatcherAssert.assertThat(actualTravelTime, closeTo(expectedTravelTime, error));
    }

    @Test
    public void computeTravelTimeWithMagician() throws Exception {
        group.addToGroup(magician);
        group.addToGroup(slowest);
        double distance = 100;
        double actualTravelTime = group.computeTravelTime(distance);

        double expectedTravelTime = distance / FlyingAndWalking.getInstance().computeTravelSpeed(slowest.getMoveSpeed());
        MatcherAssert.assertThat(actualTravelTime, closeTo(expectedTravelTime, error));
    }

    @Test
    public void computeTravelTimeWithoutMagician() throws Exception {
        group.addToGroup(secondSlowest);
        group.addToGroup(fastest);
        double distance = 100;
        double actualTravelTime = group.computeTravelTime(distance);

        double expectedTravelTime = distance / Walking.getInstance().computeTravelSpeed(secondSlowest.getMoveSpeed());
        MatcherAssert.assertThat(actualTravelTime, closeTo(expectedTravelTime, error));
    }

    @Test(expected = IllegalArgumentException.class)
    public void computeTravelTimeOfNegativeDistanceShouldThrowException() throws Exception {
        group.computeTravelTime(-1);
    }
}