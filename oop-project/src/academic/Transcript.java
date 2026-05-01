package academic;

import java.io.Serializable;
import java.util.*;

public class Transcript implements Serializable {
    private static final long serialVersionUID = 22L;

    private String transcriptId;
    private Date issueDate;
    private List<Course> courses = new ArrayList<>();
    private List<Mark> marks = new ArrayList<>();

    public Transcript() {
        this.transcriptId = "TR-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.issueDate = new Date();
    }

    public void addEntry(Course course, Mark mark) {
        courses.add(course);
        marks.add(mark);
    }

    @Override
    public String toString() {
        if (courses.isEmpty()) return "Transcript is empty";
        StringBuilder sb = new StringBuilder("\nTRANSCRIPT " + transcriptId + "\n");
        sb.append("Date of issue: ").append(issueDate.toString()).append("\n");
        sb.append("\n");
        for (int i = 0; i < courses.size(); i++) {
            sb.append(courses.get(i).getName()).append(": ").append(marks.get(i).toString()).append("\n");
        }
        return sb.toString();
    }
}