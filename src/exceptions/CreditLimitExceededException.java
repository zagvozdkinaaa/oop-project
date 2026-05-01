package exceptions;

/**
 * 
 */
public class CreditLimitExceededException {

    /**
     * Default constructor
     */
    public CreditLimitExceededException() {
    }

    /**
     * @param credits
     */
    public CreditLimitExceededException(int credits) {
        this.credits = credits;
    }

    private int credits;

    /**
     * @return
     */
    public String getMessage() {
        return "Credit limit exceeded: " + credits;
    }

}