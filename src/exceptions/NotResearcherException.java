package exceptions;

/**
 * Exception thrown when a non-researcher tries to join a research project
 */
public class NotResearcherException extends Exception {

    public NotResearcherException() {
        super("User is not a researcher");
    }

    public NotResearcherException(String message) {
        super(message);
    }

    public NotResearcherException(String message, Throwable cause) {
        super(message, cause);
    }

}