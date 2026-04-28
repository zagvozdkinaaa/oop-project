package core;

import enums.UserRole;

/**
 * 
 */
public abstract class User {

    /**
     * Default constructor
     */
    public User() {
    }

    /**
     * 
     */
    private int id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String firstName;

    /**
     * 
     */
    private String lastName;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private UserRole role;

    /**
     * @param login 
     * @param password 
     * @return
     */
    public boolean login(String login, String password) {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public void logout() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public String getUserId() {
        // TODO implement here
        return "";
    }

    /**
     * @return
     */
    public String getLogin() {
        // TODO implement here
        return "";
    }

    /**
     * @param password 
     * @return
     */
    public void setPassword(String password) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public String getFullName() {
        // TODO implement here
        return "";
    }

    /**
     * @param o 
     * @return
     */
    public boolean equals(Object o) {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public int hashCode() {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public String toString() {
        // TODO implement here
        return "";
    }

}