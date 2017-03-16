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
        return "Playing " + name + " voice over in " + language;
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

        return (name != null ? name.equals(voiceOver.name) : voiceOver.name == null)
                && (language != null ? language.equals(voiceOver.language) : voiceOver.language == null);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (language != null ? language.hashCode() : 0);
        return result;
    }
}
