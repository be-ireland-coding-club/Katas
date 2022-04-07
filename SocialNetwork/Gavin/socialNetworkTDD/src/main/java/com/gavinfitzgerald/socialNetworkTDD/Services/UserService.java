package com.gavinfitzgerald.socialNetworkTDD.Services;

import com.gavinfitzgerald.socialNetworkTDD.DTOs.Message;
import com.gavinfitzgerald.socialNetworkTDD.DTOs.MessageTooLongException;
import com.gavinfitzgerald.socialNetworkTDD.Repositories.IFollowersRepository;
import com.gavinfitzgerald.socialNetworkTDD.Repositories.ITimeLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements IUserService {

    private final int MAX_MESSAGE_LENGTH = 500;

    @Autowired
    private ITimeLineRepository timeLineRepository;

    @Autowired
    private IFollowersRepository followersRepository;

    public void post(Message message) throws MessageTooLongException {
        if(message.getMessage().length() > MAX_MESSAGE_LENGTH){
            throw new MessageTooLongException("Message too long. "+MAX_MESSAGE_LENGTH+" is the maximum number of characters.");
        }
        timeLineRepository.addMessage(message);
    }

    /**
     * Returns a users personal timeline
     *
     * @param   userName    Username to use in retrieval of timeline
     * @return              The specified users timeline or null if user not found.
     */
    public List<Message> getTimeline(String userName) {
        return timeLineRepository.getTimeline(userName);
    }

    /**
     * Returns a users personal timeline combined with subscription timelines
     *
     * @param userName Username to use in retrieval of timeline
     * @param subscriptions List of "subscribed to" users to use in retrieval of timelines
     * @return A single timeline combining the users personal timeline and their subscription timelines
     */
    public List<Message> getTimelineWithSubscriptions(String userName, List<String> subscriptions) {
        return timeLineRepository.getTimeline(userName, subscriptions);
    }

    public void subscribes(List<String> subscriptions, String user){
        followersRepository.subscribe(subscriptions, user);
    }

    public List<String> getSubscriptions(String user){
        return followersRepository.getSubscriptions(user);
    }
}