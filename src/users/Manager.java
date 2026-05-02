package users;

import core.Database;
import core.Employee;
import academic.Course;
import enums.ManagerType;
import enums.UserRole;
import enums.RequestStatus;
import services.Request;
import java.util.*;
import java.util.stream.Collectors;

public class Manager extends Employee {
    private static final long serialVersionUID = 8L;

    private ManagerType type;
    private List<String> news;
    private List<Course> managedCourses;

    public Manager(String id, String firstName, String lastName, String email, String password, double salary, ManagerType type) {
        super(id, firstName, lastName, email, password, UserRole.MANAGER, salary);
        this.type = type;
        this.news = new ArrayList<>();
        this.managedCourses = new ArrayList<>();
    }

    public void approveRegistration(Student student, Course course) {
        student.getCourses().add(course);
        System.out.println("Registration approved for " + student.getFullName() + " for " + course.getName());
    }

    /**
     * Assigns a teacher to a specific course.
     */
    public void assignTeacherToCourse(Teacher teacher, Course course) {
        course.setInstructor(teacher);
        System.out.println("Teacher " + teacher.getFullName() + " assigned to " + course.getName());
    }

    public void addNews(String newsContent) {
        this.news.add(newsContent);
        // Можно также добавить новость в глобальный список Database, если он там есть
    }

    public List<Student> viewAllStudents(Comparator<Student> comparator) {
        return Database.getInstance().getUsers().stream()
                .filter(u -> u instanceof Student)
                .map(u -> (Student) u)
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public List<Teacher> viewAllTeachers() {
        return Database.getInstance().getUsers().stream()
                .filter(u -> u instanceof Teacher)
                .map(u -> (Teacher) u)
                .collect(Collectors.toList());
    }

    public void approveRequest(Request request) {
        if (request != null) {
            request.setStatus(RequestStatus.APPROVED);
            System.out.println("Request approved.");
        }
    }

    public String createReport() {
        return "Academic Report - Total Courses: " + managedCourses.size();
    }

    @Override
    public String toString() {
        return super.toString() + " | Type: " + type;
    }
}