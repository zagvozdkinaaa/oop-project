package core;

import enums.UserRole;
import services.Message;
import users.Manager;

import java.util.*;

public abstract class Employee extends User {

    private double salary;
    private Date hireDate;
    private List<Message> messages;

    public Employee(String id, String firstName, String lastName, String email, String password,
                    UserRole role, double salary) {
        super(id, firstName, lastName, email, password, role);
        this.salary = salary;
        this.hireDate = new Date();
        this.messages = new ArrayList<>();
    }

    public double getSalary() {
        return salary;
    }

    public void sendMessage(Employee to, String text) {
        Message msg = new Message(this, to, text);
        to.receiveMessage(msg);
    }

    public void receiveMessage(Message msg) {
        if (this.messages == null) {
            this.messages = new ArrayList<>();
        }
        this.messages.add(msg);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void sendComplaint(Employee to, String text) {
        if (to.getRole() == enums.UserRole.MANAGER) {
            this.sendMessage(to, "COMPLAINT: " + text);
        } else {
            throw new IllegalArgumentException("Complaints can only be sent to Managers!");
        }
    }
}