package communication;

import core.User;

import java.io.Serializable;
import java.util.*;

public class Message implements Serializable {
    private static final long serialVersionUID = 4L;

    private String messageId;
    private User from;
    private User to;
    private String text;
    private Date date;
    private boolean isRead;

    public Message(User from, User to, String text) {
        this.messageId = UUID.randomUUID().toString();
        this.from = from;
        this.to = to;
        this.text = text;
        this.date = new Date();
        this.isRead = false;
    }

    public String getText() {
        return text;
    }

    public User getSender() {
        return from;
    }

    public User getReceiver() {
        return to;
    }

    public void markAsRead() {
        this.isRead = true;
    }

    @Override
    public String toString() {
        String status = isRead ? "[Read]" : "[New]";
        return String.format("%s From: %s | Date: %s\nText: %s",
                status, from.getFullName(), date, text);
    }
}