package core;

import academic.Course;
import communication.Request;
import research.ResearchPaper;
import research.ResearchProject;
import users.Student;
import users.Teacher;
import communication.Message;
import core.IdGenerator;

import java.io.*;
import java.util.*;

public class Database implements Serializable {
    private static final long serialVersionUID = 6L;
    private static final String FILE_NAME = "database.ser";

    private static Database instance;

    private List<User> users;
    private List<Course> courses;
    private List<ResearchPaper> researchPapers;
    private List<ResearchProject> researchProjects;
    private List<Message> messages;
    private List<Request> requests;
    private int studentCounter = 0;
    private int teacherCounter = 0;
    private int adminCounter = 0;
    private int managerCounter = 0;
    
   

    private Database() {
        this.users = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.researchPapers = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.requests = new ArrayList<>();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void load() {
        File file = new File(FILE_NAME);

        if (file.exists()) {
            try (ObjectInputStream ois =
                         new ObjectInputStream(new FileInputStream(file))) {

                Database loaded = (Database) ois.readObject();

                
                this.users = loaded.users;
                this.courses = loaded.courses;
                this.researchPapers = loaded.researchPapers;
                this.researchProjects = loaded.researchProjects;
                this.messages = loaded.messages;
                this.requests = loaded.requests;

                this.studentCounter = loaded.studentCounter;
                this.teacherCounter = loaded.teacherCounter;
                this.adminCounter = loaded.adminCounter;
                this.managerCounter = loaded.managerCounter;

                System.out.println("Database loaded. Users: " + users.size());

            } catch (Exception e) {
                System.out.println("Error loading database: " + e.getMessage());
            }
        } else {
            System.out.println("No database file found.");
        }
    }
    public void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(this);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving: " + e.getMessage());
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<ResearchPaper> getResearchPapers() {
        return researchPapers;
    }

    public List<ResearchProject> getResearchProjects() {
        return researchProjects;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void addRequest(Request request) {
        if (request != null && !requests.contains(request)) {
            requests.add(request);
        }
    }

    public void addUser(User user) {

        if (user == null) return;

        for (User u : users) {
            if (u.getUserId().equals(user.getUserId())) {
                System.out.println("User with ID already exists: " + user.getUserId());
                return;
            }
        }

        users.add(user);
        System.out.println("✓ Added: " + user.getFullName() + " (" + user.getEmail() + ")");
        save();
    }

    public void removeUser(String userId) {

        User userToRemove = null;

        for (User u : users) {
            if (u.getUserId().equals(userId)) {
                userToRemove = u;
                break;
            }
        }

        if (userToRemove == null) {
            System.out.println("User not found.");
            return;
        }

        
        if (userToRemove.getRole() == enums.UserRole.ADMIN) {
            System.out.println("You cannot delete an ADMIN user.");
            return;
        }

        
        if (userToRemove instanceof Student student) {
            for (Course c : courses) {
                c.getEnrolledStudents().remove(student);
            }
        }

        
        if (userToRemove instanceof Teacher teacher) {
            for (Course c : courses) {
                c.getTeachers().remove(teacher);
            }
        }

        users.remove(userToRemove);
        save();

        System.out.println("User removed successfully.");
    }
    public String generateStudentId() {

        int i = 1;

        while (true) {
            String id = "S" + String.format("%03d", i);

            boolean exists = false;

            for (User u : users) {
                if (u.getUserId().equals(id)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                return id; 
            }

            i++;
        }
        
    }
    public String generateTeacherId() {
        int i = 1;

        while (true) {
            String id = "T" + String.format("%03d", i);

            boolean exists = false;

            for (User u : users) {
                if (u.getUserId().equals(id)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) return id;

            i++;
        }
    }
    public String generateAdminId() {
        int i = 1;

        while (true) {
            String id = "A" + String.format("%03d", i);

            boolean exists = false;

            for (User u : users) {
                if (u.getUserId().equals(id)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) return id;

            i++;
        }
    }
    public String generateManagerId() {
        int i = 1;

        while (true) {
            String id = "M" + String.format("%03d", i);

            boolean exists = false;

            for (User u : users) {
                if (u.getUserId().equals(id)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) return id;

            i++;
        }
    }
    public String generateCourseId() {

        int i = 1;

        while (true) {

            String id =
                    "C" + String.format("%03d", i);

            boolean exists = false;

            for (Course c : courses) {

                if (c.getCourseId().equals(id)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                return id;
            }

            i++;
        }
    }
    public void removeCourse(String courseId) {

        Course courseToRemove = null;

        for (Course c : courses) {
            if (c.getCourseId().equals(courseId)) {
                courseToRemove = c;
                break;
            }
        }

        if (courseToRemove == null) {
            System.out.println("Course not found.");
            return;
        }

        
        for (Student s : users.stream().filter(u -> u instanceof Student).map(u -> (Student) u).toList()) {
            s.dropCourse(courseToRemove);
        }

       
        for (User u : users) {
            if (u instanceof Teacher t) {
                t.getCourses().remove(courseToRemove);
            }
        }

        courses.remove(courseToRemove);

        save();

        System.out.println("Course removed successfully.");
    }
}
