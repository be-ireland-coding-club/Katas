package com.gavinfitzgerald.socialNetworkTDD;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {

    private final String DATE_PATTERN = "yyyy-MM-dd HH-mm";
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
    private final String message;
    private final String user;
    private final Date timestamp;

    public Message(String user, String message, Date timestamp) {
        this.user = user;
        this.message = message;
        this.timestamp = timestamp;
    }

    private String getMessage() {
        return message;
    }

    private String getUser() {
        return user;
    }

    private Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return getUser()+", "+dateFormat.format(getTimestamp()).toString()+": "+getMessage()+"\n";
    }
}
