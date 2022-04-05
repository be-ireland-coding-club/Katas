package com.gavinfitzgerald.socialNetworkTDD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserController{

    private final int MAX_MESSAGE_LENGTH = 500;

    @Autowired
    private ITimeLineRepository timeLineRepository;

    public void post(String user, String message) throws MessageTooLongException{
        if(message.length() > MAX_MESSAGE_LENGTH){
            throw new MessageTooLongException("Message too long. "+MAX_MESSAGE_LENGTH+" is the maximum number of characters.");
        }
        timeLineRepository.addMessage(user, message);
    }

    public List<Message> getTimeline(String user) {
        return timeLineRepository.getTimeline(user);
    }

    public void follow(String user){

    }
}
