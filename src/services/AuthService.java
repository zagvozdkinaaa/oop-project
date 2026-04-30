package services;

import core.Database;
import core.User;

public class AuthService {

    private static AuthService instance;

    private User currentUser;

    private AuthService() {
    }
    public static AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }

    public User login(String login, String password) {
        for (User user : Database.getInstance().getUsers()) {
            if (user.login(login, password)) {
                this.currentUser = user;
                System.out.println("Welcome, " + user.getFullName() + "!");
                return user;
            }
        }
        System.out.println("Error: Invalid email or password.");
        return null;
    }

    public void logout() {
        if (currentUser != null) {
            currentUser.logout();
            this.currentUser = null;
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }
}