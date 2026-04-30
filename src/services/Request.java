package services;

import core.Employee;
import enums.RequestStatus;

import java.util.*;

public class Request {
    private static final long serialVersionUID = 5L;

    private String requestId;
    private Employee sender;
    private String description;
    private RequestStatus status;
    private Date date;

    public Request(Employee sender, String description) {
        this.requestId = UUID.randomUUID().toString();
        this.sender = sender;
        this.description = description;
        this.status = RequestStatus.PENDING;
        this.date = new Date();
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public Employee getSender() {
        return sender;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return String.format("Request[ID: %s, From: %s, Status: %s, Date: %s]\nDescription: %s",
                requestId, sender.getFullName(), status, date, description);
    }

}