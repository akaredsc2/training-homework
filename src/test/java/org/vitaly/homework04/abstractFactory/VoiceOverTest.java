package org.vitaly.homework04.abstractFactory;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by vitaly on 2017-03-16.
 */
public class VoiceOverTest {
    private String name;
    private String language;
    private Audio voiceOver;

    @Before
    public void setUp() throws Exception {
        name = "John Wick";
        language = "Ukrainian";
        voiceOver = new VoiceOver(name, language);
    }

    @Test
    public void playingVoiceOverShowsMovieNameAndLanguage() throws Exception {
        String playResult = voiceOver.play();

        assertThat(playResult, allOf(
                notNullValue(),
                containsString(name),
                containsString(language)));
    }

    @Test
    public void differentVoiceOversAreEqualThanAndOnlyThanWhenNameAndLanguageAreEqual() throws Exception {
        Audio otherVoiceOver = new VoiceOver(name, language);

        assertThat(voiceOver, equalTo(otherVoiceOver));
    }

    @Test
    public void voiceOversWithDifferentNamesCannotBeEqual() throws Exception {
        Audio otherVoiceOver = new VoiceOver(name + " Chapter 2", language);

        assertThat(voiceOver, not(equalTo(otherVoiceOver)));
    }

    @Test
    public void voiceOversWithDifferentLanguagesCannotBeEqual() throws Exception {
        Audio otherVoiceOver = new VoiceOver(name, "Not " + language);

        assertThat(voiceOver, not(equalTo(otherVoiceOver)));
    }
}