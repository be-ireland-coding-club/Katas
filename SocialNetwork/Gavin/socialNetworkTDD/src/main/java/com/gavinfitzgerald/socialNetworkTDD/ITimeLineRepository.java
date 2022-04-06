package com.gavinfitzgerald.socialNetworkTDD;

import java.util.List;

public interface ITimeLineRepository {
    public List<Message> getTimeline(String user);
    public List<Message> getTimeline(String user, List<String> subscriptions);
    public void addMessage(String user, String m);
}
