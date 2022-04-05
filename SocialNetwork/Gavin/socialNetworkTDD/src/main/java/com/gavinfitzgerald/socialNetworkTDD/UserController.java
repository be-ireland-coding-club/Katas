package com.gavinfitzgerald.socialNetworkTDD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserController {

    @Autowired
    private ITimeLineRepository timeLineRepository;

    public void post(String user, String message){
        timeLineRepository.addMessage(user, message);
    }

    public List<Message> getTimeline(String user) {
        return timeLineRepository.getTimeline(user);
    }
}
