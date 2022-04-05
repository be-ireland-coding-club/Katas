package com.gavinfitzgerald.socialNetworkTDD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserController {

    private final int MAX_MESSAGE_LENGTH = 500;

    @Autowired
    private ITimeLineRepository timeLineRepository;

    @Autowired
    private IFollowersRepository followersRepository;

    public void post(String user, String message) throws MessageTooLongException{
        if(message.length() > MAX_MESSAGE_LENGTH){
            throw new MessageTooLongException("Message too long. "+MAX_MESSAGE_LENGTH+" is the maximum number of characters.");
        }
        timeLineRepository.addMessage(user, message);
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

    public void subscribes(List<String> followers, String leader){
        followersRepository.follow(followers, leader);
    }

    public void getFollowers(String leader){
        followersRepository.getFollowers(leader);
    }
}
