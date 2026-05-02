package exceptions;

import users.Student;

public class FailLimitExceededException extends Exception {

    private static final int MAX_FAILS = 3;

    private final Student student;
    private final int failedCourses;

    public FailLimitExceededException() {
        super("Fail limit exceeded. Maximum allowed failed courses: " + MAX_FAILS + ".");
        this.student = null;
        this.failedCourses = MAX_FAILS;
    }

    public FailLimitExceededException(Student student, int failedCourses) {
        super(buildMessage(student, failedCourses));
        this.student = student;
        this.failedCourses = failedCourses;
    }

    public Student getStudent() {
        return student;
    }

    public int getFailedCourses() {
        return failedCourses;
    }

    public int getMaxFails() {
        return MAX_FAILS;
    }

    private static String buildMessage(Student student, int failedCourses) {
        String name = (student != null) ? student.getName() : "Unknown student";

        return "Fail limit exceeded: " + name +
               " has " + failedCourses +
               " failed courses (max " + MAX_FAILS + ").";
    }
}
