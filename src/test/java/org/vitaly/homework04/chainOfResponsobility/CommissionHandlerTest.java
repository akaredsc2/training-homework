package org.vitaly.homework04.chainOfResponsobility;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.stringContainsInOrder;

/**
 * Created by vitaly on 2017-03-18.
 */
public class CommissionHandlerTest {
    private PaymentHandler handler;
    private Payment payment;
    private String expectedSuccessMessage;
    private String expectedFailMessage;
    private double commissionPercent;
    private int amount;

    @Before
    public void setUp() throws Exception {
        PaymentHandler commitHandler = new CommitHandler();
        commissionPercent = 0.04;
        handler = new CommissionHandler(commitHandler, commissionPercent);
        amount = 10;
        payment = new Payment(amount);
        expectedSuccessMessage = "commissioned";
        expectedFailMessage = "failed";
    }

    @Test
    public void commissioningPaymentShowsInHandleMessage() throws Exception {
        String actual = handler.handle(payment);

        assertThat(actual, containsString(expectedSuccessMessage));
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingCommissionHandlerWithNullNextHandlerShouldThrowException() throws Exception {
        new VerificationHandler(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void commissioningNullPaymentShouldThrowException() throws Exception {
        handler.handle(null);
    }

    @Test
    public void commissioningPaymentWithNegativeCommissionPercentFailsInHandleMessage() throws Exception {
        Field field = handler.getClass().getDeclaredField("commissionPercent");
        field.setAccessible(true);
        field.set(handler, -2);

        String actual = handler.handle(payment);

        List<String> expectedSubstrings = Arrays.asList(expectedFailMessage, "greater than initial payment amount");
        assertThat(actual, stringContainsInOrder(expectedSubstrings));
    }

    @Test
    public void commissioningPaymentWithCommissionPercentGreaterThanOneFailsInHandleMessage() throws Exception {
        Field field = handler.getClass().getDeclaredField("commissionPercent");
        field.setAccessible(true);
        field.set(handler, 2);

        String actual = handler.handle(payment);

        List<String> expectedSubstrings = Arrays.asList(expectedFailMessage, "less than zero");
        assertThat(actual, stringContainsInOrder(expectedSubstrings));
    }
}