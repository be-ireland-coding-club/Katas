package com.gavinfitzgerald.socialNetworkTDD.Repositories;

import com.gavinfitzgerald.socialNetworkTDD.Message;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("InMemoryTimeLineRepositoryImpl")
public class InMemoryTimeLineRepositoryImpl implements ITimeLineRepository {
    private static HashMap<String, List<Message>> timelines = new HashMap<String, List<Message>>();

    public List<Message> getTimeline(String user) {
        return getTimeline(user, new ArrayList<>());
    }

    public List<Message> getTimeline(String user, List<String> subscriptions) {
        List<Message> personalTimeline = timelines.get(user);
        return (personalTimeline != null) ? joinPersonalTimelineWithSubscriptions(personalTimeline, subscriptions) : null;
    }

    private List<Message> joinPersonalTimelineWithSubscriptions(List<Message> personalTimeline, List<String> subscriptions) {
        List<Message> combinedTimeline = personalTimeline;
        for (String subscription: subscriptions) {
            List<Message> subscriptionTimeline = timelines.get(subscription);
            combinedTimeline = Stream.concat(combinedTimeline.stream(), subscriptionTimeline.stream())
                    .collect(Collectors.toList());
        }
        Collections.sort(combinedTimeline);
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
