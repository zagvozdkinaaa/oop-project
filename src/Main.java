mport core.Database;
import core.User;
import services.AuthService;
import ui.MenuController;

import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner =
            new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println(
                "=== Research-Oriented University Information System ===\n"
        );

        Database db = Database.getInstance();

        
        db.load();

        if (db.getUsers().isEmpty()) {

            users.UserFactory factory = new users.UserFactory();

            User admin = factory.createUser(
                    "ADMIN",
                    Map.of(
                            "id", "A001",
                            "firstName", "Admin",
                            "lastName", "System",
                            "email", "admin@uni.kz",
                            "password", "admin123",
                            "salary", 500000.0
                    )
            );

            db.addUser(admin);
            db.save();

            System.out.println("Default admin created.");
            System.out.println("Email: admin@uni.kz");
            System.out.println("Password: admin123");
        }

        boolean running = true;

        while (running) {

            User currentUser = loginUser();

            if (currentUser == null) {

                System.out.println("Invalid email or password.");

                System.out.print("Try again? (y/n): ");

                String answer =
                        scanner.nextLine().trim();

                if (!answer.equalsIgnoreCase("y")) {
                    running = false;
                }

                continue;
            }


            MenuController.showMenuForUser(currentUser);

            
            db.save();
        }

        
        db.save();

        System.out.println("System closed.");
    }

    private static User loginUser() {

        System.out.print("Enter email: ");
        String email =
                scanner.nextLine().trim();

        System.out.print("Enter password: ");
        String password =
                scanner.nextLine().trim();

        return AuthService
                .getInstance()
                .login(email, password);
    }
}
