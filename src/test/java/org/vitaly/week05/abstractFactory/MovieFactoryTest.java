package org.vitaly.week05.abstractFactory;

import org.junit.Test;

import java.util.Locale;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by vitaly on 01.03.17.
 */
public class MovieFactoryTest {
    private Movie movie;
    private MovieFactory movieFactory = MovieFactory.getInstance();
    private String language = "en";
    private String movieName = "Mad Max Fury Road";

    @Test
    public void movieNameIsCorrectlySet() throws Exception {
        movie = movieFactory.newMovie(movieName, language);

        String actualMovieName = movie.getName();

        assertThat(actualMovieName, equalTo(movieName));
    }

    @Test
    public void movieSoundtrackAndSubtitlesLanguagesAreEqual() throws Exception {
        movie = movieFactory.newMovie(movieName, language);

        String actualSoundTrack = movie.getSoundtrackLanguage();
        String actualSubtitles = movie.getSubtitlesLanguage();

        String expectedLanguage = new Locale(language).getDisplayLanguage();
        assertThat(actualSoundTrack, allOf(
                equalTo(actualSubtitles),
                equalTo(expectedLanguage)
        ));
    }

    @Test
    public void sameMovieWithDifferentLanguageHasDifferentSubtitles() throws Exception {
        movie = movieFactory.newMovie(movieName, language);
        Movie otherLanguageMovie = movieFactory.newMovie(movieName, "de");

        String actualSubtitlesLanguage = movie.getSubtitlesLanguage();

        String expectedOtherSubtitlesLanguage = otherLanguageMovie.getSubtitlesLanguage();
        assertThat(actualSubtitlesLanguage, not(equalTo(expectedOtherSubtitlesLanguage)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullMovieNameShouldThrowException() throws Exception {
        movieFactory.newMovie(null, language);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullLanguageNameShouldThrowException() throws Exception {
        movieFactory.newMovie(movieName, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyMovieNameShouldThrowException() throws Exception {
        movieFactory.newMovie("", language);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyLanguageNameShouldThrowException() throws Exception {
        movieFactory.newMovie(movieName, "");
    }
}