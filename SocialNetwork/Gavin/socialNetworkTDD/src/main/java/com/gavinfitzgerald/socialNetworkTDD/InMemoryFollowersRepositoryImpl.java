package com.gavinfitzgerald.socialNetworkTDD;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component("InMemoryFollowersRepositoryImpl")
public class InMemoryFollowersRepositoryImpl implements IFollowersRepository {
    private static HashMap<String, List<String>> followersDataSource = new HashMap<String, List<String>>();

    public void follow(List<String> followers, String leader) {
        if (followersDataSource.containsKey(leader)){
            for (String follower: followers) {
                followersDataSource.get(leader).add(follower);
            }
        } else {
            followersDataSource.put(leader, followers);
        }
    }

    public List<String> getFollowers(String leader) {
        return followersDataSource.get(leader);
    }
}
