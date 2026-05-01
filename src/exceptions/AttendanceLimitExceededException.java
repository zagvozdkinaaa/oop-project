package exceptions;

import users.Student;

public class AttendanceLimitExceededException extends Exception {

    public AttendanceLimitExceededException(Student student) {
        super("Attendance limit exceeded for student: " + student.getName());
    }
}
