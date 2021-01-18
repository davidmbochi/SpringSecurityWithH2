package com.david.SpringSecurityWithH2.dao;

import com.david.SpringSecurityWithH2.entity.Subscription;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;


@Repository
public class SubscriptionDaoImpl implements SubscriptionDao{

    private final EntityManager entityManager;

    public SubscriptionDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void saveSubscription(Subscription subscription) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.save(subscription);
    }

    @Override
    public Subscription findSubscriptionById(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Subscription> theQuery = currentSession.createQuery("from Subscription where id=:theId",Subscription.class);

        theQuery.setParameter("theId",id);

        Subscription theSubscription = null;

        try {
            theSubscription= theQuery.getSingleResult();
        }catch (Exception e){
            theSubscription = null;
        }

        return theSubscription;
    }

    @Override
    public Collection<Subscription> findAllSubscriptions() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Subscription> theQuery = currentSession.createQuery("from Subscription",Subscription.class);

        List<Subscription> subscriptionList = theQuery.getResultList();

        return subscriptionList;
    }
}
