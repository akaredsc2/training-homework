package org.vitaly.homework04.observer;

import static org.vitaly.util.InputChecker.TITLE_MUST_NOT_BE_NULL;
import static org.vitaly.util.InputChecker.requireNonNull;

/**
 * Created by vitaly on 2017-03-02.
 */
public class GardeningPublisher implements Publisher {
    private static GardeningPublisher instance = new GardeningPublisher();

    private GardeningPublisher() {
    }

    public static GardeningPublisher getInstance() {
        return instance;
    }

    @Override
    public Newspaper publishNewspaper(String title) {
        requireNonNull(title, TITLE_MUST_NOT_BE_NULL);

        return new Newspaper("Sanctuary Garden", title);
    }

    @Override
    public Magazine publishMagazine(String title) {
        requireNonNull(title, TITLE_MUST_NOT_BE_NULL);

        return new Magazine("Gardens of the Moon", title);
    }
}
