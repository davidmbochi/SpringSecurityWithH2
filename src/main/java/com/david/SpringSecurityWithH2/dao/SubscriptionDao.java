package com.david.SpringSecurityWithH2.dao;

import com.david.SpringSecurityWithH2.entity.Subscription;

import java.util.Collection;

public interface SubscriptionDao {
    void saveSubscription(Subscription subscription);
    Subscription findSubscriptionById(Long id);
    Collection<Subscription> findAllSubscriptions();
}
