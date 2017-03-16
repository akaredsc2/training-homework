package org.vitaly.homework04.abstractFactory;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by vitaly on 2017-03-16.
 */
public class SubtitlesTest {
    private String name;
    private String language;
    private Text subtitles;

    @Before
    public void setUp() throws Exception {
        name = "Watchmen";
        language = "English";
        subtitles = new Subtitles(name, language);
    }

    @Test
    public void printingSubtitlesShowsMovieNameAndLanguage() throws Exception {
        String printResult = subtitles.print();

        assertThat(printResult, allOf(
                notNullValue(),
                containsString(name),
                containsString(language)));
    }

    @Test
    public void differentSubtitlesAreEqualThanAndOnlyThanWhenNameAndLanguageAreEqual() throws Exception {
        Text otherSubtitles = new Subtitles(name, language);

        assertThat(subtitles, equalTo(otherSubtitles));
    }

    @Test
    public void subtitlesWithDifferentNamesCannotBeEqual() throws Exception {
        Text otherSubtitles = new Subtitles(name + " ", language);

        assertThat(subtitles, not(equalTo(otherSubtitles)));
    }

    @Test
    public void subtitlesWithDifferentLanguagesCannotBeEqual() throws Exception {
        Audio otherVoiceOver = new VoiceOver(name, "Pure " + language);

        assertThat(subtitles, not(equalTo(otherVoiceOver)));
    }
}