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


    /**
     * @param student 
     * @param course 
     * @return
     */
    public void approveRegistration(Student student, Course course) {
        if (course != null) {
            course.addStudent(student);
        }
    }

    /**
     * @param Course c 
     * @param Teacher t 
     * @return
     */
    public void assignTeacher(Course c, Teacher t) {
        if (c != null && t != null) {
            c.addTeacher(t);
            t.addCourse(c);
        }
    }

    /**
     * @return
     */
    public String createReport() {
        return "Manager report";
    }

    /**
     * @return
     */
    public void manageNews() {
        // Logic
    }

    /**
     * @param course 
     * @param major 
     * @param year 
     * @return
     */
    public void addCourseForRegistration(Course course, String major, StudentYear year) {
        if (course != null) {
            course.setOpenForRegistration(true);
        }
    }

    /**
     * @param teacher 
     * @param course 
     * @return
     */
    public void assignTeacherToCourse(Teacher teacher, Course course) {
        if (course != null) {
            course.addTeacher(teacher);
        }
    }

    /**
     * @param news 
     * @return
     */
    public void addNews(String news) {
        if (this.news == null) this.news = new ArrayList<>();
        this.news.add(news);
    }

    /**
     * @param comparator 
     * @return
     */
    public List<Student> viewAllStudents(Comparator<Student> comparator) {
        return null;
    }

    /**
     * @return
     */
    public List<Teacher> viewAllTeachers() {
        return null;
    }

    /**
     * @return
     */
    public List<Request> viewRequests() {
        return null;
    }

    /**
     * @param request 
     * @return
     */
    public void approveRequest(Request request) {
        if (request != null) {
            request.setStatus(enums.RequestStatus.APPROVED);
        }
    }

}