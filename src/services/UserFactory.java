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

        double salary = (double) data.getOrDefault("salary", 0.0);

        UserRole role;
        try {
            role = UserRole.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid user type: " + type);
            return null;
        }

        return switch (role) {
            case ADMIN -> new Admin(id, firstName, lastName, email, password, salary);
            case STUDENT -> new Student(id, firstName, lastName, email, password);
            case TEACHER -> {
                enums.TeacherPosition position = (enums.TeacherPosition) data.getOrDefault("position", enums.TeacherPosition.TUTOR);
                yield new Teacher(id, firstName, lastName, email, password, salary,  position);
            }
            case MANAGER -> {
                enums.ManagerType managerType = (enums.ManagerType) data.getOrDefault("managerType", enums.ManagerType.OR);
                yield new Manager(id, firstName, lastName, email, password, managerType, salary);
            }
            default -> {
                System.err.println("Unsupported user role: " + role);
                yield null;
            }
        };
    }

}