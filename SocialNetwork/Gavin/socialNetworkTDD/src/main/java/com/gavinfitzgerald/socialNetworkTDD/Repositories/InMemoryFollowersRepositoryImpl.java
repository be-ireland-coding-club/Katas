package com.gavinfitzgerald.socialNetworkTDD.Repositories;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository("InMemoryFollowersRepositoryImpl")
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
