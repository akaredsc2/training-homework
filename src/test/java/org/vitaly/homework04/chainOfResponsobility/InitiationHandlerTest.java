package org.vitaly.homework04.chainOfResponsobility;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by vitaly on 2017-03-18.
 */
public class InitiationHandlerTest {
    private PaymentHandler handler;
    private Payment payment;
    private String expectedSuccessMessage;
    private int amount;

    @Before
    public void setUp() throws Exception {
        PaymentHandler commitHandler = new CommitHandler();
        handler = new InitiationHandler(commitHandler);
        amount = 10;
        payment = new Payment(amount);
        expectedSuccessMessage = "initialized";
    }

    @Test
    public void initializingPaymentShowsInHandleMessage() throws Exception {
        String actual = handler.handle(payment);

        assertThat(actual, containsString(expectedSuccessMessage));
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingInitializationHandlerWithNullNextHandlerShouldThrowException() throws Exception {
        new InitiationHandler(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void handlingNullPaymentShouldThrowException() throws Exception {
        handler.handle(null);
    }
}