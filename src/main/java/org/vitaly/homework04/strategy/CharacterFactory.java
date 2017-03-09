package org.vitaly.homework04.strategy;

import static org.vitaly.util.InputChecker.*;

/**
 * Created by vitaly on 2017-03-09.
 */
public class CharacterFactory {
    private static CharacterFactory instance = new CharacterFactory();

    private CharacterFactory() {
    }

    public static CharacterFactory getInstance() {
        return instance;
    }

    public Character createOrc(String name, double moveSpeed) {
        requireNonNull(name, NAME_MUST_NOT_BE_NULL);
        requirePositiveDouble(moveSpeed, "Move speed");

        return new Character(name, CharacterRace.ORC, moveSpeed);
    }

    public Character createTroll(String name, double moveSpeed) {
        requireNonNull(name, NAME_MUST_NOT_BE_NULL);
        requirePositiveDouble(moveSpeed, "Move speed");

        return new Character(name, CharacterRace.TROLL, moveSpeed);
    }

    public Character createPegasus(String name, double moveSpeed) {
        requireNonNull(name, NAME_MUST_NOT_BE_NULL);
        requirePositiveDouble(moveSpeed, "Move speed");

        return new Character(name, CharacterRace.PEGASUS, moveSpeed);
    }

    public Character createElf(String name, double moveSpeed) {
        requireNonNull(name, NAME_MUST_NOT_BE_NULL);
        requirePositiveDouble(moveSpeed, "Move speed");

        return new Character(name, CharacterRace.ELF, moveSpeed);
    }

    public Character createVampire(String name, double moveSpeed) {
        requireNonNull(name, NAME_MUST_NOT_BE_NULL);
        requirePositiveDouble(moveSpeed, "Move speed");

        return new Character(name, CharacterRace.VAMPIRE, moveSpeed);
    }

    public Character createHarpy(String name, double moveSpeed) {
        requireNonNull(name, NAME_MUST_NOT_BE_NULL);
        requirePositiveDouble(moveSpeed, "Move speed");

        return new Character(name, CharacterRace.HARPY, moveSpeed);
    }
}
