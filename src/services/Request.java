package services;

import enums.RequestStatus;

import java.util.*;

/**
 * 
 */
public class Request {

    /**
     * Default constructor
     */
    public Request() {
    }

    /**
     * 
     */
    private String requestId;

    /**
     * 
     */
    private Employee sender;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private RequestStatus status;

    /**
     * 
     */
    private Date date;



    /**
     * @return
     */
    public RequestStatus getStatus() {
        // TODO implement here
        return null;
    }

    /**
     * @param status 
     * @return
     */
    public void setStatus(RequestStatus status) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public String toString() {
        // TODO implement here
        return "";
    }

}