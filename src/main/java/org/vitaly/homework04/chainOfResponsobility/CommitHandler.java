package org.vitaly.homework04.chainOfResponsobility;

/**
 * Created by vitaly on 2017-03-18.
 */
public class CommitHandler implements PaymentHandler {
    @Override
    public String handle(Payment payment) {
        if (payment != null) {
            double totalAmount = payment.getAmount();
            double commissionedAmount = payment.getCommissionedAmount();
            double commissionPercent = payment.getCommission() * 100;
            double commissionAmount = payment.getCommissionAmount();
            StringBuilder builder = new StringBuilder()
                    .append("Payment committed").append(System.lineSeparator())
                    .append("Payment amount: ").append(totalAmount).append(System.lineSeparator())
                    .append("Amount without commission: ").append(commissionedAmount).append(System.lineSeparator())
                    .append("Commission percent: ").append(commissionPercent).append(System.lineSeparator())
                    .append("Commission amount: ").append(commissionAmount).append(System.lineSeparator());
            return builder.toString();
        } else {
            throw new IllegalArgumentException("Can't commit null payment");
        }
    }
}
