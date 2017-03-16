package org.vitaly.homework04.abstractFactory;

/**
 * Created by vitaly on 2017-03-16.
 */
public class Movie {
    private Audio audio;
    private Image image;
    private Text text;

    public Movie(Audio audio, Image image, Text text) {
        this.audio = audio;
        this.image = image;
        this.text = text;
    }

    public String play() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Playing ")
                .append(image.getName())
                .append(" with ")
                .append(audio.getLanguage())
                .append(" voice over and ")
                .append(text.getLanguage())
                .append(" subtitles.");
        return stringBuilder.toString();
    }
}
