package org.vitaly.homework04.observer;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by vitaly on 2017-03-02.
 */
public class NewspaperTest {
    @Test
    public void newspaperQualityIsLow() throws Exception {
        Press newspaper = new Newspaper("", "");

        PaperQuality actualPaperQuality = newspaper.getPaperQuality();

        PaperQuality expectedPaperQuality = PaperQuality.LOW;
        assertThat(actualPaperQuality, equalTo(expectedPaperQuality));
    }
}