package academic;

import java.io.Serializable;
import java.util.*;
import users.Teacher;
import users.Student;

public class Course implements Serializable {
    private static final long serialVersionUID = 20L;

    private String courseId;
    private String name;
    private int credits;
    private List<Teacher> teachers = new ArrayList<>();
    private List<Student> enrolledStudents = new ArrayList<>();
    private boolean isOpen = true; 

    public Course(String courseId, String name, int credits) {
        this.courseId = courseId;
        this.name = name;
        this.credits = credits;
    }

    public int getCredits() { return credits; }
    public String getName() { return name; }
    public String getCourseId() { return courseId; }
    public boolean isOpen() { return isOpen; }
    public void setOpen(boolean open) { this.isOpen = open; }

    public void addTeacher(Teacher teacher) { teachers.add(teacher); }
    public void addStudent(Student student) { enrolledStudents.add(student); }

    @Override
    public String toString() {
        return String.format("Course[%s: %s, Credits: %d]", courseId, name, credits);
    }
}