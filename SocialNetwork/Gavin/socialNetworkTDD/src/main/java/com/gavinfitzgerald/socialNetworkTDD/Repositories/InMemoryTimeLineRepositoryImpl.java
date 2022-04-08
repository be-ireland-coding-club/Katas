package com.gavinfitzgerald.socialNetworkTDD.Repositories;

import com.gavinfitzgerald.socialNetworkTDD.DTOs.Message;
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
        return joinPersonalTimelineWithSubscriptions(personalTimeline, subscriptions);
    }

    private List<Message> joinPersonalTimelineWithSubscriptions(List<Message> personalTimeline, List<String> subscriptions) {
        List<Message> combinedTimeline = (personalTimeline != null) ? personalTimeline : new ArrayList<Message>();
        for (String subscription: subscriptions) {
            List<Message> subscriptionTimeline = timelines.get(subscription);
            combinedTimeline = Stream.concat(combinedTimeline.stream(), subscriptionTimeline.stream())
                    .collect(Collectors.toList());
        }
        Collections.sort(combinedTimeline);
        return combinedTimeline;
    }

    public void addMessage(Message message) {
        if( timelines.containsKey(message.getUser())){
            timelines.get(message.getUser()).add(message);
        } else {
            List<Message> userMessageList = new ArrayList<Message>();
            userMessageList.add(message);
            timelines.put(message.getUser(), userMessageList);
        }
    }

    public static void deleteTimelines(){
        timelines.clear();
    }
}
