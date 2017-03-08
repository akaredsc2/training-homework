package org.vitaly.homework04.abstractFactory;

import static org.vitaly.util.InputChecker.requireNonEmptyString;
import static org.vitaly.util.InputChecker.requireNonNull;

/**
 * Created by vitaly on 01.03.17.
 */
public class MovieFactory {
    private static MovieFactory instance = new MovieFactory();

    private MovieFactory() {
    }

    public static MovieFactory getInstance() {
        return instance;
    }

    public Movie newMovie(String movieName, String language) {
        requireNonNull(movieName, "Movie name must not be null!");
        requireNonNull(language, "Language name must not be null!");
        requireNonEmptyString(movieName, "Movie name must not be empty!");
        requireNonEmptyString(language, "Language name must not be empty!");
        return new Movie(movieName, language);
    }
}
