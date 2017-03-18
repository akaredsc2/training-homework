package org.vitaly.homework04.chainOfResponsobility;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by vitaly on 2017-03-18.
 */
public class VerificationHandlerTest {
    private PaymentHandler handler;
    private Payment payment;
    private String expectedSuccessMessage;
    private String expectedFailMessage;
    private int amount;

    @Before
    public void setUp() throws Exception {
        PaymentHandler commitHandler = new CommitHandler();
        handler = new VerificationHandler(commitHandler);
        amount = 10;
        payment = new Payment(amount);
        expectedSuccessMessage = "verified";
        expectedFailMessage = "failed";
    }

    @Test
    public void verifyingPaymentShowsInHandleMessage() throws Exception {
        String actual = handler.handle(payment);

        assertThat(actual, containsString(expectedSuccessMessage));
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingVerificationHandlerWithNullNextHandlerShouldThrowException() throws Exception {
        new VerificationHandler(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifyingNullPaymentShouldThrowException() throws Exception {
        handler.handle(null);
    }

    @Test
    public void verifyingPaymentWithNegativeAmountFailsInHandleMessage() throws Exception {
        Field field = payment.getClass().getDeclaredField("amount");
        field.setAccessible(true);
        field.set(payment, -100);

        String actual = handler.handle(payment);

        assertThat(actual, containsString(expectedFailMessage));
    }
}