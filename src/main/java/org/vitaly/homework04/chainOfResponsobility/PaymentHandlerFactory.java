package org.vitaly.homework04.chainOfResponsobility;

/**
 * Created by vitaly on 2017-03-18.
 */
public class PaymentHandlerFactory {
    public static final double REGULAR_COMMISSION = 0.03;
    public static final double STATE_COMMISSION = 0.02;
    public static final double PREFERENTIAL_COMMISSION = 0.015;

    private static PaymentHandlerFactory instance = new PaymentHandlerFactory();

    private PaymentHandlerFactory() {
    }

    public static PaymentHandlerFactory getInstance() {
        return instance;
    }

    public PaymentHandler createRegularPaymentHandler() {
        PaymentHandler verificationHandler = prepareOuterHandler(REGULAR_COMMISSION);
        return new InitiationHandler(verificationHandler);
    }

    public PaymentHandler createStatePaymentHandler() {
        PaymentHandler verificationHandler = prepareOuterHandler(STATE_COMMISSION);
        return new InitiationHandler(verificationHandler);
    }

    public PaymentHandler createPreferentialPaymentHandler() {
        PaymentHandler verificationHandler = prepareOuterHandler(PREFERENTIAL_COMMISSION);
        return new InitiationHandler(verificationHandler);
    }

    private PaymentHandler prepareOuterHandler(double commission) {
        PaymentHandler commitHandler = new CommitHandler();
        PaymentHandler commissionHandler = new CommissionHandler(commitHandler, commission);
        return new VerificationHandler(commissionHandler);
    }

    public PaymentHandler createInnerPaymentHandler() {
        PaymentHandler commitHandler = new CommitHandler();
        PaymentHandler verificationHandler = new VerificationHandler(commitHandler);
        return new InitiationHandler(verificationHandler);
    }
}
