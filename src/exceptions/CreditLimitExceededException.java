package exceptions;

public class CreditLimitExceededException extends Exception {

    private static final int MAX_CREDITS = 21;

    private final int attemptedCredits;

    public CreditLimitExceededException() {
        super("Credit limit exceeded. Maximum allowed is " + MAX_CREDITS + " credits.");
        this.attemptedCredits = MAX_CREDITS;
    }

    public CreditLimitExceededException(int attemptedCredits) {
        super(buildMessage(attemptedCredits));
        this.attemptedCredits = attemptedCredits;
    }

    public int getAttemptedCredits() {
        return attemptedCredits;
    }

    public int getMaxCredits() {
        return MAX_CREDITS;
    }

    private static String buildMessage(int attemptedCredits) {
        return "Credit limit exceeded: attempted " + attemptedCredits +
               " credits, but maximum allowed is " + MAX_CREDITS + ".";
    }
}
