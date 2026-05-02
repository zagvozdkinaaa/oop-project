package exceptions;

/**
 * Thrown when mark is out of valid range (0–100).
 */
public class InvalidMarkException extends Exception {

    private final double value;

    public InvalidMarkException(double value) {
        super("Invalid mark: " + value + ". Must be between 0 and 100.");
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
