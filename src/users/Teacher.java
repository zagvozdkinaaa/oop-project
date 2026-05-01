package users;

import enums.TeacherPosition;
import research.Researcher;
import research.ResearchPaper;
import research.ResearchProject;
import academic.Course;
import academic.Mark;

import java.util.*;

/**
 * 
 */
public class Teacher extends Employee implements Researcher {

    /**
     * Default constructor
     */
    public Teacher() {
    }

    /**
     * 
     */
    private TeacherPosition position;

    /**
     * 
     */
    private List<Course> courses;

    /**
     * 
     */
    private double rating;

    /**
     * 
     */
    private int hIndex;

    /**
     * 
     */
    private List<ResearchPaper> researchPapers;

    /**
     * 
     */
    private List<ResearchProject> researchProjects;

    /**
     * 
     */
    private boolean isResearcher;

    /**
     * 
     */
    public void Attribute1;




    /**
     * @return
     */
    public TeacherPosition getPosition() {
        return this.position;
    }

    /**
     * @return
     */
    public List<Course> getCourses() {
        return this.courses;
    }

    /**
     * @return
     */
    public double getRating() {
        return this.rating;
    }

    /**
     * @return
     */
    public int getHIndex() {
        return this.hIndex;
    }

    /**
     * @param student 
     * @param course 
     * @param mark 
     * @return
     */
    public void putMark(Student student, Course course, Mark mark) {
        if (student != null && student.viewTranscript() != null) {
            student.viewTranscript().addMark(mark);
        }
    }

    /**
     * @param course 
     * @return
     */
    public void addCourse(Course course) {
        if (this.courses == null) this.courses = new ArrayList<>();
        this.courses.add(course);
    }

    /**
     * @param course 
     * @return
     */
    public List<Student> viewStudents(Course course) {
        if (course != null) {
            return course.getEnrolledStudents();
        }
        return null;
    }

    /**
     * @param course 
     * @return
     */
    public String generateMarkReport(Course course) {
        return "Mark report for " + (course != null ? course.getName() : "unknown");
    }

    /**
     * @return
     */
    public boolean isResearcher() {
        return this.isResearcher;
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
     * @return
     */
    public String toString() {
        return "Teacher: " + getFullName();
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

    public void addRating(double r) {
        // Simple rating update
        this.rating = (this.rating + r) / 2;
    }

}