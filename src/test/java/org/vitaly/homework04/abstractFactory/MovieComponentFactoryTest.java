package org.vitaly.homework04.abstractFactory;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by vitaly on 2017-03-16.
 */
public class MovieComponentFactoryTest {
    private AbstractMediaFactory factory;
    private String language;
    private String name;

    @Before
    public void setUp() throws Exception {
        factory = new MovieComponentFactory();
        name = "Mad Max Fury Road";
        language = "English";
    }

    @Test
    public void createdAudioIsAlwaysNewInstance() throws Exception {
        Audio firstAudio = factory.createAudio(name, language);
        Audio secondAudio = factory.createAudio(name, language);

        assertThat(firstAudio, allOf(
                notNullValue(),
                equalTo(secondAudio),
                not(sameInstance(secondAudio))));
    }

    @Test(expected = IllegalArgumentException.class)
    public void createAudioWithEmptyNameShouldThrowException() throws Exception {
        factory.createAudio("", language);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createAudioWithEmptyLanguageShouldThrowException() throws Exception {
        factory.createAudio(name, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createAudioWithNullNameShouldThrowException() throws Exception {
        factory.createAudio(null, language);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createAudioWithNullLanguageShouldThrowException() throws Exception {
        factory.createAudio(name, null);
    }

    @Test
    public void createdImageIsAlwaysNewInstance() throws Exception {
        Image firstImage = factory.createImage(name);
        Image secondImage = factory.createImage(name);

        assertThat(firstImage, allOf(
                notNullValue(),
                equalTo(secondImage),
                not(sameInstance(secondImage))));
    }

    @Test(expected = IllegalArgumentException.class)
    public void createImageWithEmptyNameShouldThrowException() throws Exception {
        factory.createImage("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createImageWithNullNameShouldThrowException() throws Exception {
        factory.createImage(null);
    }

    @Test
    public void createdTextIsAlwaysNewInstance() throws Exception {
        Text firstText = factory.createText(name, language);
        Text secondText = factory.createText(name, language);

        assertThat(firstText, allOf(
                notNullValue(),
                equalTo(secondText),
                not(sameInstance(secondText))));
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTextWithEmptyNameShouldThrowException() throws Exception {
        factory.createText("", language);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTextWithEmptyLanguageShouldThrowException() throws Exception {
        factory.createText(name, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTextWithNullNameShouldThrowException() throws Exception {
        factory.createText(null, language);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTextWithNullLanguageShouldThrowException() throws Exception {
        factory.createText(name, null);
    }
}
