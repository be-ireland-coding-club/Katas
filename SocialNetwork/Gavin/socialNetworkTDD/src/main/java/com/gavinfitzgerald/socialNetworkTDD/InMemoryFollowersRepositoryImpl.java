package com.gavinfitzgerald.socialNetworkTDD;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component("InMemoryFollowersRepositoryImpl")
public class InMemoryFollowersRepositoryImpl implements IFollowersRepository {
    private static HashMap<String, List<String>> followersDataSource = new HashMap<String, List<String>>();

    public void subscribe(List<String> subscriptions, String user) {
        if (followersDataSource.containsKey(user)){
            for (String userName: subscriptions) {
                followersDataSource.get(user).add(userName);
            }
        } else {
            followersDataSource.put(user, subscriptions);
        }
    }

    public List<String> getSubscriptions(String user) {
        return followersDataSource.get(user);
    }
}
