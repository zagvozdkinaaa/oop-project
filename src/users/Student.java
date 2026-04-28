package users;

import core.User;
import research.Researcher;

import java.util.*;

/**
 * 
 */
public class Student extends User implements Researcher, Comparable<Student> {

    /**
     * Default constructor
     */
    public Student() {
    }

    /**
     * 
     */
    private double gpa;

    /**
     * 
     */
    private int year;

    /**
     * 
     */
    private String major;

    /**
     * 
     */
    private int credits;

    /**
     * 
     */
    private List<Course> courses;

    /**
     * 
     */
    private List<Mark> marks;

    /**
     * 
     */
    private Transcript transcript;

    /**
     * 
     */
    private Researcher supervisor;

    /**
     * 
     */
    private int failCount;

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
     * @param course 
     * @return
     */
    public void registerCourse(Course course) {
        // TODO implement here
        return null;
    }

    /**
     * @param course 
     * @return
     */
    public void dropCourse(Course course) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public List<Course> viewCourses() {
        // TODO implement here
        return null;
    }

    /**
     * @param Course c 
     * @return
     */
    public List<Student> viewStudents(void Course c) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public List<Mark> viewMarks() {
        // TODO implement here
        return null;
    }

    /**
     * @param teacher 
     * @param rating 
     * @return
     */
    public void rateTeacher(Teacher teacher, double rating) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Transcript viewTranscript() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public double getGpa() {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @return
     */
    public int getCredits() {
        // TODO implement here
        return 0;
    }

    /**
     * @param teacher 
     * @return
     */
    public void setSupervisor(Teacher teacher) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Teacher getSupervisor() {
        // TODO implement here
        return null;
    }

    /**
     * @param other 
     * @return
     */
    public int compareTo(Student other) {
        // TODO implement here
        return 0;
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