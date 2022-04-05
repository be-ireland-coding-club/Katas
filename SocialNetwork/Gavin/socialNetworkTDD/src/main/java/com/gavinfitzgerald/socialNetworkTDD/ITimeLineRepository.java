package com.gavinfitzgerald.socialNetworkTDD;

import java.util.List;

public interface ITimeLineRepository {
    public List<Message> getTimeline(String user);
    public void addMessage(String user, String m);
}
