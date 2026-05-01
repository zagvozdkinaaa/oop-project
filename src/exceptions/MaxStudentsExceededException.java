package exceptions;

/**
 * Thrown when course exceeds maximum number of students.
 */
public class MaxStudentsExceededException extends Exception {

    private final int max;

    public MaxStudentsExceededException(int max) {
        super("Maximum number of students exceeded. Limit is " + max);
        this.max = max;
    }

    public int getMax() {
        return max;
    }
}
