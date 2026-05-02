package users;

import core.User;
import research.Researcher;
import research.ResearchPaper;
import research.ResearchProject;
import academic.Course;
import academic.Mark;
import academic.Transcript;

import java.util.*;

/**
 * 
 */
public class Student extends User implements Researcher, Comparable<Student> {

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
        this.transcript = new academic.Transcript();
        this.researchPapers = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
        this.isResearcher = false;
        this.failCount = 0;
    }

    public void registerCourse(Course course) throws exceptions.CreditLimitExceededException {
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

    /**
     * @param course 
     * @return
     */
    public void dropCourse(Course course) {
        if (this.courses != null) {
            this.courses.remove(course);
        }
    }

    /**
     * @return
     */
    public List<Course> viewCourses() {
        return this.courses;
    }

    /**
     * @param Course c 
     * @return
     */
    public List<Student> viewStudents(Course c) {
        if (c != null) {
            return c.getEnrolledStudents();
        }
        return null;
    }

    /**
     * @return
     */
    public List<Mark> viewMarks() {
        return this.marks;
    }

    /**
     * @param teacher 
     * @param rating 
     * @return
     */
    public void rateTeacher(Teacher teacher, double rating) {
        if (teacher != null) {
            teacher.addRating(rating);
        }
    }

    /**
     * @return
     */
    public Transcript viewTranscript() {
        return this.transcript;
    }

    /**
     * @return
     */
    public double getGpa() {
        return this.gpa;
    }

    /**
     * @return
     */
    public int getCredits() {
        return this.credits;
    }

    /**
     * @param teacher 
     * @return
     */
    public void setSupervisor(Teacher teacher) {
        this.supervisor = (Researcher) teacher;
    }

    /**
     * @return
     */
    public Teacher getSupervisor() {
        return (Teacher) this.supervisor;
    }

    /**
     * @param other 
     * @return
     */
    public int compareTo(Student other) {
        return Double.compare(this.gpa, other.gpa);
    }

    /**
     * @return
     */
    public String toString() {
        return "Student: " + getFullName();
    }

    /**
     * @return
     */
    public int getHIndex() {
        // TODO implement research.Researcher.getHIndex() here
        return 0;
    }

    /**
     * @return
     */
    public List<ResearchPaper> getResearchPapers() {
        return this.researchPapers;
    }

    /**
     * @return
     */
    public List<ResearchProject> getResearchProjects() {
        return this.researchProjects;
    }

    /**
     * @param c 
     * @return
     */
    public void printPapers(Comparator<ResearchPaper> c) {
        if (this.researchPapers != null) {
            this.researchPapers.sort(c);
            for (ResearchPaper p : this.researchPapers) {
                System.out.println(p);
            }
        }
    }

    /**
     * @param paper 
     * @return
     */
    public void addResearchPaper(ResearchPaper paper) {
        if (this.researchPapers == null) this.researchPapers = new ArrayList<>();
        this.researchPapers.add(paper);
    }

    /**
     * @param project 
     * @return
     */
    public void addResearchProject(ResearchProject project) {
        if (this.researchProjects == null) this.researchProjects = new ArrayList<>();
        this.researchProjects.add(project);
    }

}