package org.vitaly.homework04.abstractFactory;

/**
 * Created by vitaly on 2017-03-16.
 */
public class VoiceOver implements Audio {
    private String name;
    private String language;

    public VoiceOver(String name, String language) {
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
    public String play() {
        return "Playing " + name + " subtitles in " + language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VoiceOver voiceOver = (VoiceOver) o;

        return name.equals(voiceOver.name) && language.equals(voiceOver.language);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + language.hashCode();
        return result;
    }
}
