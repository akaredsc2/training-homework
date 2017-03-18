package org.vitaly.homework04.chainOfResponsobility;

import static org.vitaly.util.InputChecker.NEXT_HANDLER;
import static org.vitaly.util.InputChecker.requireNonNull;

/**
 * Created by vitaly on 2017-03-18.
 */
public class VerificationHandler implements PaymentHandler {
    private PaymentHandler nextHandler;

    public VerificationHandler(PaymentHandler nextHandler) {
        requireNonNull(nextHandler, NEXT_HANDLER);

        this.nextHandler = nextHandler;
    }

    @Override
    public String handle(Payment payment) {
        if (payment != null) {
            return verifyNonNullPayment(payment);
        } else {
            throw new IllegalArgumentException("Can't verify null payment!");
        }
    }

    private String verifyNonNullPayment(Payment payment) {
        if (payment.getAmount() > 0) {
            return "Payment verified" + System.lineSeparator() + nextHandler.handle(payment);
        } else {
            return "Verification failed";
        }
    }
}
