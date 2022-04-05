package com.gavinfitzgerald.socialNetworkTDD;

public class MessageTooLongException extends Exception {
    public MessageTooLongException(String errorMessage) {
        super(errorMessage);
    }
}
