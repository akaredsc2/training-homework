package org.vitaly.week05.observer;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by vitaly on 2017-03-02.
 */
public class JuniorJavaDeveloperTest {
    private Subscriber subscriber;

    @Before
    public void setUp() throws Exception {
        subscriber = new JuniorJavaDeveloper();
    }

    @Test
    public void informAddsOnlyJavaPressOfAnyQuality() throws Exception {
        Publisher javaPublisher = JavaPublisher.getInstance();
        Press javaNewspaper = javaPublisher.publishNewspaper("java1");
        Press javaMagazine = javaPublisher.publishMagazine("java2");
        Publisher gardenPublisher = GardeningPublisher.getInstance();
        Press gardenNewspaper = gardenPublisher.publishNewspaper("garden1");
        Press gardenMagazine = gardenPublisher.publishMagazine("garden2");

        subscriber.inform(javaNewspaper);
        subscriber.inform(javaMagazine);
        subscriber.inform(gardenNewspaper);
        subscriber.inform(gardenMagazine);
        Collection<Press> actualPressCollection = subscriber.getPressCollection();

        assertThat(actualPressCollection, allOf(
                notNullValue(),
                containsInAnyOrder(javaMagazine, javaNewspaper),
                not(contains(gardenNewspaper)),
                not(contains(gardenMagazine))
        ));
    }

    @Test(expected = IllegalArgumentException.class)
    public void informNullPressShouldThrowException() throws Exception {
        subscriber.inform(null);
    }
}