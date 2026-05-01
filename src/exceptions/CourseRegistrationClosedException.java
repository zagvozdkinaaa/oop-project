package exceptions;

/**
 * Thrown when trying to register for a closed course.
 */
public class CourseRegistrationClosedException extends Exception {

    private final String courseName;

    public CourseRegistrationClosedException(String courseName) {
        super("Registration is closed for course: " + courseName);
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }
}
