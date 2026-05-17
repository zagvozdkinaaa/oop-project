package users;

import core.Employee;
import academic.Course;
import enums.ManagerType;
import enums.StudentYear;
import enums.UserRole;
import enums.RequestStatus;
import exceptions.AlreadyEnrolledException;
import exceptions.CourseRegistrationClosedException;
import exceptions.MaxStudentsExceededException;
import communication.Request;
import java.util.*;

public class Manager extends Employee {
    private static final long serialVersionUID = 8L;

    private ManagerType type;
    private List<String> news;
    private List<Course> managedCourses;

    public Manager(String id, String firstName, String lastName, String email, String password, ManagerType type, double salary) {
        super(id, firstName, lastName, email, password, UserRole.MANAGER, salary);
        this.type = type;
        this.news = new ArrayList<>();
        this.managedCourses = new ArrayList<>();
    }

    public void approveRegistration(Student student, Course course) throws MaxStudentsExceededException, AlreadyEnrolledException, CourseRegistrationClosedException {
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

    public void addCourseForRegistration(Course course, String major, StudentYear year) {
        if (course != null) {
            course.setOpenForRegistration(true);
        }
    }
    
 
    
    public void manageNews() {
        System.out.println("Current news count: " + (news != null ? news.size() : 0));
    }

    public void addNews(String news) {
        if (this.news == null) this.news = new ArrayList<>();
        this.news.add(news);
    }

    public void openCourse(Course course) {
        if (course != null) {
            course.setOpenForRegistration(true);
        }
    }

    public void closeCourse(Course course) {
        if (course != null) {
            course.setOpenForRegistration(false);
        }
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

    public void approveRequest(Request request) {
        if (request != null) {
            request.setStatus(RequestStatus.APPROVED);
            System.out.println("Request approved.");
        }
    }

    public List<Request> viewRequests() {
        return new ArrayList<>(); 
    }

    public String createReport() {
        return "Manager " + getFullName() + " report - " + new Date();
    }

    @Override
    public String toString() {
        return super.toString() + " | Type: " + type;
    }
}	
