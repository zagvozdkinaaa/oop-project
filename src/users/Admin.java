package users;

import core.Database;
import core.Employee;
import core.User;
import enums.UserRole;
import enums.RequestStatus;
import communication.Request;
import java.util.ArrayList;
import java.util.List;

public class Admin extends Employee {
    private static final long serialVersionUID = 7L;

    private List<String> logs;

    public Admin(String id, String firstName, String lastName, String email, String password, double salary) {
        super(id, firstName, lastName, email, password, UserRole.ADMIN, salary);
        this.logs = new ArrayList<>();
    }

    public void addUser(User user) {
        if (user == null) return;

        for (User u : Database.getInstance().getUsers()) {
            if (u.getUserId().equals(user.getUserId())) {
                System.out.println(" User with ID already exists: " + user.getUserId());
                return; 
            }
        }

        Database.getInstance().getUsers().add(user);
        System.out.println("✓ Added: " + user.getFullName());
        Database.getInstance().save();
    }
    public void removeUser(User user) {
        if (user != null) {
            Database.getInstance().removeUser(user.getUserId());
            logs.add("Removed user: " + user.getUserId());
        }
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
        return Database.getInstance().getUsers();
    }

    public List<String> viewLogs() {
        return logs;
    }

    public List<Request> viewSupervisorRequests() {
        List<Request> allRequests = Database.getInstance().getRequests();
        List<Request> supervisorReqs = new ArrayList<>();
        if (allRequests != null) {
            for (Request req : allRequests) {
                if ("SUPERVISOR".equals(req.getRequestType()) && req.getStatus() == RequestStatus.PENDING) {
                    supervisorReqs.add(req);
                }
            }
        }
        return supervisorReqs;
    }

    public void approveSupervisorRequest(Request req) {
        if (req != null && "SUPERVISOR".equals(req.getRequestType())) {
            try {
                if (req.getSender() instanceof Student) {
                    Student student = (Student) req.getSender();
                    student.assignSupervisor(req.getSupervisorCandidate());
                    req.setStatus(RequestStatus.APPROVED);
                    logs.add("Approved supervisor request for student: " + student.getUserId());
                }
            } catch (exceptions.LowHIndexException e) {
                System.err.println("Cannot approve: " + e.getMessage());
                req.setStatus(RequestStatus.REJECTED);
                logs.add("Rejected supervisor request (Low H-Index) for student: " + req.getSender().getUserId());
            } catch (Exception e) {
                System.err.println("Error approving request: " + e.getMessage());
            }
        }
    }

    public void rejectSupervisorRequest(Request req) {
        if (req != null && "SUPERVISOR".equals(req.getRequestType())) {
            req.setStatus(RequestStatus.REJECTED);
            logs.add("Rejected supervisor request for user: " + req.getSender().getUserId());
        }
    }
}
