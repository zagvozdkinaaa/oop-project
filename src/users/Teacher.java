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
    private List<ResearchPaper> researchPapers;
    private List<ResearchProject> researchProjects;
    private boolean isResearcher;

    public Teacher(String id, String firstName, String lastName, String email, String password, double salary, TeacherPosition position) {
        super(id, firstName, lastName, email, password, UserRole.TEACHER, salary);
        this.position = position;
        this.courses = new ArrayList<>();
        this.researchPapers = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
        this.rating = 0.0;
        this.isResearcher = false;
    }


    public void putMark(Student student, Course course, Mark mark) {
        student.viewMarks().add(mark);
        System.out.println("Mark " + mark.getTotal() + " assigned to " + student.getFullName());
    }

    public void addCourse(Course course) {
        if (!courses.contains(course)) {
            this.courses.add(course);
        }
    }

    public List<Student> viewStudents(Course course) {
        return new ArrayList<>();
    }

    public String generateMarkReport(Course course) {
        return "Mark Report for " + course.getName() + " by " + getFullName();
    }


    public TeacherPosition getPosition() { return position; }
    public List<Course> getCourses() { return courses; }
    public double getRating() { return rating; }


    @Override
    public int getHIndex() {
        return researchPapers.size(); // Упрощенная логика для текущей версии
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

    public boolean isResearcher() {
        return isResearcher;
    }

    @Override
    public String toString() {
        return String.format("%s | Position: %s | Rating: %.1f",
                super.toString(), position, rating);
    }
}