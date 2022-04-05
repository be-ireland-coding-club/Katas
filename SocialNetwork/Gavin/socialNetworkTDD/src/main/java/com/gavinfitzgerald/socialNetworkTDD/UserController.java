package com.gavinfitzgerald.socialNetworkTDD;

import java.text.SimpleDateFormat;
import java.util.*;

public class UserController {
    private static HashMap<String, List<Message>> timeline = new HashMap<String, List<Message>>();

    public void post(String user, String message){
        if( timeline.containsKey(user)){
            timeline.get(user).add(new Message(user, message, new Date()));
        } else {
            List<Message> userMessageList = new ArrayList<Message>();
            userMessageList.add(new Message(user, message, new Date()));
            timeline.put(user, userMessageList);
        }
    }

    public List<Message> getTimeline(String user) {
        return timeline.get(user);
    }
}
