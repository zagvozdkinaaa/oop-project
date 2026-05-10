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

    public Student(String id, String firstName, String lastName, String email, String password, String major, int year) {
        super(id, firstName, lastName, email, password, enums.UserRole.STUDENT);
        this.major = major;
        this.year = year;
        this.gpa = 0.0;
        this.credits = 0;
        this.courses = new ArrayList<>();
        this.marks = new ArrayList<>();
        this.transcript = new Transcript(id, this);
        this.researchPapers = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
        this.isResearcher = false;
        this.failCount = 0;
    }

    public void registerCourse(Course course) throws exceptions.CreditLimitExceededException, exceptions.CourseRegistrationClosedException, exceptions.AlreadyEnrolledException, exceptions.MaxStudentsExceededException {
        if (course == null) return;

        int totalCredits = this.credits + course.getCredits();
        if (totalCredits > 21) {
            throw new exceptions.CreditLimitExceededException(totalCredits);
        }

        if (this.courses == null) this.courses = new ArrayList<>();
        this.courses.add(course);
        this.credits = totalCredits;
        course.addStudent(this);
    }

    public void dropCourse(Course course) {
        if (this.courses != null) {
            this.courses.remove(course);
        }
    }
    public List<Course> viewCourses() {
        return courses;
    }

    public List<Student> viewStudents(Course c) {
        if (c != null) {
            return c.getEnrolledStudents();
        }
        return null;
    }

    public void rateTeacher(Teacher teacher, double rating) {
        if (teacher != null) {
            teacher.addRating(rating);
        }
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

    public void setSupervisor(Teacher teacher) {
        this.supervisor = (Researcher) teacher;
    }

    public Teacher getSupervisor() {
        return (Teacher) this.supervisor;
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
        if (this.researchPapers == null) this.researchPapers = new ArrayList<>();
        this.researchPapers.add(paper);
    }

    @Override
    public List<ResearchProject> getResearchProjects() {
        return researchProjects;
    }

    @Override
    public void addResearchProject(ResearchProject project) {
        if (this.researchProjects == null) this.researchProjects = new ArrayList<>();
        this.researchProjects.add(project);
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