package users;

import enums.TeacherPosition;
import research.Researcher;

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
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public List<Course> getCourses() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public double getRating() {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @return
     */
    public int getHIndex() {
        // TODO implement here
        return 0;
    }

    /**
     * @param student
     * @param course
     * @param mark
     * @return
     */
    public void putMark(Student student, Course course, Mark mark) {
        // TODO implement here
        return null;
    }

    /**
     * @param course
     * @return
     */
    public void addCourse(Course course) {
        // TODO implement here
        return null;
    }

    /**
     * @param course
     * @return
     */
    public void addCourse(Course course) {
        // TODO implement here
        return null;
    }

    /**
     * @param course
     * @return
     */
    public List<Student> viewStudents(Course course) {
        // TODO implement here
        return null;
    }

    /**
     * @param course
     * @return
     */
    public String generateMarkReport(Course course) {
        // TODO implement here
        return "";
    }

    /**
     * @return
     */
    public boolean isResearcher() {
        // TODO implement here
        return false;
    }

    /**
     * @param student
     * @param course
     * @param mark
     * @return
     */
    public void putMark(Student student, Course course, Mark mark) {
        // TODO implement here
        return null;
    }

    /**
     * @param c
     * @return
     */
    public void printPapers(Comparator<ResearchPaper> c) {
        // TODO implement here
        return null;
    }

    /**
     * @param course
     * @return
     */
    public String generateMarkReport(Course course) {
        // TODO implement here
        return "";
    }

    /**
     * @return
     */
    public String toString() {
        // TODO implement here
        return "";
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
        // TODO implement research.Researcher.getResearchPapers() here
        return null;
    }

    /**
     * @return
     */
    public List<ResearchProject> getResearchProjects() {
        // TODO implement research.Researcher.getResearchProjects() here
        return null;
    }

    /**
     * @param c
     * @return
     */
    public void printPapers(Comparator<ResearchPaper> c) {
        // TODO implement research.Researcher.printPapers() here
        return null;
    }

    /**
     * @param paper
     * @return
     */
    public void addResearchPaper(ResearchPaper paper) {
        // TODO implement research.Researcher.addResearchPaper() here
        return null;
    }

    /**
     * @param project
     * @return
     */
    public void addResearchProject(ResearchProject project) {
        // TODO implement research.Researcher.addResearchProject() here
        return null;
    }

}