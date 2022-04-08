package com.gavinfitzgerald.socialNetworkTDD.DTOs;

import java.util.List;

public class UserSubscriptions {
    private String user;
    private List<String> subscriptions;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<String> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<String> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
