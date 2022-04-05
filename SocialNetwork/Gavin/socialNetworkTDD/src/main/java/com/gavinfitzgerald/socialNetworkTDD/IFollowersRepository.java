package com.gavinfitzgerald.socialNetworkTDD;

import java.util.List;

public interface IFollowersRepository {
    public void follow(List<String> follower, String leader);

    public List<String> getFollowers(String leader);
}
