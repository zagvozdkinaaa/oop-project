package exceptions;


public class LowHIndexException extends Exception {
    private int actualHIndex;
    private int requiredHIndex;

    public LowHIndexException(int actual, int required) {
        super("Low H-Index: " + actual + " Required at least: " + required);
        this.actualHIndex = actual;
        this.requiredHIndex = required;
    }

    public int getActualHIndex() { return actualHIndex; }
    public int getRequiredHIndex() { return requiredHIndex; }
}