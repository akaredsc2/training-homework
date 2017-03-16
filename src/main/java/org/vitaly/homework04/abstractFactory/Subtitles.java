package org.vitaly.homework04.abstractFactory;

/**
 * Created by vitaly on 2017-03-16.
 */
public class Subtitles implements Text {
    private String name;
    private String language;

    public Subtitles(String name, String language) {
        this.name = name;
        this.language = language;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLanguage() {
        return language;
    }

    @Override
    public String print() {
        return "Playing " + name + " voice over in " + language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subtitles subtitles = (Subtitles) o;

        return name.equals(subtitles.name) && language.equals(subtitles.language);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (language != null ? language.hashCode() : 0);
        return result;
    }
}
