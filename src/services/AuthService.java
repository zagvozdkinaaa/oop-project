package services;

import core.Database;
import core.User;

public class AuthService {
    
    private static AuthService instance;
    private User currentUser;

    private AuthService() {}

    public static AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }

    public User login(String email, String password) {
        System.out.println("Trying to login with: '" + email + "'");

        Database db = Database.getInstance();   // берём базу
        System.out.println("Total users in DB: " + db.getUsers().size());

        for (User user : db.getUsers()) {
            System.out.println("  Checking: " + user.getEmail() + " | " + user.getFullName());

            if (user.getEmail().equalsIgnoreCase(email) && 
                user.login(email, password)) {
                
                System.out.println("SUCCESS! Logged in as " + user.getFullName());
                this.currentUser = user;
                return user;
            }
        }
        System.out.println("Error: No matching user found.");
        return null;
    }

    public User getCurrentUser() {
        return currentUser;
    }




    public void logout() {
        if (currentUser != null) {
            currentUser.logout();
            this.currentUser = null;
        }
    }


    public boolean isLoggedIn() {
        return currentUser != null;
    }
}
