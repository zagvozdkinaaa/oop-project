package academic;

import users.Student;

public class Mark {

    private String markId;
    private Student student;
    private Course course;

    private double firstAttestation;
    private double secondAttestation;
    private double finalExam;

    private double total;

   
    public Mark(String markId, Student student, Course course,
                double firstAttestation, double secondAttestation, double finalExam) {
        this.markId = markId;
        this.student = student;
        this.course = course;
        this.firstAttestation = firstAttestation;
        this.secondAttestation = secondAttestation;
        this.finalExam = finalExam;

        this.total = calculateTotal();
    }

   

    public double calculateTotal() {
        this.total = firstAttestation + secondAttestation + finalExam;
        return total;
    }

    public double getTotal() {
        return total;
    }

    public boolean isPassed() {
        return total >= 50;
    }

    public String getLetterGrade() {
    if (total > 100) total = 100;
    if (total < 0) total = 0;

    if (total >= 95) return "A";
    else if (total >= 90) return "A-";
    else if (total >= 85) return "B+";
    else if (total >= 80) return "B";
    else if (total >= 75) return "B-";
    else if (total >= 70) return "C+";
    else if (total >= 65) return "C";
    else if (total >= 60) return "C-";
    else if (total >= 55) return "D+";
    else if (total >= 50) return "D";
    else return "F";
}

  
    @Override
    public String toString() {
        return "Course: " + (course != null ? course.getName() : "N/A") +
                ", Total: " + total +
                ", Grade: " + getLetterGrade() +
                ", Passed: " + (isPassed() ? "Yes" : "No");
    }
}
