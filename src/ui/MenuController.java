package ui;

import academic.Mark;
import communication.Request;
import core.User;
import core.Database;
import research.ResearchPaper;
import users.Admin;
import users.Manager;
import users.Teacher;
import users.Student;
import users.UserFactory;
import java.util.*;

import academic.Course;
import academic.Lesson;
import enums.LessonType;
import core.IdGenerator;

public class MenuController {

    private static final Scanner scanner = new Scanner(System.in);
    private static final UserFactory factory = new UserFactory();
    
    private static void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
    private static List<Course> getAvailableCourses() {

        List<Course> result = new ArrayList<>();

        for (Course c : Database.getInstance().getCourses()) {

            if (c.getEnrolledStudents().size() < 30
                    && c.isOpenForRegistration()) {
                result.add(c);
            }
        }

        return result;
    }
    
    private static void createCourseUI() {

        System.out.println("\n=== Create Course ===");

        String courseId =
                IdGenerator.generate("COURSE");

        System.out.print("Course name: ");
        String name = scanner.nextLine();

        System.out.print("Course code: ");
        String code = scanner.nextLine();

        System.out.print("Credits: ");
        int credits = readInt();

        Course course = new Course(
                courseId,
                name,
                code,
                credits
        );

        Database.getInstance()
                .getCourses()
                .add(course);

        Database.getInstance().save();

        System.out.println(
                "Course created successfully!"
        );

        System.out.println(
                "Course ID: " + courseId
        );
    }
    private static void requestSupervisor(Student student) {

        Database db = Database.getInstance();

        List<User> users = db.getUsers();

        List<Teacher> teachers = new ArrayList<>();

        
        for (User u : users) {
            if (u instanceof Teacher t) {
                teachers.add(t);
            }
        }

        if (teachers.isEmpty()) {
            System.out.println("No teachers available.");
            return;
        }

        System.out.println("\n=== Available Teachers ===");

        for (int i = 0; i < teachers.size(); i++) {

            Teacher t = teachers.get(i);

            System.out.println(
                    (i + 1) + ". " +
                    t.getFullName() +
                    " | Position: " +
                    t.getPosition() +
                    " | H-index: " +
                    t.getHIndex()
            );
        }

        System.out.print("\nChoose teacher (0 to cancel): ");

        int choice = readInt();

        if (choice == 0) return;

        if (choice < 1 || choice > teachers.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Teacher selectedTeacher = teachers.get(choice - 1);

        try {

            Request req = student.requestSupervisor(selectedTeacher);

            System.out.println("\nRequest sent successfully!");
            System.out.println(req);

        } catch (Exception e) {

            System.out.println("Request failed: " + e.getMessage());
        }
    }
    
    private static void viewRequests(Admin admin) {

        List<Request> requests =
                admin.viewSupervisorRequests();

        if (requests.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }

        System.out.println("\n=== Pending Supervisor Requests ===");

        for (int i = 0; i < requests.size(); i++) {

            System.out.println((i + 1) + ".\n" + requests.get(i));
        }
    }
    
    private static void approveRequest(Admin admin) {

        List<Request> requests =
                admin.viewSupervisorRequests();

        if (requests.isEmpty()) {
            System.out.println("No requests to approve.");
            return;
        }

        viewRequests(admin);

        System.out.print("\nSelect request number: ");
        int choice = readInt();

        if (choice < 1 || choice > requests.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Request req = requests.get(choice - 1);

        admin.approveSupervisorRequest(req);

        System.out.println("Request approved!");
    }
    
    private static void updateTeacherHIndex(Teacher teacher) {
        List<research.ResearchPaper> papers = teacher.getResearchPapers();

        List<Integer> citations = papers.stream()
                .map(research.ResearchPaper::getCitations)
                .sorted((a, b) -> b - a)
                .toList();

        int h = 0;

        for (int i = 0; i < citations.size(); i++) {
            if (citations.get(i) >= i + 1) {
                h++;
            } else {
                break;
            }
        }

        // через reflection-safe способ
        try {
            java.lang.reflect.Field field = Teacher.class.getDeclaredField("hIndex");
            field.setAccessible(true);
            field.set(teacher, h);
        } catch (Exception e) {
            System.out.println("Failed to update H-index: " + e.getMessage());
        }
    }
    
    private static void addCitationUI(Teacher teacher) {

        List<research.ResearchPaper> papers = teacher.getResearchPapers();

        if (papers.isEmpty()) {
            System.out.println("No research papers found.");
            return;
        }

        System.out.println("\n=== Select Paper ===");

        for (int i = 0; i < papers.size(); i++) {
            System.out.println((i + 1) + ". " + papers.get(i).getTitle());
        }

        System.out.print("Choose paper: ");
        int index = readInt() - 1;

        if (index < 0 || index >= papers.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        System.out.print("Enter citations to add: ");
        int citations = readInt();

        if (citations <= 0) {
            System.out.println("Invalid citation count.");
            return;
        }

        research.ResearchPaper paper = papers.get(index);

        paper.setCitations(paper.getCitations() + citations);

        System.out.println("Citations updated!");

        
        updateTeacherHIndex(teacher);

        core.Database.getInstance().save();
    }
    
    private static void rejectRequest(Admin admin) {

        List<Request> requests =
                admin.viewSupervisorRequests();

        if (requests.isEmpty()) {
            System.out.println("No requests to reject.");
            return;
        }

        viewRequests(admin);

        System.out.print("\nSelect request number: ");
        int choice = readInt();

        if (choice < 1 || choice > requests.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Request req = requests.get(choice - 1);

        admin.rejectSupervisorRequest(req);

        System.out.println("Request rejected!");
    }
    
    
    private static void addUserUI(Admin admin) {

        System.out.println("\n=== Add User ===");
        System.out.println("1. Student");
        System.out.println("2. Teacher");
        System.out.println("3. Manager");
        System.out.println("4. Admin");

        int type = readInt();

        System.out.print("First name: ");
        String first = scanner.nextLine();

        System.out.print("Last name: ");
        String last = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = null;

        switch (type) {

        case 1 -> {

            System.out.println("Select student year:");
            System.out.println("1. First year");
            System.out.println("2. Second year");
            System.out.println("3. Third year");
            System.out.println("4. Fourth year");

            int year = readInt();

            if (year < 1 || year > 4) {
                System.out.println("Invalid year. Default = 1");
                year = 1;
            }

            user = factory.createUser(
                    "STUDENT",
                    Map.of(
                            "id", IdGenerator.generate("STUDENT"),
                            "firstName", first,
                            "lastName", last,
                            "email", email,
                            "password", password,
                            "major", "CS",
                            "year", year
                    )
            );
        }	

            case 2 -> {

                user = factory.createUser(
                        "TEACHER",
                        Map.of(
                                "id", IdGenerator.generate("TEACHER"),
                                "firstName", first,
                                "lastName", last,
                                "email", email,
                                "password", password,
                                "salary", 300000.0,
                                "position", enums.TeacherPosition.PROFESSOR
                        )
                );

                Teacher teacher = (Teacher) user;

                List<Course> courses =
                        Database.getInstance().getCourses();

                if (courses.isEmpty()) {

                    System.out.println(
                            "No courses available. Create course first."
                    );

                } else {

                    System.out.println("\n=== Select Course ===");

                    for (int i = 0; i < courses.size(); i++) {

                        System.out.println(
                                (i + 1) + ". "
                                + courses.get(i).getName()
                        );
                    }

                    System.out.print("Choose course: ");

                    int choice = readInt() - 1;

                    if (choice >= 0
                            && choice < courses.size()) {

                        Course selectedCourse =
                                courses.get(choice);

                        teacher.addCourse(selectedCourse);

                        selectedCourse.addTeacher(teacher);

                        System.out.println(
                                "Teacher assigned to: "
                                + selectedCourse.getName()
                        );
                    }
                }
            }

            case 3 -> user = factory.createUser(
                    "MANAGER",
                    Map.of(
                    		"id", IdGenerator.generate("MANAGER"),
                            "firstName", first,
                            "lastName", last,
                            "email", email,
                            "password", password,
                            "salary", 250000.0,
                            "managerType", enums.ManagerType.OR
                    )
            );

            case 4 -> user = factory.createUser(
                    "ADMIN",
                    Map.of(
                    		"id", IdGenerator.generate("ADMIN"),
                            "firstName", first,
                            "lastName", last,
                            "email", email,
                            "password", password,
                            "salary", 400000.0
                    )
            );

            default -> {
                System.out.println("Invalid type");
                return;
            }
        }

        admin.addUser(user);

        System.out.println("User added successfully!");
    }
    
    private static void removeUserUI(Admin admin) {

        List<User> users = admin.getAllUsers();

        System.out.println("\n=== Users ===");

        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + ". " + users.get(i));
        }

        System.out.print("\nSelect user to remove: ");

        int choice = readInt();

        if (choice < 1 || choice > users.size()) {
            System.out.println("Invalid choice");
            return;
        }

        User user = users.get(choice - 1);

        admin.removeUser(user);

        System.out.println("User removed!");
    }
    
    private static void removeCourseUI(Admin admin) {

        List<Course> courses = Database.getInstance().getCourses();

        if (courses.isEmpty()) {
            System.out.println("No courses.");
            return;
        }

        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i));
        }

