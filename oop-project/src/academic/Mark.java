package academic;

import java.io.Serializable;

public class Mark implements Serializable {
    private static final long serialVersionUID = 21L;

    private Course course;
    private double firstAttestation;
    private double secondAttestation;
    private double finalExam;
    private int attendedLessons;
    private int totalLessons;

    public Mark(Course course, double first, double second, double finalEx, int attended, int total) {
        this.course = course;
        this.firstAttestation = first;
        this.secondAttestation = second;
        this.finalExam = finalEx;
        this.attendedLessons = attended;
        this.totalLessons = total;
    }

    public double getTotal() {
        return firstAttestation + secondAttestation + finalExam;
    }

    public void setAttendedLessons(int attendedLessons) {
        this.attendedLessons = attendedLessons;
    }

    public void setTotalLessons(int totalLessons) {
        this.totalLessons = totalLessons;
    }

    public boolean isFailed() {
        if (totalLessons == 0) {
            return finalExam < 20;
        }
        double attendanceRate = (double) attendedLessons / totalLessons;
        // Завалил, если финал < 20 или посещаемость < 70% (пропустил > 30%)
        return finalExam < 20 || attendanceRate < 0.7; 
    }

    public Course getCourse() { return course; }

    @Override
    public String toString() {
        double attendanceRate = ((double) attendedLessons / totalLessons) * 100;
        return String.format("Mark for %s [Total: %.1f, Final: %.1f, Attendance: %.1f%%]", 
            course.getName(), getTotal(), finalExam, attendanceRate);
    }
}