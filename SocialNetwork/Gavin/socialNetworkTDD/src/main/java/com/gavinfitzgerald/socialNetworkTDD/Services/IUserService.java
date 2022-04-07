package com.gavinfitzgerald.socialNetworkTDD.Services;

import com.gavinfitzgerald.socialNetworkTDD.DTOs.Message;
import com.gavinfitzgerald.socialNetworkTDD.DTOs.MessageTooLongException;

import java.util.List;

public interface IUserService {
    void post(Message message) throws MessageTooLongException;
    List<Message> getTimeline(String userName);
    List<Message> getTimelineWithSubscriptions(String userName, List<String> subscriptions);
    void subscribes(List<String> subscriptions, String user);
    List<String> getSubscriptions(String user);
}
