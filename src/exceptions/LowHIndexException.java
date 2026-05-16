package exceptions;

/**
 * Exception thrown when a researcher's h-index is too low for a role
 */
public class LowHIndexException extends Exception {

    private int requiredHIndex;
    private int actualHIndex;

    public LowHIndexException() {
        super("H-index is too low for this role");
    }

    public LowHIndexException(int actual, int required) {
        super("H-index " + actual + " is below required " + required);
        this.actualHIndex = actual;
        this.requiredHIndex = required;
    }

    public int getRequiredHIndex() {
        return requiredHIndex;
    }

    public int getActualHIndex() {
        return actualHIndex;
    }
}