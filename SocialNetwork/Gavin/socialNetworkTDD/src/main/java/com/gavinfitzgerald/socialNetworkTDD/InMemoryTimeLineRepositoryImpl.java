package com.gavinfitzgerald.socialNetworkTDD;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component("InMemoryTimeLineRepositoryImpl")
public class InMemoryTimeLineRepositoryImpl implements ITimeLineRepository {
    private static HashMap<String, List<Message>> timelines = new HashMap<String, List<Message>>();

    public List<Message> getTimeline(String user) {
        return getTimeline(user, new ArrayList<>());
    }

    public List<Message> getTimeline(String user, List<String> subscriptions) {
        List<Message> personalTimeline = timelines.get(user);
        return joinPersonalTimelineWithSubscriptions(personalTimeline, subscriptions);
    }

    private List<Message> joinPersonalTimelineWithSubscriptions(List<Message> personalTimeline, List<String> subscriptions) {
        List<Message> combinedTimeline = personalTimeline;
        for (String subscription: subscriptions) {
            List<Message> subscriptionTimeline = timelines.get(subscription);
            combinedTimeline = Stream.concat(combinedTimeline.stream(), subscriptionTimeline.stream())
                    .collect(Collectors.toList());
        }
        return combinedTimeline;
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
