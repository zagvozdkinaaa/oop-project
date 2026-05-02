package users;

import core.Database;
import core.Employee;
import core.User;
import enums.UserRole;
import java.util.ArrayList;
import java.util.List;

public class Admin extends Employee {
    private static final long serialVersionUID = 7L;

    private List<String> logs;

    public Admin(String id, String firstName, String lastName, String email, String password, double salary) {
        super(id, firstName, lastName, email, password, UserRole.ADMIN, salary);
        this.logs = new ArrayList<>();
    }

    public void addUser(User user) {
        if (user != null) {
            Database.getInstance().addUser(user);
            addLog("Added user: " + user.getUserId());
        }
    }

    public void removeUser(User user) {
        if (user != null) {
            Database.getInstance().removeUser(user.getUserId());
            addLog("Removed user: " + user.getUserId());
        }
    }

    public void updateUser(User user) {
        addLog("Updated user: " + user.getUserId());
    }

    public List<User> getAllUsers() {
        return Database.getInstance().getUsers();
    }

    public List<String> viewLogs() {
        return logs;
    }

    private void addLog(String action) {
        String logEntry = new java.util.Date() + ": " + action;
        this.logs.add(logEntry);
        System.out.println("[ADMIN LOG] " + logEntry);
    }
}