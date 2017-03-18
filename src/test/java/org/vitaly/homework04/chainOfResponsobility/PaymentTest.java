package org.vitaly.homework04.chainOfResponsobility;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by vitaly on 2017-03-18.
 */
public class PaymentTest {
    private double precision;
    private double amount;
    private double commission;
    private Payment payment;

    @Before
    public void setUp() throws Exception {
        precision = 0.000_001;
        amount = 100;
        commission = 0.1;
        payment = new Payment(amount);
    }

    @Test
    public void newPaymentCommissionIsEqualToZero() throws Exception {
        double actualCommission = payment.getCommission();

        int expectedCommission = 0;
        assertEquals(expectedCommission, actualCommission, precision);
    }

    @Test
    public void paymentAmountIsEqualToSumOfCommissionedAmountAndCommissionAmountWithZeroCommission() throws Exception {
        double actualAmount = payment.getCommissionedAmount() + payment.getCommissionAmount();

        double expectedAmount = payment.getAmount();
        assertEquals(expectedAmount, actualAmount, precision);
    }

    @Test
    public void paymentAmountEqualsToCommissionedAmountAndCommissionAmountSumWithNonZeroCommission() throws Exception {
        payment.setCommission(commission);

        double actualAmount = payment.getCommissionedAmount() + payment.getCommissionAmount();

        double expectedAmount = payment.getAmount();
        assertEquals(expectedAmount, actualAmount, precision);
    }

    @Test
    public void commissionedAmountIsLessThanAmountWithCommissionGreaterThanZero() throws Exception {
        payment.setCommission(commission);

        assertTrue(payment.getCommissionedAmount() < payment.getAmount());
    }

    @Test
    public void commissionAmountIsLessThanAmountWithCommissionGreaterThanZero() throws Exception {
        payment.setCommission(commission);

        assertTrue(payment.getCommissionAmount() < payment.getAmount());
    }

    @Test
    public void settingCommissionUpdatesPaymentCommission() throws Exception {
        payment.setCommission(commission);

        double expectedCommission = payment.getCommission();
        assertEquals(commission, expectedCommission, precision);
    }

    @Test(expected = IllegalArgumentException.class)
    public void settingCommissionToLessThanZeroShouldThrowException() throws Exception {
        payment.setCommission(-2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void settingCommissionToGreaterThanOneShouldThrowException() throws Exception {
        payment.setCommission(2);
    }
}