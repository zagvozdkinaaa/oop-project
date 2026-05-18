package academic;

import users.Student;
import users.Teacher;
import exceptions.*;

import java.io.Serializable;
import java.util.*;

public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    private String courseId;
    private String name;
    private String code;
    private int credits;

    private int maxStudents = 30;

    private List<Teacher> teachers;
    private List<Student> enrolledStudents;
    private List<Lesson> lessons;

    private boolean isOpenForRegistration;

    public Course(String courseId, String name, String code, int credits) {
        this.courseId = courseId;
        this.name = name;
        this.code = code;
        this.credits = credits;

        this.teachers = new ArrayList<>();
        this.enrolledStudents = new ArrayList<>();
        this.lessons = new ArrayList<>();
        this.isOpenForRegistration = true;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCode() {
        return code;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public String getName() {
        return name;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public int getCredits() {
        return credits;
    }

    // registration
    public void addStudent(Student student)
            throws CourseRegistrationClosedException,
            AlreadyEnrolledException,
            MaxStudentsExceededException {

        if (!isOpenForRegistration) {
            throw new RuntimeException("Course closed");
        }

        if (enrolledStudents.contains(student)) {
            return; 
        }

        if (enrolledStudents.size() >= maxStudents) {
            isOpenForRegistration = false;
            throw new RuntimeException("Course is full");
        }

        enrolledStudents.add(student);
    }

    public void addTeacher(Teacher teacher) {
        if (!teachers.contains(teacher)) {
            teachers.add(teacher);
        }
    }

    public void addLesson(Lesson lesson) {
        for (Lesson l : lessons) {
            if (l.getDate().equals(lesson.getDate()) &&
                    l.getRoom().equals(lesson.getRoom())) {

                System.out.println("Schedule conflict detected");
                return;
            }
        }

        lessons.add(lesson);
    }

    public boolean isOpenForRegistration() {
        return isOpenForRegistration;
    }

    public void setOpenForRegistration(boolean open) {
        this.isOpenForRegistration = open;
    }

    public int getStudentCount() {
        return enrolledStudents.size();
    }

    public double getAttendancePercentage(Student student) {
        if (lessons == null || lessons.isEmpty()) {
            return 100.0;
        }
        long totalLessons = 0;
        long presentLessons = 0;
        for (Lesson lesson : lessons) {
            if (lesson.getAttendance() != null && lesson.getAttendance().containsKey(student)) {
                totalLessons++;
                if (lesson.getAttendance().get(student)) {
                    presentLessons++;
                }
            }
        }
        if (totalLessons == 0) {
            return 100.0;
        }
        return ((double) presentLessons / totalLessons) * 100.0;
    }

    @Override
    public String toString() {
        return "Course: " + name +
                " (" + code + "), credits=" + credits +
                ", students=" + enrolledStudents.size();
    }
}
