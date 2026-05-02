package users;

import core.User;

import java.util.*;

/**
 * 
 */
public class Admin extends Employee {

    /**
     * Default constructor
     */
    public Admin() {
    }

    /**
     * 
     */
    private List<String> logs;

    public Admin(String id, String firstName, String lastName, String email, String password, double salary) {
        super(id, firstName, lastName, email, password, enums.UserRole.ADMIN, salary);
        this.logs = new ArrayList<>();
    }

    public void addUser(User user) {
        core.Database.getInstance().addUser(user);
        logs.add("Added user: " + user.getUserId());
    }

    public void removeUser(User user) {
        core.Database.getInstance().removeUser(user.getUserId());
        logs.add("Removed user: " + user.getUserId());
    }

    public void updateUser(User user) {
        List<User> users = core.Database.getInstance().getUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(user.getUserId())) {
                users.set(i, user);
                logs.add("Updated user: " + user.getUserId());
                return;
            }
        }
    }

    public List<User> getAllUsers() {
        return core.Database.getInstance().getUsers();
    }

    public List<String> viewLogs() {
        return logs;
    }

}