package academic;

import users.Student;
import users.Teacher;

import java.util.*;

public class Course {

    private String courseId;
    private String name;
    private String code;
    private int credits;

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

 
    public void addStudent(Student student) {
        if (!isOpenForRegistration) {
            System.out.println("Registration is closed for this course.");
            return;
        }

        if (!enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
        }
    }


    public void addTeacher(Teacher teacher) {
        if (!teachers.contains(teacher)) {
            teachers.add(teacher);
        }
    }

  
    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

  
    public boolean isOpenForRegistration() {
        return isOpenForRegistration;
    }

   
    public void setOpenForRegistration(boolean open) {
        this.isOpenForRegistration = open;
    }

  
    @Override
    public String toString() {
        return "Course: " + name +
                " (" + code + "), credits=" + credits +
                ", students=" + enrolledStudents.size();
    }
}
