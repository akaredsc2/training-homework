package org.vitaly.homework04.abstractFactory;

import java.util.Locale;

/**
 * Created by vitaly on 01.03.17.
 */
public class Movie {
    private final String name;
    private final Locale locale;

    public Movie(String name, String language) {
        this.name = name;
        this.locale = new Locale(language);
    }

    public String getName() {
        return name;
    }

    public String getSoundtrackLanguage() {
        return locale.getDisplayLanguage();
    }

    public String getSubtitlesLanguage() {
        return locale.getDisplayLanguage();
    }
}
