package com.gavinfitzgerald.socialNetworkTDD.Services;

import com.gavinfitzgerald.socialNetworkTDD.Message;
import com.gavinfitzgerald.socialNetworkTDD.MessageTooLongException;

import java.util.List;

public interface IUserService {
    void post(String user, String message) throws MessageTooLongException;
    List<Message> getTimeline(String userName);
    List<Message> getTimelineWithSubscriptions(String userName, List<String> subscriptions);
    void subscribes(List<String> subscriptions, String user);
}
