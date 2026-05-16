package core;

import academic.Course;
import communication.Request;
import research.ResearchPaper;
import research.ResearchProject;
import communication.Message;

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

    public void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(this);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving database: " + e.getMessage());
        }
    }

    public void load() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                instance = (Database) ois.readObject();
                System.out.println("Database loaded from file.");
            } catch (Exception e) {
                System.err.println("Error loading database, creating new one: " + e.getMessage());
                instance = new Database();
            }
        } else {
            instance = new Database();
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
        if (user != null && !users.contains(user)) {
            users.add(user);
        }
    }

    public void removeUser(String userId) {
        users.removeIf(u -> u.getUserId().equals(userId));
    }

}