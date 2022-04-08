package com.gavinfitzgerald.socialNetworkTDD.DTOs;

public class MessageTooLongException extends Exception {
    public MessageTooLongException(String errorMessage) {
        super(errorMessage);
    }
}
