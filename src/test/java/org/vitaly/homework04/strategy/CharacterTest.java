package org.vitaly.homework04.strategy;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

/**
 * Created by vitaly on 2017-03-09.
 */
public class CharacterTest {
    private Character character;
    private String name;
    private CharacterRace race;
    private int moveSpeed;
    private double error;

    @Before
    public void setUp() throws Exception {
        name = "Grommash Hellscream";
        race = CharacterRace.ORC;
        moveSpeed = 100;
        character = Character.createCharacter(name, race, moveSpeed);
        error = 0.000_001;
    }

    @Test
    public void computeTravelTimeOfZeroDistance() throws Exception {
        double actualTravelTime = character.computeTravelTime(0);

        double expectedTravelTime = 0;
        assertThat(actualTravelTime, closeTo(expectedTravelTime, error));
    }

    @Test
    public void computeTravelTime() throws Exception {
        double distance = 100;
        double actualTravelTime = character.computeTravelTime(distance);

        double expectedTravelTime = distance / race.getTravelStrategy().computeTravelSpeed(moveSpeed);
        assertThat(actualTravelTime, closeTo(expectedTravelTime, error));
    }

    @Test(expected = IllegalArgumentException.class)
    public void computeTravelTimeOfNegativeDistanceShouldThrowException() throws Exception {
        character.computeTravelTime(-1);
    }

    @Test
    public void createdCharacterHasCorrectName() throws Exception {
        String actualName = character.getName();

        assertThat(actualName, equalTo(name));
    }

    @Test
    public void createdCharacterHasCorrectRace() throws Exception {
        CharacterRace actualRace = character.getRace();

        assertThat(actualRace, equalTo(race));
    }

    @Test
    public void createdCharacterHasCorrectMoveSpeed() throws Exception {
        double actualMoveSpeed = character.getMoveSpeed();

        assertThat(actualMoveSpeed, closeTo(moveSpeed, error));
    }

    @Test(expected = IllegalArgumentException.class)
    public void createCharacterWithNullNameShouldThrowException() throws Exception {
        Character.createCharacter(null, race, moveSpeed);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createCharacterEmptyNameShouldThrowException() throws Exception {
        Character.createCharacter("", race, moveSpeed);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createCharacterNullRaceShouldThrowException() throws Exception {
        Character.createCharacter(name, null, moveSpeed);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createCharacterNonPositiveMoveSpeedShouldThrowException() throws Exception {
        Character.createCharacter(name, race, 0);
    }
}