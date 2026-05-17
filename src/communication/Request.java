package communication;

import core.User;
import enums.RequestStatus;
import enums.RequestType;
import users.Teacher;

import java.util.*;

public class Request {
    private static final long serialVersionUID = 5L;

    private String requestId;
    private User sender;
    private String description;
    private RequestStatus status;
    private Date date;
    private RequestType requestType; // GENERAL, SUPERVISOR
    private Teacher supervisorCandidate; // for SUPERVISOR requests

    public Request(User sender, String description) {
        this.requestId = UUID.randomUUID().toString();
        this.sender = sender;
        this.description = description;
        this.status = RequestStatus.PENDING;
        this.date = new Date();
        this.requestType = RequestType.GENERAL;
        this.supervisorCandidate = null;
    }

    public Request(User sender, String description, RequestType requestType, Teacher supervisorCandidate) {
        this.requestId = UUID.randomUUID().toString();
        this.sender = sender;
        this.description = description;
        this.status = RequestStatus.PENDING;
        this.date = new Date();
        this.requestType = requestType;
        this.supervisorCandidate = supervisorCandidate;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public User getSender() {
        return sender;
    }

    public String getDescription() {
        return description;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public Teacher getSupervisorCandidate() {
        return supervisorCandidate;
    }

    public String getRequestId() {
        return requestId;
    }

    public String toString() {
        String baseInfo = String.format("Request[ID: %s, From: %s, Status: %s, Date: %s]\nDescription: %s",
                requestId, sender.getFullName(), status, date, description);

        if (RequestType.SUPERVISOR == requestType && supervisorCandidate != null) {
            baseInfo += "\nType: SUPERVISOR REQUEST\nRequested Supervisor: " + supervisorCandidate.getFullName();
        }

        return baseInfo;
    }

}