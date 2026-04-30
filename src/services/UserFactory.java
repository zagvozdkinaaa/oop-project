package services;

import core.User;
import enums.UserRole;
import users.Admin;
import users.Manager;
import users.Student;
import users.Teacher;

import java.util.*;

public class UserFactory {

    public UserFactory() {
    }

    public User createUser(String type, Map<String, Object> data) {
        String id = (String) data.get("id");
        String firstName = (String) data.get("firstName");
        String lastName = (String) data.get("lastName");
        String email = (String) data.get("email");
        String password = (String) data.get("password");

        UserRole role;
        try {
            role = UserRole.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid user type: " + type);
            return null;
        }

        return switch (role) {
            case ADMIN -> new Admin(id, firstName, lastName, email, password);
            case STUDENT -> new Student(id, firstName, lastName, email, password);
            case TEACHER -> {
                yield new Teacher(id, firstName, lastName, email, password);
            }
            case MANAGER -> {
                yield new Manager(id, firstName, lastName, email, password);
            }
            default -> {
                System.err.println("Unsupported user role: " + role);
                yield null;
            }
        };
    }

}