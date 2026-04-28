package core;

import java.util.*;

/**
 * 
 */
public abstract class Employee extends User {

    /**
     * Default constructor
     */
    public Employee() {
    }

    /**
     * 
     */
    private double salary;

    /**
     * 
     */
    private Date hireDate;





    /**
     * @return
     */
    public double getSalary() {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @param to 
     * @param text 
     * @return
     */
    public void sendMessage(Employee to, String text) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public List<Message> getMessages() {
        // TODO implement here
        return null;
    }

    /**
     * @param to 
     * @param text 
     * @return
     */
    public void sendComplaint(Manager to, String text) {
        // TODO implement here
        return null;
    }

}