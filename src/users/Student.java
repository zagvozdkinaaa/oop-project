package users;

import academic.Course;
import academic.Mark;
import academic.Transcript;
import core.User;
import research.ResearchPaper;
import research.ResearchProject;
import research.Researcher;
import communication.Request;
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

    /**
     * Отправить запрос на назначение supervisor администрации
     */
    public Request requestSupervisor(Teacher teacher) throws exceptions.LowHIndexException {
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher cannot be null");
        }

        if (this.year != 4) {
            throw new IllegalArgumentException("Supervisor can only be assigned to 4th year students");
        }

        if (!(teacher instanceof Researcher)) {
            throw new IllegalArgumentException("Supervisor must be a Researcher");
        }

        Researcher researcher = (Researcher) teacher;
        if (!researcher.isResearcher()) {
            throw new IllegalArgumentException("Supervisor must be an active Researcher");
        }
        if (researcher.getHIndex() < 3) {
            throw new exceptions.LowHIndexException(researcher.getHIndex(), 3);
        }

        String description = String.format(
            "Student %s (%s) requests supervisor appointment for teacher %s. Major: %s, Year: %d",
            this.getFullName(), this.getUserId(), teacher.getFullName(), this.major, this.year
        );

        Request req = new Request(this, description, "SUPERVISOR", teacher);
        core.Database.getInstance().addRequest(req);
        return req;
    }

    /**
     * Установить supervisor (вызывается администратором при одобрении запроса)
     */
    protected void assignSupervisor(Teacher teacher) throws exceptions.LowHIndexException {
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher cannot be null");
        }

        if (!(teacher instanceof Researcher)) {
            throw new IllegalArgumentException("Supervisor must be a Researcher");
        }

        Researcher researcher = (Researcher) teacher;
        if (!researcher.isResearcher()) {
            throw new IllegalArgumentException("Supervisor must be an active Researcher");
        }
        if (researcher.getHIndex() < 3) {
            throw new exceptions.LowHIndexException(researcher.getHIndex(), 3);
        }

        this.supervisor = researcher;
    }

    /**
     * @deprecated Используйте requestSupervisor() и Admin.approveSupervisorRequest() вместо этого
     */
    @Deprecated
    public void setSupervisor(Teacher teacher) throws exceptions.LowHIndexException {
        assignSupervisor(teacher);
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

    @Override
    public int compareTo(Student other) {
        return Double.compare(other.getGpa(), this.getGpa());
    }

    @Override
    public boolean isResearcher() {
        return this.isResearcher;
    }

    @Override
    public String toString() {
        return String.format("%s | Major: %s | GPA: %.2f | Year: %d",
                super.toString(), major, gpa, year);
    }

}
