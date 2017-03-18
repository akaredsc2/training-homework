package org.vitaly.homework04.chainOfResponsobility;

import static org.vitaly.util.InputChecker.*;

/**
 * Created by vitaly on 2017-03-18.
 */
public class CommissionHandler implements PaymentHandler {
    private PaymentHandler nextHandler;
    private double commissionPercent;

    public CommissionHandler(PaymentHandler nextHandler, double commissionPercent) {
        requireNonNull(nextHandler, NEXT_HANDLER);

        this.nextHandler = nextHandler;
        this.commissionPercent = commissionPercent;
    }

    @Override
    public String handle(Payment payment) {
        if (payment != null) {
            return commissionNonNullPayment(payment);
        } else {
            throw new IllegalArgumentException("Can't commission null payment");
        }
    }

    private String commissionNonNullPayment(Payment payment) {
        double leftoverPercent = 1 - commissionPercent;
        if (leftoverPercent < 0) {
            return "Commissioning failed due to leftover payment amount being less than zero";
        }
        if (leftoverPercent >= 1) {
            return "Commissioning failed due to leftover payment amount being greater than initial payment amount";
        }
        payment.setCommission(commissionPercent);
        return "Payment commissioned" + System.lineSeparator() + nextHandler.handle(payment);
    }
}
