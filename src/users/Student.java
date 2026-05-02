package users;

import academic.Course;
import academic.Mark;
import academic.Transcript;
import core.User;
import enums.UserRole;
import research.ResearchPaper;
import research.ResearchProject;
import research.Researcher;
import java.util.*;

public class Student extends User implements Researcher, Comparable<Student> {
    private static final long serialVersionUID = 9L;

    private double gpa;
    private int year;
    private String major;
    private int credits;
    private List<Course> courses;
    private List<Mark> marks;
    private Transcript transcript;
    private Researcher supervisor;
    private int failCount;
    private List<ResearchPaper> researchPapers;
    private List<ResearchProject> researchProjects;
    private boolean isResearcher;

    public Student(String id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email, password, UserRole.STUDENT);
        this.courses = new ArrayList<>();
        this.marks = new ArrayList<>();
        this.researchPapers = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
        this.transcript = new Transcript();
        this.isResearcher = false;
        this.gpa = 0.0;
    }
    public void registerCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
        }
    }

    public void dropCourse(Course course) {
        courses.remove(course);
    }

    public List<Course> viewCourses() {
        return courses;
    }

    public List<Mark> viewMarks() {
        return marks;
    }

    public Transcript viewTranscript() {
        return transcript;
    }

    public double getGpa() {
        return gpa;
    }

    public int getCredits() {
        return credits;
    }

    public void setSupervisor(Researcher supervisor) {
        this.supervisor = supervisor;
        this.isResearcher = true;
    }

    public Researcher getSupervisor() {
        return supervisor;
    }

    @Override
    public int getHIndex() {
        return researchPapers.size();
    }

    @Override
    public List<ResearchPaper> getResearchPapers() {
        return researchPapers;
    }

    @Override
    public void addResearchPaper(ResearchPaper paper) {
        this.researchPapers.add(paper);
        this.isResearcher = true;
    }

    @Override
    public List<ResearchProject> getResearchProjects() {
        return researchProjects;
    }

    @Override
    public void addResearchProject(ResearchProject project) {
        this.researchProjects.add(project);
    }

    @Override
    public void printPapers(Comparator<ResearchPaper> c) {
        researchPapers.sort(c);
        researchPapers.forEach(System.out::println);
    }

    @Override
    public int compareTo(Student other) {
        return Double.compare(other.getGpa(), this.getGpa());
    }

    @Override
    public String toString() {
        return String.format("%s | Major: %s | GPA: %.2f | Year: %d",
                super.toString(), major, gpa, year);
    }
}