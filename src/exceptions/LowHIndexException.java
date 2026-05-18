package exceptions;

/**
 * 
 */
public class LowHIndexException extends Exception {

    /**
     * Default constructor
     */
    public LowHIndexException() {
        super();
    }

    /**
     * 
     */
    private int requiredHIndex;

    /**
     * 
     */
    private int actualHIndex;

    /**
     * @param actual 
     * @param required
     */
    public LowHIndexException(int actual, int required) {
        super("Low h-index: " + actual + " (required: " + required + ")");
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