        System.out.print("Select course to remove: ");
        int choice = readInt();

        if (choice < 1 || choice > courses.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Course course = courses.get(choice - 1);

        Database.getInstance().removeCourse(course.getCourseId());

        System.out.println("Course deleted!");
    }
    
    private static void assignCourseToTeacher(Teacher teacher) {

        List<Course> courses = Database.getInstance().getCourses();

        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }

        System.out.println("\n=== Available Courses ===");

        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i).getName());
        }

        System.out.print("Select course to assign to yourself: ");
        int choice = readInt() - 1;

        if (choice < 0 || choice >= courses.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Course selected = courses.get(choice);

        
        if (teacher.getCourses().contains(selected)) {
            System.out.println("You are already assigned to this course.");
            return;
        }

        teacher.addCourse(selected);
        selected.addTeacher(teacher);
        Database.getInstance().save();

        System.out.println("Course assigned successfully!");
    }
    
    private static void addResearchPaperUI(Teacher teacher) {

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Authors (comma separated): ");
        String authorsInput = scanner.nextLine();

        List<String> authors = Arrays.stream(authorsInput.split(","))
                .map(String::trim)
                .toList();

        ResearchPaper paper = new ResearchPaper(
                UUID.randomUUID().toString(),
                title,
                authors,
                "Unknown Journal",
                10,
                new Date(),
                0,
                "N/A"
        );

        teacher.addResearchPaper(paper);

        core.Database.getInstance().save();

        System.out.println("Research paper added!");
    }
    
    public static void showMenuForUser(User user) {
        boolean session = true;

        while (session) {
            clearScreen();
            System.out.println("=== Welcome, " + user.getFullName() + " ===");
            System.out.println("Role: " + user.getRole());

            if (user instanceof Admin admin) {
                showAdminMenu(admin);
            } else if (user instanceof Manager manager) {
                showManagerMenu(manager);
            } else if (user instanceof Teacher teacher) {
                showTeacherMenu(teacher);
            } else if (user instanceof Student student) {
                showStudentMenu(student);
            } else {
                System.out.println("Unknown role!");
                break;
            }

            System.out.print("\nLog out? (y/n): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                session = false;
            }
        }
    }
    
 

    private static void showAdminMenu(Admin admin) {

        while (true) {

            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View All Users");
            System.out.println("2. Add User");
            System.out.println("3. Remove User");
            System.out.println("4. View Supervisor Requests");
            System.out.println("5. Approve Request");
            System.out.println("6. Reject Request");
            System.out.println("7. View Logs");
            System.out.println("8. Create Course");
            System.out.println("9. Remove Course");
            System.out.println("0. Back");

            int choice = readInt();

            if (choice == 0) return;

            switch (choice) {

                case 1 -> admin.getAllUsers()
                        .forEach(System.out::println);

                case 2 -> addUserUI(admin);

                case 3 -> removeUserUI(admin);

                case 4 -> viewRequests(admin);

                case 5 -> approveRequest(admin);

                case 6 -> rejectRequest(admin);

                case 7 -> admin.viewLogs()
                        .forEach(System.out::println);
                
                case 8 -> createCourseUI();
                case 9 -> removeCourseUI(admin);

                default -> System.out.println("Invalid choice!");
            }

            pause();
        }
    }

    private static void showManagerMenu(Manager manager) {
        while (true) {
        	System.out.println("\n--- Manager Menu ---");
        	System.out.println("1. View All Students");
        	System.out.println("2. View All Teachers");
        	System.out.println("3. Assign Teacher to Course");
        	System.out.println("4. Manage Course Registration");
        	System.out.println("5. Manage News");
        	System.out.println("6. Create Report");
        	System.out.println("0. Back");

            int choice = readInt();
            if (choice == 0) return;

            switch (choice) {

            case 1 -> {
                manager.viewAllStudents(null)
                        .forEach(System.out::println);
            }

            case 2 -> {
                manager.viewAllTeachers()
                        .forEach(System.out::println);
            }

            case 3 -> {
                List<Course> courses = Database.getInstance().getCourses();
                List<Teacher> teachers = manager.viewAllTeachers();

                System.out.println("=== Courses ===");
                for (int i = 0; i < courses.size(); i++) {
                    System.out.println((i + 1) + ". " + courses.get(i));
                }

                System.out.print("Select course: ");
                int cIndex = readInt() - 1;

                System.out.println("=== Teachers ===");
                for (int i = 0; i < teachers.size(); i++) {
                    System.out.println((i + 1) + ". " + teachers.get(i));
                }

                System.out.print("Select teacher: ");
                int tIndex = readInt() - 1;

                if (cIndex >= 0 && tIndex >= 0) {
                    manager.assignTeacher(courses.get(cIndex), teachers.get(tIndex));
                    System.out.println("Teacher assigned!");
                }
            }

            case 4 -> {
                List<Course> courses = Database.getInstance().getCourses();

                System.out.println("\n=== Course Registration Control ===");
                System.out.println("1. Open Course");
                System.out.println("2. Close Course");
                System.out.println("3. View Status");
                System.out.print("Choose option: ");

                int action = readInt();

                switch (action) {

                    case 1 -> {
                        System.out.println("\n=== CLOSED COURSES ===");
                        List<Course> closed = new ArrayList<>();

                        for (Course c : courses) {
                            if (!c.isOpenForRegistration()) {
                                closed.add(c);
                            }
                        }

                        if (closed.isEmpty()) {
                            System.out.println("No closed courses.");
                            break;
                        }

                        for (int i = 0; i < closed.size(); i++) {
                            System.out.println((i + 1) + ". " + closed.get(i));
                        }

                        System.out.print("Select course to OPEN: ");
                        int index = readInt() - 1;

                        if (index >= 0 && index < closed.size()) {
                            manager.openCourse(closed.get(index));
                            System.out.println("Course opened!");
                        }
                    }

                    case 2 -> {
                        System.out.println("\n=== OPEN COURSES ===");
                        List<Course> open = new ArrayList<>();

                        for (Course c : courses) {
                            if (c.isOpenForRegistration()) {
                                open.add(c);
                            }
                        }

                        if (open.isEmpty()) {
                            System.out.println("No open courses.");
                            break;
                        }

                        for (int i = 0; i < open.size(); i++) {
                            System.out.println((i + 1) + ". " + open.get(i));
                        }

                        System.out.print("Select course to CLOSE: ");
                        int index = readInt() - 1;

                        if (index >= 0 && index < open.size()) {
                            manager.closeCourse(open.get(index));
                            System.out.println("Course closed!");
                        }
                    }

                    case 3 -> {
                        System.out.println("\n=== ALL COURSES STATUS ===");

                        for (Course c : courses) {
                            String status = c.isOpenForRegistration()
                                    ? "OPEN "
                                    : "CLOSED ";

                            System.out.println(c.getName() + " → " + status);
                        }
                    }

                    default -> System.out.println("Invalid option!");
                }
            }

            case 5 -> {
                System.out.print("Enter news: ");
                String news = scanner.nextLine();
                manager.addNews(news);
                System.out.println("News added!");
            }

            case 6 -> {
                System.out.println(manager.createReport());
            }

            case 0 -> {
                return;
            }

            default -> System.out.println("Invalid choice!");
        }

        pause();
        }
    }

    private static void showTeacherMenu(Teacher teacher) {

        while (true) {

            System.out.println("\n--- Teacher Menu ---");
            System.out.println("1. My Courses");
            System.out.println("2. View Students of Course");
            System.out.println("3. Put Marks");
            System.out.println("4. Generate Mark Report");
            System.out.println("5. My Research Papers");
            System.out.println("6. My Rating");
            System.out.println("7. Assign Course to Me");
            System.out.println("8. Add Citation to Paper");
            System.out.println("9. Add Research Paper");
            System.out.println("10. Manage Attendance");
            System.out.println("0. Back");

            int choice = readInt();

            if (choice == 0) return;

            switch (choice) {

                case 1 -> {
                    if (teacher.getCourses().isEmpty()) {
                        System.out.println("No assigned courses.");
                    } else {
                        teacher.getCourses()
                                .forEach(System.out::println);
                    }
                }

                case 2 -> {

                    List<Course> courses = teacher.getCourses();

                    if (courses.isEmpty()) {
                        System.out.println("No courses assigned.");
                        break;
                    }

                    System.out.println("=== My Courses ===");

                    for (int i = 0; i < courses.size(); i++) {
                        System.out.println((i + 1) + ". "
                                + courses.get(i));
                    }

                    System.out.print("Select course: ");
                    int cIndex = readInt() - 1;

                    if (cIndex >= 0 && cIndex < courses.size()) {

                        List<Student> students =
                                teacher.viewStudents(courses.get(cIndex));

                        if (students.isEmpty()) {
                            System.out.println("No students.");
                        } else {
                            students.forEach(System.out::println);
                        }
                    }
                }

                case 3 -> {

                    List<Course> courses = teacher.getCourses();

                    if (courses.isEmpty()) {
                        System.out.println("No assigned courses.");
                        break;
                    }

                    System.out.println("=== Courses ===");

                    for (int i = 0; i < courses.size(); i++) {
                        System.out.println((i + 1) + ". "
                                + courses.get(i));
                    }

                    System.out.print("Select course: ");
                    int cIndex = readInt() - 1;

                    if (cIndex < 0 || cIndex >= courses.size()) {
                        System.out.println("Invalid choice.");
                        break;
                    }

                    Course selectedCourse = courses.get(cIndex);

                    List<Student> students =
                            selectedCourse.getEnrolledStudents();

                    if (students.isEmpty()) {
                        System.out.println("No students.");
                        break;
                    }

                    System.out.println("=== Students ===");

                    for (int i = 0; i < students.size(); i++) {
                        System.out.println((i + 1)
                                + ". "
                                + students.get(i));
                    }

                    System.out.print("Select student: ");
                    int sIndex = readInt() - 1;

                    if (sIndex < 0 || sIndex >= students.size()) {
                        System.out.println("Invalid student.");
                        break;
                    }

                    Student selectedStudent =
                            students.get(sIndex);

                    try {

                        System.out.print("First Attestation (0-100): ");
                        double att1 =
                                Double.parseDouble(scanner.nextLine());

                        System.out.print("Second Attestation (0-100): ");
                        double att2 =
                                Double.parseDouble(scanner.nextLine());

                        System.out.print("Final Exam (0-100): ");
                        double finalExam =
                                Double.parseDouble(scanner.nextLine());

                        Mark mark = new Mark(
                                UUID.randomUUID().toString(),
                                selectedStudent,
                                selectedCourse,
                                att1,
                                att2,
                                finalExam
                        );

                        teacher.putMark(
                                selectedStudent,
                                selectedCourse,
                                mark
                        );

                        System.out.println("Mark added!");
                        System.out.println(mark);

                    } catch (Exception e) {
                        System.out.println(
                                "Error: " + e.getMessage()
                        );
                    }
                }

                case 4 -> {

                    List<Course> courses =
                            teacher.getCourses();

                    if (courses.isEmpty()) {
                        System.out.println("No courses.");
                        break;
                    }

                    System.out.println("=== Courses ===");

                    for (int i = 0; i < courses.size(); i++) {
                        System.out.println((i + 1)
                                + ". "
                                + courses.get(i));
                    }

                    System.out.print("Select course: ");
                    int cIndex = readInt() - 1;

                    if (cIndex >= 0
                            && cIndex < courses.size()) {

                        System.out.println(
                                teacher.generateMarkReport(
                                        courses.get(cIndex)
                                )
                        );
                    }
                }

                case 5 -> {

                    if (teacher.getResearchPapers().isEmpty()) {
                        System.out.println("No research papers.");
                    } else {
                        teacher.printPapers(
                                ResearchPaper.byCitationsDesc()
                        );
                    }
                }

                case 6 -> {
                    System.out.println(
                            "Rating: "
                                    + teacher.getRating()
                    );

                    System.out.println(
                            "Votes: "
                                    + teacher.getRatingCount()
                    );
                }
                case 7 -> assignCourseToTeacher(teacher);
                case 8 -> addCitationUI(teacher);
                case 9 -> addResearchPaperUI(teacher);
                case 10 -> manageAttendanceUI(teacher);

                default -> System.out.println("Invalid choice!");
            }

            pause();
        }
    }

    private static void showStudentMenu(Student student) {
        while (true) {

            System.out.println("\n--- Student Menu ---");
            System.out.println("1. My Courses");
            System.out.println("2. Register for Course");
            System.out.println("3. My Marks");
            System.out.println("4. My Transcript");
            System.out.println("5. Request Research Supervisor");
            System.out.println("6. My Research Papers");
            System.out.println("7. My Notifications");
            System.out.println("8. Attendance Report");
            System.out.println("0. Back");

            int choice = readInt();

            if (choice == 0) return;

            switch (choice) {

                case 1 -> {
                    if (student.viewCourses().isEmpty()) {
                        System.out.println("No registered courses.");
                    } else {
                        student.viewCourses().forEach(System.out::println);
                    }
                }

                case 2 -> {

                    List<Course> courses = getAvailableCourses();

                    if (courses.isEmpty()) {
                        System.out.println("No available courses (all full or closed).");
                        break;
                    }

                    System.out.println("=== Available Courses ===");

                    for (int i = 0; i < courses.size(); i++) {
                        Course c = courses.get(i);

                        System.out.println((i + 1) + ". " + c.getName()
                                + " | " + c.getCourseId()
                                + " | " + c.getEnrolledStudents().size() + "/30");
                    }

                    System.out.print("Select course: ");
                    int choice1 = readInt() - 1;

                    if (choice1 >= 0 && choice1 < courses.size()) {

                        Course selected = courses.get(choice1);

                        try {

                            student.registerCourse(selected);

                            System.out.println("Successfully registered!");

                        } catch (Exception e) {

                            System.out.println("Failed: " + e.getMessage());
                        }
                    }
                }

                case 3 -> {
                    if (student.viewMarks().isEmpty()) {
                        System.out.println("No marks yet.");
                    } else {
                        student.viewMarks().forEach(System.out::println);
                    }
                }

                case 4 -> System.out.println(student.viewTranscript());

                
                case 5 -> requestSupervisor(student);

                case 6 ->
                        student.printPapers(ResearchPaper.byDateDesc());

                case 7 -> {
                    List<String> notifications = student.getNotifications();
                    if (notifications == null || notifications.isEmpty()) {
                        System.out.println("No new notifications.");
                    } else {
                        System.out.println("\n=== My Notifications ===");
                        for (int i = 0; i < notifications.size(); i++) {
                            System.out.println((i + 1) + ". [News] " + notifications.get(i));
                        }
                    }
                }
                case 8 -> showAttendanceReportUI(student);

                default -> System.out.println("Invalid choice!");
            }

            pause();
        }
    }

    private static void manageAttendanceUI(Teacher teacher) {
        List<Course> courses = teacher.getCourses();
        if (courses == null || courses.isEmpty()) {
            System.out.println("No courses assigned to you.");
            return;
        }

        System.out.println("\n=== Select Course for Attendance ===");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i).getName() + " (" + courses.get(i).getCode() + ")");
        }
        System.out.print("Select course (0 to cancel): ");
        int courseIdx = readInt() - 1;
        if (courseIdx < 0 || courseIdx >= courses.size()) {
            return;
        }

        Course selectedCourse = courses.get(courseIdx);
        List<Lesson> lessons = selectedCourse.getLessons();

        while (true) {
            System.out.println("\n=== Lessons for " + selectedCourse.getName() + " ===");
            if (lessons == null || lessons.isEmpty()) {
                System.out.println("No lessons created yet.");
            } else {
                for (int i = 0; i < lessons.size(); i++) {
                    Lesson lesson = lessons.get(i);
                    int markedCount = 0;
                    if (lesson.getAttendance() != null) {
                        markedCount = lesson.getAttendance().size();
                    }
                    System.out.println((i + 1) + ". " + lesson.getType() + 
                                       " | Room: " + lesson.getRoom() + 
                                       " | Date: " + new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm").format(lesson.getDate()) +
                                       " | Attendance: " + markedCount + "/" + selectedCourse.getStudentCount());
                }
            }

            System.out.println("\nSelect lesson number to mark/edit attendance,");
            System.out.println("'N' to create a new lesson, or '0' to go back.");
            System.out.print("Your choice: ");
            String input = scanner.nextLine().trim();

            if (input.equals("0")) {
                break;
            }

            if (input.equalsIgnoreCase("N")) {
                // Create a new lesson
                System.out.println("\n--- Create New Lesson ---");
                System.out.println("1. Lecture");
                System.out.println("2. Practice");
                System.out.print("Select type: ");
                int typeChoice = readInt();
                LessonType type;
                int duration;
                if (typeChoice == 1) {
                    type = LessonType.LECTURE;
                    duration = LessonType.LECTURE.getDefaultDuration();
                } else if (typeChoice == 2) {
                    type = LessonType.PRACTICE;
                    duration = LessonType.PRACTICE.getDefaultDuration();
                } else {
                    System.out.println("Invalid lesson type choice.");
                    continue;
                }

                System.out.print("Enter Room: ");
                String room = scanner.nextLine().trim();
                if (room.isEmpty()) {
                    System.out.println("Room cannot be empty.");
                    continue;
                }

                String lessonId = "L" + UUID.randomUUID().toString().substring(0, 8);
                Lesson newLesson = new Lesson(lessonId, selectedCourse, type, new Date(), room, teacher, duration);
                selectedCourse.addLesson(newLesson);
                System.out.println("Lesson created successfully!");

                System.out.print("Mark attendance for this new lesson now? (y/n): ");
                String ans = scanner.nextLine().trim();
                if (ans.equalsIgnoreCase("y")) {
                    markLessonAttendance(newLesson, selectedCourse);
                }
                Database.getInstance().save();
            } else {
                try {
                    int lessonIdx = Integer.parseInt(input) - 1;
                    if (lessonIdx >= 0 && lessonIdx < lessons.size()) {
                        Lesson selectedLesson = lessons.get(lessonIdx);
                        markLessonAttendance(selectedLesson, selectedCourse);
                        Database.getInstance().save();
                    } else {
                        System.out.println("Invalid lesson number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid option.");
                }
            }
        }
    }

    private static void markLessonAttendance(Lesson lesson, Course course) {
        List<Student> enrolled = course.getEnrolledStudents();
        if (enrolled == null || enrolled.isEmpty()) {
            System.out.println("No students enrolled in this course.");
            return;
        }

        System.out.println("\n--- Mark Attendance for " + lesson.getType() + " ---");
        for (Student student : enrolled) {
            while (true) {
                System.out.print("Is " + student.getFullName() + " (" + student.getUserId() + ") present? (y/n): ");
                String response = scanner.nextLine().trim().toLowerCase();
                if (response.equals("y")) {
                    lesson.markAttendance(student, true);
                    break;
                } else if (response.equals("n")) {
                    lesson.markAttendance(student, false);
                    break;
                } else {
                    System.out.println("Please enter 'y' for present or 'n' for absent.");
                }
            }

            // Update the student's marks in the transcript for any attendance-triggered retakes
            if (student.viewTranscript() != null && student.viewTranscript().getMarks() != null) {
                double attendancePercent = course.getAttendancePercentage(student);
                if (attendancePercent < 70.0) {
                    System.out.println("⚠️ Warning: " + student.getFullName() + "'s attendance is low (" + 
                                       String.format("%.1f", attendancePercent) + "%). Retake required!");
                }
                for (Mark m : student.viewTranscript().getMarks()) {
                    if (m.getCourse() != null && m.getCourse().getCourseId().equals(course.getCourseId())) {
                        m.checkRetake(attendancePercent);
                    }
                }
            }
        }
        System.out.println("Attendance updated successfully!");
    }

    private static void showAttendanceReportUI(Student student) {
        List<Course> enrolled = student.viewCourses();
        if (enrolled == null || enrolled.isEmpty()) {
            System.out.println("You are not registered in any courses.");
            return;
        }

        System.out.println("\n================================ ATTENDANCE REPORT ================================");
        System.out.printf("%-10s | %-25s | %-12s | %-12s | %-12s | %-10s\n", 
                          "Course ID", "Course Name", "Total Lessons", "Present", "Absent", "Status");
        System.out.println("----------------------------------------------------------------------------------");

        for (Course course : enrolled) {
            List<Lesson> lessons = course.getLessons();
            int totalLessons = 0;
            int present = 0;
            int absent = 0;

            if (lessons != null) {
                for (Lesson lesson : lessons) {
                    if (lesson.getAttendance() != null && lesson.getAttendance().containsKey(student)) {
                        totalLessons++;
                        if (lesson.getAttendance().get(student)) {
                            present++;
                        } else {
                            absent++;
                        }
                    }
                }
            }

            double percent = course.getAttendancePercentage(student);
            String status = percent >= 70.0 ? "OK" : "RETAKE REQUIRED";

            System.out.printf("%-10s | %-25s | %-12d | %-12d | %-12d | %-10s (%.1f%%)\n", 
                              course.getCourseId(), 
                              course.getName().length() > 25 ? course.getName().substring(0, 22) + "..." : course.getName(), 
                              totalLessons, 
                              present, 
                              absent, 
                              status, 
                              percent);
        }
        System.out.println("==================================================================================");
    }

    private static int readInt() {
        System.out.print("Enter your choice: ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            return -1;
        }
    }

    private static void clearScreen() {
        for (int i = 0; i < 30; i++) System.out.println();
    }
}