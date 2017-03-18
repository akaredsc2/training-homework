package org.vitaly.homework04.chainOfResponsobility;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vitaly on 2017-03-18.
 */
public class PaymentHandlerFactoryTest {
    private PaymentHandlerFactory factory;
    private PaymentHandler handler;
    private double amount;
    private Payment payment;
    private double precision;

    @Before
    public void setUp() throws Exception {
        factory = PaymentHandlerFactory.getInstance();
        amount = 100;
        payment = new Payment(amount);
        precision = 0.000_001;
    }

    @Test
    public void regularPaymentHandlerChargesRegularCommission() throws Exception {
        handler = factory.createRegularPaymentHandler();

        handler.handle(payment);

        assertEquals(payment.getCommission(), PaymentHandlerFactory.REGULAR_COMMISSION, precision);
    }

    @Test
    public void createStatePaymentHandlerChargesStateCommission() throws Exception {
        handler = factory.createStatePaymentHandler();

        handler.handle(payment);

        assertEquals(payment.getCommission(), PaymentHandlerFactory.STATE_COMMISSION, precision);
    }

    @Test
    public void createPreferentialPaymentHandlerChargesPreferentialCommission() throws Exception {
        handler = factory.createPreferentialPaymentHandler();

        handler.handle(payment);

        assertEquals(payment.getCommission(), PaymentHandlerFactory.PREFERENTIAL_COMMISSION, precision);
    }

    @Test
    public void createInnerPaymentHandlerChargesNoCommission() throws Exception {
        handler = factory.createInnerPaymentHandler();

        handler.handle(payment);

        assertEquals(payment.getCommission(), 0, precision);
    }

}