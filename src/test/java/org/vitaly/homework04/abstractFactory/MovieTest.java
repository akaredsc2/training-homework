package org.vitaly.homework04.abstractFactory;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.stringContainsInOrder;

/**
 * Created by vitaly on 2017-03-16.
 */
public class MovieTest {
    private String name;
    private String otherName;
    private String language;
    private String otherLanguage;
    private AbstractMediaFactory factory;
    private Movie movie;
    private Audio audio;
    private Image image;
    private Text text;

    @Before
    public void setUp() throws Exception {
        name = "Road to Eternity";
        otherName = "Walkway to Eternity";
        language = "Italian";
        otherLanguage = "Spanish";
        factory = new MovieComponentFactory();
        audio = factory.createAudio(name, language);
        image = factory.createImage(name);
        text = factory.createText(name, language);
        movie = new Movie(audio, image, text);
    }

    @Test
    public void playingMovieShowsNameAndSubtitlesLanguageAndVoiceOverLanguage() throws Exception {
        String playResult = movie.play();

        ArrayList<String> strings = new ArrayList<>();
        strings.add(name);
        strings.add(language + " voice over");
        strings.add(language + " subtitles");

        assertThat(playResult, allOf(
                notNullValue(),
                stringContainsInOrder(strings)));
    }

    @Test
    public void moviesWithSameAudioImageAndTextAreEqual() throws Exception {
        Movie otherMovie = new Movie(audio, image, text);

        assertThat(movie, equalTo(otherMovie));
    }

    @Test
    public void moviesWithDifferentAudioAreNotEqual() throws Exception {
        Audio otherAudio = factory.createAudio(otherName, otherLanguage);
        Movie otherMovie = new Movie(otherAudio, image, text);

        assertThat(movie, not(equalTo(otherMovie)));
    }

    @Test
    public void moviesWithDifferentImageAreNotEqual() throws Exception {
        Image otherImage = factory.createImage(otherName);
        Movie otherMovie = new Movie(audio, otherImage, text);

        assertThat(movie, not(equalTo(otherMovie)));
    }

    @Test
    public void moviesWithDifferentTextAreNotEqual() throws Exception {
        Text otherText = factory.createText(otherName, otherLanguage);
        Movie otherMovie = new Movie(audio, image, otherText);

        assertThat(movie, not(equalTo(otherMovie)));
    }
}