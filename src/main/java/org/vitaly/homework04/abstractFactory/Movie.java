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

    public Audio getAudio() {
        return audio;
    }

    public Text getText() {
        return text;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Movie movie = (Movie) o;

        boolean isEqualAudio = audio != null ? audio.equals(movie.audio) : movie.audio == null;
        boolean isEqualImage = image != null ? image.equals(movie.image) : movie.image == null;
        boolean isEqualText = text != null ? text.equals(movie.text) : movie.text == null;
        return isEqualAudio && isEqualImage && isEqualText;
    }

    @Override
    public int hashCode() {
        int result = audio != null ? audio.hashCode() : 0;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
