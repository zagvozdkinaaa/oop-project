package academic;

import users.Student;
import java.util.*;

public class Transcript {

    private String transcriptId;
    private Student student;
    private List<Mark> marks;
    private double gpa;
    private Date generatedDate;

    public Transcript(String transcriptId, Student student) {
        this.transcriptId = transcriptId;
        this.student = student;
        this.marks = new ArrayList<>();
        this.generatedDate = new Date();
    }

   
    public void addMark(Mark mark) {
        marks.add(mark);
    }

    public double calculateGpa() {
        if (marks.isEmpty()) return 0.0;

        double sum = 0;
        for (Mark m : marks) {
            sum += m.getTotal();
        }

        this.gpa = sum / marks.size();
        return gpa;
    }

    public double getGpa() {
        return gpa;
    }

    public void generate(Student student) {
        this.student = student;
        this.generatedDate = new Date();
        calculateGpa();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Transcript for: ")
          .append(student != null ? student.getName() : "N/A")
          .append("\nDate: ").append(generatedDate)
          .append("\nGPA: ").append(gpa)
          .append("\nMarks:\n");

        for (Mark m : marks) {
            sb.append(m.toString()).append("\n");
        }

        return sb.toString();
    }
}
