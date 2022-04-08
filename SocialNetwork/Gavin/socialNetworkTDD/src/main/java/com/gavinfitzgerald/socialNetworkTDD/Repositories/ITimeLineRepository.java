package com.gavinfitzgerald.socialNetworkTDD.Repositories;

import com.gavinfitzgerald.socialNetworkTDD.DTOs.Message;

import java.util.List;

public interface ITimeLineRepository {
    public List<Message> getTimeline(String user);
    public List<Message> getTimeline(String user, List<String> subscriptions);
    public void addMessage(Message message);
}
