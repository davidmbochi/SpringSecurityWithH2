package com.david.SpringSecurityWithH2.Service;

import com.david.SpringSecurityWithH2.entity.Subscription;

import java.util.Collection;

public interface SubscriptionService {
    void saveSubscription(Subscription subscription);
    Subscription findSubscriptionByName(String name);
    Collection<Subscription> findAllSubscriptions();
}
