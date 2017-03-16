package org.vitaly.homework04.abstractFactory;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by vitaly on 2017-03-16.
 */
public class MovieRentalTest {
    private MovieRental movieRental;
    private String name;
    private String language;
    private Movie movie;

    @Before
    public void setUp() throws Exception {
        movieRental = new MovieRental();
        name = "Full Metal Jacket";
        language = "English";
        movie = movieRental.rentMovie(name, language);
    }

    @Test
    public void rentedMovieTextAndAudioLanguagesAreEqual() throws Exception {
        String textLanguage = movie.getAudio().getLanguage();
        String audioLanguage = movie.getText().getLanguage();

        assertThat(textLanguage, equalTo(audioLanguage));
    }

    @Test
    public void rentingSameMovieWithSameLanguageReturnsDifferentInstances() throws Exception {
        Movie secondMovie = movieRental.rentMovie(name, language);

        assertThat(secondMovie, allOf(
                notNullValue(),
                equalTo(movie),
                not(sameInstance(movie))));
    }

    @Test(expected = IllegalArgumentException.class)
    public void rentingMovieWithEmptyNameShouldThrowException() throws Exception {
        movieRental.rentMovie("", language);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rentingMovieWithNullNameShouldThrowException() throws Exception {
        movieRental.rentMovie(null, language);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rentingMovieWithEmptyLanguageShouldThrowException() throws Exception {
        movieRental.rentMovie(name, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void rentingMovieWithNullLanguageShouldThrowException() throws Exception {
        movieRental.rentMovie(name, null);
    }
}