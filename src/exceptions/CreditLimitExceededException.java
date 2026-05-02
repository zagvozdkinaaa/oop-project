package exceptions;

/**
 * 
 */
public class CreditLimitExceededException extends Exception {

    private int credits;

    public CreditLimitExceededException(int credits) {
        super("Credit limit exceeded: " + credits);
        this.credits = credits;
    }

    public int getCredits() {
        return credits;
    }

}