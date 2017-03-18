package org.vitaly.homework04.chainOfResponsobility;

import static org.vitaly.util.InputChecker.requirePositiveDouble;

/**
 * Created by vitaly on 2017-03-18.
 */
public class Payment {
    private double amount;
    private double commission;

    public Payment(double amount) {
        requirePositiveDouble(amount, "Amount");

        this.amount = amount;
        this.commission = 0.0;
    }

    public double getAmount() {
        return amount;
    }

    public double getCommissionedAmount() {
        return amount * (1 - commission);
    }

    public double getCommissionAmount() {
        return amount * commission;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        requirePositiveDouble(commission, "Commission");
        if (commission >= 1) {
            throw new IllegalArgumentException("Commission can't be greater than or equal to one!");
        }

        this.commission = commission;
    }
}
