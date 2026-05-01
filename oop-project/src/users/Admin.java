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
    public Admin(String id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email, password, enums.UserRole.ADMIN, 0);
    }

    /**
     * 
     */
    private List<String> logs;

    /**
     * 
     */
    private List<String> logs;

    /**
     * @param user 
     * @return
     */
    public void addUser(User user) {
        // TODO implement here
        return null;
    }

    /**
     * @param user 
     * @return
     */
    public void removeUser(User user) {
        // TODO implement here
        return null;
    }

    /**
     * @param user 
     * @return
     */
    public void updateUser(User user) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public List<User> getAllUsers() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public List<Log> viewLogs() {
        // TODO implement here
        return null;
    }

}