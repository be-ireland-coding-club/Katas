package com.gavinfitzgerald.socialNetworkTDD;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component("InMemoryTimeLineRepositoryImpl")
public class InMemoryTimeLineRepositoryImpl implements ITimeLineRepository {
    private static HashMap<String, List<Message>> timelines = new HashMap<String, List<Message>>();

    public List<Message> getTimeline(String user) {
        return timelines.get(user);
    }


    public void addMessage(String user, String message) {
        if( timelines.containsKey(user)){
            timelines.get(user).add(new Message(user, message, new Date()));
        } else {
            List<Message> userMessageList = new ArrayList<Message>();
            userMessageList.add(new Message(user, message, new Date()));
            timelines.put(user, userMessageList);
        }
    }

    public static void deleteTimelines(){
        timelines.clear();
    }
}
