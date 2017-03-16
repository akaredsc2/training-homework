package org.vitaly.homework04.abstractFactory;

import static org.vitaly.util.InputChecker.*;

/**
 * Created by vitaly on 2017-03-16.
 */
public class MovieRental {
    private AbstractMediaFactory mediaFactory;

    public MovieRental() {
        this.mediaFactory = new MovieComponentFactory();
    }

    public Movie rentMovie(String name, String language) {
        requireNonEmptyString(name, NAME);
        requireNonEmptyString(language, LANGUAGE);

        Audio audio = mediaFactory.createAudio(name, language);
        Image image = mediaFactory.createImage(name);
        Text text = mediaFactory.createText(name, language);
        return new Movie(audio, image, text);
    }
}
