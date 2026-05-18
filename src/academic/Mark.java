package academic;

import users.Student;
import exceptions.AttendanceLimitExceededException;
import exceptions.InvalidMarkException;

public class Mark {

    private String markId;
    private Student student;
    private Course course;
    private double firstAttestation;
    private double secondAttestation;
    private double finalExam;
    private double total;
    private boolean retakeRequired;

    
    public Mark(String markId, Student student, Course course,
            double firstAttestation, double secondAttestation, double finalExam)
            throws InvalidMarkException {

        validate(firstAttestation);
        validate(secondAttestation);
        validate(finalExam);

        this.markId = markId;
        this.student = student;
        this.course = course;
        this.firstAttestation = firstAttestation;
        this.secondAttestation = secondAttestation;
        this.finalExam = finalExam;

        this.retakeRequired = false;
        this.total = calculateTotal();
    }

    
    private void validate(double value) throws InvalidMarkException {
        if (value < 0 || value > 100) {
            throw new InvalidMarkException(value);
        }
    }

    public void checkRetake(double attendancePercent)
        throws AttendanceLimitExceededException {

    if (attendancePercent < 70) {
        retakeRequired = true;
        finalExam = 0;
        calculateTotal();

        throw new AttendanceLimitExceededException(student);
    }
}

    public boolean isRetakeRequired() {
        return retakeRequired;
    }

    public double calculateTotal() {
        total = firstAttestation + secondAttestation + finalExam;
        return total;
    }

    public double getTotal() {
        return total;
    }

    public boolean isPassed() {
        return total >= 50 && !retakeRequired;
    }

    public String getLetterGrade() {

        if (retakeRequired)
            return "F";

        
        double t = Math.max(0, Math.min(100, total)); 

        if (t >= 95)
            return "A";
        else if (t >= 90)
            return "A-";
        else if (t >= 85)
            return "B+";
        else if (t >= 80)
            return "B";
        else if (t >= 75)
            return "B-";
        else if (t >= 70)
            return "C+";
        else if (t >= 65)
            return "C";
        else if (t >= 60)
            return "C-";
        else if (t >= 55)
            return "D+";
        else if (t >= 50)
            return "D";
        else
            return "F";
    }

    @Override
    public String toString() {
        return "Course: " + (course != null ? course.getName() : "N/A") +
                ", Total: " + total +
                ", Grade: " + getLetterGrade() +
                ", Passed: " + (isPassed() ? "Yes" : "No") +
                ", Retake: " + (retakeRequired ? "Required" : "No");
    }
}
