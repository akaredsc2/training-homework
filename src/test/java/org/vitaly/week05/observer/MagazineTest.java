package org.vitaly.week05.observer;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by vitaly on 2017-03-02.
 */
public class MagazineTest {
    @Test
    public void magazineHasHighPaperQuality() throws Exception {
        Press magazine = new Magazine("", "");

        PaperQuality actualPaperQuality = magazine.getPaperQuality();

        PaperQuality expectedPaperQuality = PaperQuality.HIGH;
        assertThat(actualPaperQuality, equalTo(expectedPaperQuality));
    }
}