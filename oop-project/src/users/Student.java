package users;

import academic.*;
import core.User;
import research.ResearchPaper;
import research.ResearchProject;
import research.Researcher;
import exceptions.CreditLimitExceededException;
import exceptions.LowHIndexException;

import java.util.*;

public class Student extends User implements Researcher, Comparable<Student> {

    private double gpa;
    private int year;
    private String major;
    private int credits;
    private List<Course> courses = new ArrayList<>();
    private List<Mark> marks = new ArrayList<>();
    private Transcript transcript;
    private Researcher supervisor;
    private int failCount;
    private double balance = 500000;

    private List<ResearchPaper> researchPapers = new ArrayList<>();
    private List<ResearchProject> researchProjects = new ArrayList<>();

    public Student(String id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email, password, enums.UserRole.STUDENT);
        this.year = 1;
        this.credits = 0;
        this.transcript = new Transcript();
    }

    public void registerCourse(Course course) throws Exception {
        if (!course.isOpen()) {
            throw new Exception("Registration for " + course.getName() + " is closed");
        }
        if (this.credits + course.getCredits() > 21) {
            throw new CreditLimitExceededException(this.credits + course.getCredits());
        }
        this.courses.add(course);
        this.credits += course.getCredits();
        course.addStudent(this);
    }

    public void payForRetake(Course course) throws Exception {
        boolean failed = marks.stream().anyMatch(m -> m.getCourse().equals(course) && m.isFailed());
        if (!failed) {
            throw new Exception("You didn't fail this course ");
        }
        double cost = course.getCredits() * 25000;
        if (balance < cost) throw new Exception("Not enough balance ");
        balance -= cost;
        marks.removeIf(m -> m.getCourse().equals(course));
        System.out.println("Retake paid for " + course.getName());
    }

    public List<Course> viewCourses() { return courses; }
    public List<Mark> viewMarks() { return marks; }
    public void addMark(Mark mark) { this.marks.add(mark); }
    public double getGpa() { return gpa; }
    public Transcript viewTranscript() { return transcript; }
    public double getBalance() { return balance; }

    public void setSupervisor(Researcher supervisor) throws LowHIndexException {
        if (this.year == 4 && supervisor.getHIndex() < 3) {
            throw new LowHIndexException(supervisor.getHIndex(), 3);
        }
        this.supervisor = supervisor;
    }

    @Override
    public int getHIndex() {
        if (researchPapers.isEmpty()) return 0;
        List<Integer> citations = new ArrayList<>();
        for (ResearchPaper p : researchPapers) citations.add(p.getCitations());
        citations.sort(Comparator.reverseOrder());
        int hIndex = 0;
        for (int i = 0; i < citations.size(); i++) {
            if (citations.get(i) >= i + 1) hIndex = i + 1;
            else break;
        }
        return hIndex;
    }

    @Override
    public List<ResearchPaper> getResearchPapers() { return researchPapers; }
    @Override
    public List<ResearchProject> getResearchProjects() { return researchProjects; }
    @Override
    public void addResearchPaper(ResearchPaper paper) { researchPapers.add(paper); }
    @Override
    public void addResearchProject(ResearchProject project) { researchProjects.add(project); }
    @Override
    public void printPapers(Comparator<ResearchPaper> c) {
        researchPapers.stream().sorted(c).forEach(System.out::println);
    }

    @Override
    public int compareTo(Student other) { return Double.compare(other.gpa, this.gpa); }

    @Override
    public String toString() {
        return super.toString() + String.format(" GPA: %.2f, Balance: %.0f", gpa, balance);
    }
}