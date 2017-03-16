package org.vitaly.homework04.abstractFactory;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by vitaly on 2017-03-16.
 */
public class VideoTest {
    private String name;
    private Image subtitles;

    @Before
    public void setUp() throws Exception {
        name = "Dark Knight";
        subtitles = new Video(name);
    }

    @Test
    public void playingVideoShowsMovieName() throws Exception {
        String playResult = subtitles.play();

        assertThat(playResult, allOf(
                notNullValue(),
                containsString(name)));
    }

    @Test
    public void differentVideosAreEqualThanAndOnlyThanWhenNamesAreEqual() throws Exception {
        Video otherVideo = new Video(name);

        assertThat(subtitles, equalTo(otherVideo));
    }

    @Test
    public void subtitlesWithDifferentNamesCannotBeEqual() throws Exception {
        Video otherVideo = new Video(name + " Rises");

        assertThat(subtitles, not(equalTo(otherVideo)));
    }
}