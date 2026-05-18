package users;

import enums.ManagerType;
import core.Employee;
import academic.Course;
import services.Request;
import java.util.*;

public class Manager extends Employee {

    private ManagerType type;
    private List<String> news = new ArrayList<>();
    private List<Course> managedCourses = new ArrayList<>();

    public Manager(String id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email, password, enums.UserRole.MANAGER, 0);
    }

    public void approveRegistration(Student student, Course course) {
        try {
            student.registerCourse(course);
        } catch (Exception e) {
            System.err.println("Failed to approve registration: " + e.getMessage());
        }
    }

    public void assignTeacher(Course c, Teacher t) {
        if (c != null && t != null) {
            c.addTeacher(t);
        }
    }

    public String createReport() {
        return "Report created by Manager " + getFullName();
    }

    public void manageNews() {
        // Implementation placeholder
    }

    public void addCourseForRegistration(Course course, String major, int year) {
        if (course != null) {
            managedCourses.add(course);
        }
    }

    public void assignTeacherToCourse(Teacher teacher, Course course) {
        if (course != null && teacher != null) {
            course.addTeacher(teacher);
        }
    }

    public void addNews(String newsItem) {
        news.add(newsItem);
    }

    public List<Student> viewAllStudents(Comparator<Student> comparator) {
        // Implementation placeholder
        return new ArrayList<>();
    }

    public List<Teacher> viewAllTeachers() {
        // Implementation placeholder
        return new ArrayList<>();
    }

    public List<Request> viewRequests() {
        // Implementation placeholder
        return new ArrayList<>();
    }

    public void approveRequest(Request request) {
        if (request != null) {
            request.setStatus(enums.RequestStatus.APPROVED);
        }
    }

}