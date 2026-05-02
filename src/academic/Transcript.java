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
        calculateGpa();
    }

    public double calculateGpa() {
        if (marks.isEmpty()) {
            this.gpa = 0.0;
            return gpa;
        }

        double sum = 0;
        for (Mark m : marks) {
            sum += m.getTotal();
        }

        this.gpa = sum / marks.size();
        return gpa;
    }

    public double getGpa() {
        return calculateGpa();
    }

    public void generate(Student student) {
        this.student = student;
        this.generatedDate = new Date();
        calculateGpa();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        String formattedDate = new java.text.SimpleDateFormat("dd-MM-yyyy")
                .format(generatedDate);

        sb.append("Transcript for: ")
          .append(student != null ? student.getName() : "N/A")
          .append("\nDate: ").append(formattedDate)
          .append("\nGPA: ").append(String.format("%.2f", gpa))
          .append("\nMarks:\n");

        for (Mark m : marks) {
            sb.append(m.toString()).append("\n");
        }

        return sb.toString();
    }
}
