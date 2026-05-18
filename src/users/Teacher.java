package users;

import academic.Course;
import academic.Mark;
import core.Employee;
import enums.TeacherPosition;
import enums.UserRole;
import research.ResearchPaper;
import research.ResearchProject;
import research.Researcher;
import java.util.*;

public class Teacher extends Employee implements Researcher {
    private static final long serialVersionUID = 10L;

    private TeacherPosition position;
    private List<Course> courses;
    private double rating;
    private List<Double> ratingHistory;
    private int hIndex;
    private List<ResearchPaper> researchPapers;
    private List<ResearchProject> researchProjects;
    private boolean isResearcher;

    public Teacher(String id, String firstName, String lastName, String email, String password, double salary, TeacherPosition position) {
        super(id, firstName, lastName, email, password, enums.UserRole.TEACHER, salary);
        this.position = position;
        this.rating = 0.0;
        this.ratingHistory = new ArrayList<>();
        this.hIndex = 0;
        this.courses = new ArrayList<>();
        this.researchPapers = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
        this.isResearcher = position == TeacherPosition.PROFESSOR || position == TeacherPosition.ASSOCIATE_PROFESSOR;
    }


    public void putMark(Student student, Course course, Mark mark) {
        if (student != null && student.viewTranscript() != null) {
            student.viewTranscript().addMark(mark);
        }
    }

    public void addCourse(Course course) {
        if (this.courses == null) this.courses = new ArrayList<>();
        this.courses.add(course);
    }

    public List<Student> viewStudents(Course course) {
        if (course != null) {
            return course.getEnrolledStudents();
        }
        return null;
    }

    public String generateMarkReport(Course course) {
        return "Mark report for " + (course != null ? course.getName() : "unknown");
    }

    public void addRating(double r) {
        if (r >= 0 && r <= 5) {
            this.ratingHistory.add(r);
            double sum = 0;
            for (double rating : ratingHistory) {
                sum += rating;
            }
            this.rating = sum / ratingHistory.size();
        }
    }

    public TeacherPosition getPosition() {
        return position;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public double getRating() {
        return rating;
    }

    public List<Double> getRatingHistory() {
        return ratingHistory;
    }

    public int getRatingCount() {
        return ratingHistory.size();
    }

    private void updateHIndex() {
        this.hIndex = researchPapers.size();
    }

    @Override
    public int getHIndex() {
        return hIndex;
    }

    @Override
    public List<ResearchPaper> getResearchPapers() {
        return researchPapers;
    }

    @Override
    public void addResearchPaper(ResearchPaper paper) {
        if (this.researchPapers == null) this.researchPapers = new ArrayList<>();
        this.researchPapers.add(paper);
        updateHIndex();
        this.isResearcher = true;
    }

    @Override
    public List<ResearchProject> getResearchProjects() {
        return researchProjects;
    }

    @Override
    public void addResearchProject(ResearchProject project) {
        if (this.researchProjects == null) this.researchProjects = new ArrayList<>();
        this.researchProjects.add(project);
        this.isResearcher = true;
    }

    @Override
    public void printPapers(Comparator<ResearchPaper> c) {
        if (this.researchPapers != null) {
            this.researchPapers.sort(c);
            for (ResearchPaper p : this.researchPapers) {
                System.out.println(p);
            }
        }
    }

    public boolean isResearcher() {
        return isResearcher;
    }

    @Override
    public String toString() {
        return String.format("%s | Position: %s | Rating: %.1f",
                super.toString(), position, rating);
    }
    public void removeCourse(Course course) {
        if (this.courses != null) {
            this.courses.remove(course);
        }

        if (course != null) {
            course.getTeachers().remove(this);
        }
    }
}
