package exceptions;

public class CreditLimitExceededException extends Exception {
    public CreditLimitExceededException(int totalCredits) {
        super("Credit limit exceeded! Attempted total credits: " + totalCredits);
    }
}