package academic;

import enums.LessonType;
import users.Teacher;
import users.Student;
import java.util.*;

public class Lesson {

    private String lessonId;
    private Course course;
    private LessonType type;
    private Date date;
    private String room;
    private Teacher teacher;
    private int duration;

    private Map<Student, Boolean> attendanceList = new HashMap<>();

    public Lesson() {
    }

    public Lesson(String lessonId, Course course, LessonType type, Date date, String room, Teacher teacher, int duration) {
        this.lessonId = lessonId;
        this.course = course;
        this.type = type;
        this.date = date;
        this.room = room;
        this.teacher = teacher;
        this.duration = duration;
    }

    public String getLessonId() { return lessonId; }
    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
    public LessonType getType() { return type; }
    public Date getDate() { return date; }
    public String getRoom() { return room; }
    public Teacher getTeacher() { return teacher; }
    public int getDuration() { return duration; }

    public void setAttendance(Student student, boolean isPresent) {
        attendanceList.put(student, isPresent);
    }

    public boolean wasPresent(Student student) {
        return attendanceList.getOrDefault(student, false);
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "lessonId='" + lessonId + '\'' +
                ", course=" + (course != null ? course.getName() : "null") +
                ", type=" + type +
                ", room='" + room + '\'' +
                '}';
    }
}