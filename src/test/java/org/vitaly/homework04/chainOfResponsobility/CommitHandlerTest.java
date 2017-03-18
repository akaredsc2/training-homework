package org.vitaly.homework04.chainOfResponsobility;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by vitaly on 2017-03-18.
 */
public class CommitHandlerTest {
    private PaymentHandler handler;
    private Payment payment;
    private String expectedSuccessMessage;
    private int amount;

    @Before
    public void setUp() throws Exception {
        handler = new CommitHandler();
        amount = 40;
        payment = new Payment(amount);
        expectedSuccessMessage = "committed";
    }

    @Test
    public void committingPaymentShowsInHandleMessage() throws Exception {
        String actual = handler.handle(payment);

        assertThat(actual, containsString(expectedSuccessMessage));
    }

    @Test(expected = IllegalArgumentException.class)
    public void committingNullPaymentShouldThrowException() throws Exception {
        handler.handle(null);
    }
}