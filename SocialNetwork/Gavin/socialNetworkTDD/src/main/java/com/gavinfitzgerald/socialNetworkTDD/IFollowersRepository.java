package com.gavinfitzgerald.socialNetworkTDD;

import java.util.List;

public interface IFollowersRepository {
    public void subscribe(List<String> subscriptions, String user);

    public List<String> getSubscriptions(String user);
}
