package users;

import enums.ManagerType;

import java.util.*;

/**
 * 
 */
public class Manager extends Employee {

    /**
     * Default constructor
     */
    public Manager(String id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email, password, enums.UserRole.MANAGER, 0);
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


    /**
     * @param student 
     * @param course 
     * @return
     */
    public void approveRegistration(Student student, Course course) {
        // TODO implement here
        return null;
    }

    /**
     * @param Course c 
     * @param Teacher t 
     * @return
     */
    public void assignTeacher(void Course c, void Teacher t) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public String createReport() {
        // TODO implement here
        return "";
    }

    /**
     * @return
     */
    public void manageNews() {
        // TODO implement here
        return null;
    }

    /**
     * @param course 
     * @param major 
     * @param year 
     * @return
     */
    public void addCourseForRegistration(Course course, String major, StudentYear year) {
        // TODO implement here
        return null;
    }

    /**
     * @param teacher 
     * @param course 
     * @return
     */
    public void assignTeacherToCourse(Teacher teacher, Course course) {
        // TODO implement here
        return null;
    }

    /**
     * @param news 
     * @return
     */
    public void addNews(String news) {
        // TODO implement here
        return null;
    }

    /**
     * @param comparator 
     * @return
     */
    public List<Student> viewAllStudents(Comparator<Student> comparator) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public List<Teacher> viewAllTeachers() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public List<Request> viewRequests() {
        // TODO implement here
        return null;
    }

    /**
     * @param request 
     * @return
     */
    public void approveRequest(Request request) {
        // TODO implement here
        return null;
    }

}