package com.gavinfitzgerald.socialNetworkTDD.DTOs;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements Comparable<Message> {

    private final String DATE_PATTERN = "yyyy-MM-dd HH-mm";
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
    private final String message;
    private final String user;
    private final Date timestamp;

    public Message(String user, String message) {
        this.user = user;
        this.message = message;
        this.timestamp = new Date();
    }

    public String getMessage() {
        return message;
    }

    public String getUser() {
        return user;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return getUser()+", "+dateFormat.format(getTimestamp()).toString()+": "+getMessage()+"\n";
    }

    @Override
    public int compareTo(Message o) {
        return getTimestamp().compareTo(o.getTimestamp());
    }
}
