package org.vitaly.homework04.chainOfResponsobility;

import static org.vitaly.util.InputChecker.NEXT_HANDLER;
import static org.vitaly.util.InputChecker.requireNonNull;

/**
 * Created by vitaly on 2017-03-18.
 */
public class InitiationHandler implements PaymentHandler {
    private PaymentHandler nextHandler;

    public InitiationHandler(PaymentHandler nextHandler) {
        requireNonNull(nextHandler, NEXT_HANDLER);

        this.nextHandler = nextHandler;
    }

    @Override
    public String handle(Payment payment) {
        if (payment != null) {
            return "Payment initialized" + System.lineSeparator() + nextHandler.handle(payment);
        } else {
            throw new IllegalArgumentException("Can't initialize null payment!");
        }
    }
}
