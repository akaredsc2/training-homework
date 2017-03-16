package org.vitaly.homework04.abstractFactory;

import static org.vitaly.util.InputChecker.*;

/**
 * Created by vitaly on 2017-03-16.
 */
public class MovieComponentFactory implements AbstractMediaFactory {
    @Override
    public Audio createAudio(String name, String language) {
        requireNonEmptyString(name, NAME);
        requireNonEmptyString(language, LANGUAGE);

        return new VoiceOver(name, language);
    }

    @Override
    public Image createImage(String name) {
        requireNonEmptyString(name, NAME);

        return new Video(name);
    }

    @Override
    public Text createText(String name, String language) {
        requireNonEmptyString(name, NAME);
        requireNonEmptyString(language, LANGUAGE);

        return new Subtitles(name, language);
    }
}
