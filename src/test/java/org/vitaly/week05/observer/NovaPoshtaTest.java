package org.vitaly.week05.observer;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

/**
 * Created by vitaly on 2017-03-02.
 */
public class NovaPoshtaTest {
    private PostOffice postOffice;
    private Subscriber subscriber;

    @Before
    public void setUp() throws Exception {
        postOffice = new NovaPoshta();
        subscriber = new JuniorJavaDeveloper();
    }

    @Test
    public void addSubscriber() throws Exception {
        postOffice.addSubscriber(subscriber);

        Set<Subscriber> actualSubscribers = postOffice.getSubscribers();

        assertThat(actualSubscribers, allOf(
                notNullValue(),
                contains(subscriber)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullSubscriberShouldThrowException() throws Exception {
        postOffice.addSubscriber(null);
    }

    @Test
    public void removeSubscriber() throws Exception {
        postOffice.addSubscriber(subscriber);
        postOffice.removeSubscriber(subscriber);

        Set<Subscriber> actualSubscribers = postOffice.getSubscribers();

        assertThat(actualSubscribers, allOf(
                notNullValue(),
                not(contains(subscriber))));
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeNullSubscriberShouldThrowException() throws Exception {
        postOffice.removeSubscriber(null);
    }

    @Test
    public void receivePress() throws Exception {
        Publisher publisher = JavaPublisher.getInstance();
        Press press = publisher.publishMagazine("hello");

        postOffice.receivePress(press);
        List<Press> actualPress = postOffice.getReceivedPressArchive();

        assertThat(actualPress, allOf(
                notNullValue(),
                contains(press)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void receiveNullPressShouldThrowException() throws Exception {
        postOffice.receivePress(null);
    }
}