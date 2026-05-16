package exceptions;

import users.Student;

/**
 * Thrown when student is already enrolled in a course.
 */
public class AlreadyEnrolledException extends Exception {

    private final Student student;

    public AlreadyEnrolledException(Student student) {
        super("Student " + student.getFullName() + " is already enrolled.");
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }
}
