package org.vitaly.week05.observer;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by vitaly on 2017-03-02.
 */
public class GardeningPublisherTest {
    private Publisher publisher = GardeningPublisher.getInstance();
    private String title;
    private Press press;
    private String expectedPartOfName;

    @Before
    public void setUp() throws Exception {
        title = "Garden title";
        expectedPartOfName = "garden";
    }

    @Test
    public void newspaperHasCorrectTitle() throws Exception {
        press = publisher.publishNewspaper(title);

        String actualTitle = press.getTitle();

        String expectedTitle = title;
        assertThat(actualTitle, allOf(
                notNullValue(),
                equalTo(expectedTitle)));
    }

    @Test
    public void newspaperHasLowPaperQuality() throws Exception {
        press = publisher.publishNewspaper(title);

        PaperQuality actualPaperQuality = press.getPaperQuality();

        PaperQuality expectedPaperQuality = PaperQuality.LOW;
        assertThat(actualPaperQuality, equalTo(expectedPaperQuality));
    }

    @Test
    public void newspaperNameContainsWordGarden() throws Exception {
        press = publisher.publishNewspaper(title);

        String actualName = press.getName().toLowerCase();

        assertThat(actualName, containsString(expectedPartOfName));
    }

    @Test(expected = IllegalArgumentException.class)
    public void publishNewspaperWithNullTitleShouldThrowException() throws Exception {
        publisher.publishNewspaper(null);
    }

    @Test
    public void magazineHasCorrectTitle() throws Exception {
        press = publisher.publishMagazine(title);

        String actualTitle = press.getTitle();

        String expectedTitle = title;
        assertThat(actualTitle, allOf(
                notNullValue(),
                equalTo(expectedTitle)));
    }

    @Test
    public void magazineHasLowPaperQuality() throws Exception {
        press = publisher.publishMagazine(title);

        PaperQuality actualPaperQuality = press.getPaperQuality();

        PaperQuality expectedPaperQuality = PaperQuality.HIGH;
        assertThat(actualPaperQuality, equalTo(expectedPaperQuality));
    }

    @Test
    public void magazineNameContainsWordGarden() throws Exception {
        press = publisher.publishMagazine(title);

        String actualName = press.getName().toLowerCase();

        assertThat(actualName, containsString(expectedPartOfName));
    }

    @Test(expected = IllegalArgumentException.class)
    public void publishMagazineWithNullTitleShouldThrowException() throws Exception {
        publisher.publishMagazine(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sendNullPressShouldThrowException() throws Exception {
        publisher.sendPress(null, new NovaPoshta());
    }

    @Test(expected = IllegalArgumentException.class)
    public void sendToNullPostOfficePressShouldThrowException() throws Exception {
        publisher.sendPress(publisher.publishMagazine(title), null);
    }

    @Test
    public void sentPressReceivedByPostOffice() throws Exception {
        press = publisher.publishMagazine(title);
        PostOffice postOffice = new NovaPoshta();

        publisher.sendPress(press, postOffice);
        List<Press> actualPressList = postOffice.getReceivedPressArchive();

        assertThat(actualPressList, contains(press));
    }
}