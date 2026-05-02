package users;

import enums.ManagerType;
import enums.StudentYear;
import academic.Course;
import services.Request;

import java.util.*;

/**
 * 
 */
public class Manager extends Employee {

    /**
     * Default constructor
     */
    public Manager() {
    }

    /**
     * 
     */
    private ManagerType type;

    /**
     * 
     */
    private List<String> news;

    /**
     * 
     */
    private List<Course> managedCourses;

    public Manager(String id, String firstName, String lastName, String email, String password, double salary, ManagerType type) {
        super(id, firstName, lastName, email, password, enums.UserRole.MANAGER, salary);
        this.type = type;
        this.news = new ArrayList<>();
        this.managedCourses = new ArrayList<>();
    }

    public void approveRegistration(Student student, Course course) {
        if (course != null && course.isOpenForRegistration()) {
            course.addStudent(student);
        }
    }

    public void assignTeacher(Course c, Teacher t) {
        if (c != null && t != null) {
            c.addTeacher(t);
            t.addCourse(c);
        }
    }

    public String createReport() {
        return "Manager " + getFullName() + " report - " + new Date();
    }

    public void manageNews() {
        System.out.println("Current news count: " + (news != null ? news.size() : 0));
    }

    public void addCourseForRegistration(Course course, String major, StudentYear year) {
        if (course != null) {
            course.setOpenForRegistration(true);
        }
    }

    public void assignTeacherToCourse(Teacher teacher, Course course) {
        if (course != null) {
            course.addTeacher(teacher);
        }
    }

    public void addNews(String news) {
        if (this.news == null) this.news = new ArrayList<>();
        this.news.add(news);
    }

    public List<Student> viewAllStudents(Comparator<Student> comparator) {
        List<Student> students = new ArrayList<>();
        for (core.User u : core.Database.getInstance().getUsers()) {
            if (u instanceof Student) {
                students.add((Student) u);
            }
        }
        if (comparator != null) {
            students.sort(comparator);
        }
        return students;
    }

    public List<Teacher> viewAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        for (core.User u : core.Database.getInstance().getUsers()) {
            if (u instanceof Teacher) {
                teachers.add((Teacher) u);
            }
        }
        return teachers;
    }

    public List<Request> viewRequests() {
        return new ArrayList<>(); // Request storage in Database
    }

    public void approveRequest(Request request) {
        if (request != null) {
            request.setStatus(enums.RequestStatus.APPROVED);
        }
    }

}