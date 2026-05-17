package core;

import core.Database;

public class IdGenerator {


    public static String generate(String type) {

        return switch (type) {

            case "STUDENT" -> Database.getInstance().generateStudentId();

            case "TEACHER" -> Database.getInstance().generateTeacherId();

            case "ADMIN" -> Database.getInstance().generateAdminId();

            case "MANAGER" -> Database.getInstance().generateManagerId();
            case "COURSE" -> Database.getInstance().generateCourseId();
            
            

            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }
}