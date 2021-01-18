package com.david.SpringSecurityWithH2.Service;

import com.david.SpringSecurityWithH2.dao.SubscriptionDao;
import com.david.SpringSecurityWithH2.dao.UserDao;
import com.david.SpringSecurityWithH2.entity.Subscription;
import com.david.SpringSecurityWithH2.exceptions.InvalidDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{
    private final Logger logger = LoggerFactory.getLogger(SubscriptionService.class);
    private final SubscriptionDao subscriptionDao;
    private final UserDao userDao;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionDao subscriptionDao,UserDao userDao){
        this.subscriptionDao = subscriptionDao;
        this.userDao = userDao;
    }
    @Override
    public void saveSubscription(Subscription theSubscription) {
        Subscription subscription = new Subscription();
        subscription.setCost(theSubscription.getCost());
        subscription.setDescription(theSubscription.getDescription());
        if (theSubscription.validateDate()){
            subscription.setStartDate(theSubscription.getStartDate());
            logger.info("start date is "+subscription.getStartDate());
            subscription.setEndDate(theSubscription.getEndDate());
            logger.info("end date is "+subscription.getEndDate());
        }else{
            throw new InvalidDate("You entered an invalid date");
        }

        subscription.setDuration(theSubscription.getDuration());


        subscriptionDao.saveSubscription(subscription);

    }

    @Override
    public Subscription findSubscriptionById(Long id) {
        return subscriptionDao.findSubscriptionById(id);
    }

    @Override
    public Collection<Subscription> findAllSubscriptions() {
        return subscriptionDao.findAllSubscriptions();
    }

}
