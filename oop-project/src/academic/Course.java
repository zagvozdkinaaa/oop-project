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
    private List<Lesson> lessons = new ArrayList<>();
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

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
        lesson.setCourse(this);
    }

    public List<Lesson> getLessons() { return lessons; }
    public List<Student> getEnrolledStudents() { return enrolledStudents; }

    public int getAttendedLessonsForStudent(Student student) {
        int attended = 0;
        for (Lesson lesson : lessons) {
            if (lesson.wasPresent(student)) {
                attended++;
            }
        }
        return attended;
    }
    
    public void finalizeCourse() {
        for (Student student : enrolledStudents) {
            Mark studentMark = student.viewMarks().stream()
                    .filter(m -> m.getCourse().equals(this))
                    .findFirst().orElse(null);
            if (studentMark != null && studentMark.isFailed()) {
                System.out.println("Студент " + student.getFullName() + " отправлен на Retake по курсу " + this.getName() + " (превышен лимит пропусков или завален финал).");
            }
        }
        this.setOpen(false); 
    }

    @Override
    public String toString() {
        return String.format("Course[%s: %s, Credits: %d]", courseId, name, credits);
    }
}