package academic;

import enums.LessonType;
import users.Student;
import users.Teacher;

import java.util.*;

public class Lesson implements java.io.Serializable {
    private static final long serialVersionUID = 12L;

    private String lessonId;
    private Course course;
    private LessonType type;
    private Date date;
    private String room;
    private Teacher teacher;
    private int duration;

    // attendance
    private Map<Student, Boolean> attendance = new HashMap<>();

    public Lesson(String lessonId, Course course, LessonType type,
                  Date date, String room, Teacher teacher, int duration) {
        this.lessonId = lessonId;
        this.course = course;
        this.type = type;
        this.date = date;
        this.room = room;
        this.teacher = teacher;
        this.duration = duration;
    }

    public String getLessonId() {
        return lessonId;
    }

    public LessonType getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public String getRoom() {
        return room;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public int getDuration() {
        return duration;
    }

    public Course getCourse() {
        return course;
    }

    public boolean isLecture() {
        return type == LessonType.LECTURE;
    }

    // attendance
    public void markAttendance(Student student, boolean present) {
        attendance.put(student, present);
    }

    public long getAbsences(Student student) {
        return attendance.entrySet().stream()
                .filter(e -> e.getKey().equals(student) && !e.getValue())
                .count();
    }

    public Map<Student, Boolean> getAttendance() {
        return attendance;
    }

    @Override
    public String toString() {
        return "Lesson: " + type +
                ", course=" + (course != null ? course.getName() : "N/A") +
                ", teacher=" + (teacher != null ? teacher.getFullName() : "N/A") +
                ", room=" + room +
                ", date=" + date +
                ", duration=" + duration + " min";

    }
}
