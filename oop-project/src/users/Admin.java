package users;

import core.Employee;
import core.User;
import core.Database;
import java.util.*;

public class Admin extends Employee {

    private List<String> logs = new ArrayList<>();

    public Admin(String id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email, password, enums.UserRole.ADMIN, 0);
    }

    public void addUser(User user) {
        Database.getInstance().addUser(user);
        logs.add("Added user: " + (user != null ? user.getFullName() : "null"));
    }

    public void removeUser(User user) {
        if (user != null) {
            Database.getInstance().removeUser(user.getUserId());
            logs.add("Removed user: " + user.getFullName());
        }
    }

    public void updateUser(User user) {
        // Update user placeholder logic
        logs.add("Updated user: " + (user != null ? user.getFullName() : "null"));
    }

    public List<User> getAllUsers() {
        return Database.getInstance().getUsers();
    }

    public List<String> viewLogs() {
        return logs;
    }
}