package com.david.SpringSecurityWithH2.dao;

import com.david.SpringSecurityWithH2.entity.Subscription;
import com.david.SpringSecurityWithH2.entity.User;
import com.david.SpringSecurityWithH2.entity.UserData;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


@Repository
public class UserDaoImpl implements UserDao{
    private Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    private final EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public User findUserByUsername(String username) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> theQuery = currentSession.createQuery("from User where username=:theUsername",User.class);
        theQuery.setParameter("theUsername",username);
       User theUser = null;
       try {
           theUser = theQuery.getSingleResult();
       }catch (Exception e){
           theUser = null;
       }

       return theUser;
    }

    @Override
    public void saveMember(User user) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(user);

    }

    @Override
    public void saveTrainer(User user) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.save(user);
    }

    @Override
    public User findMemberById(Long userId) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<User> theQuery = currentSession.createQuery("from User where id=:theUserId",User.class);

        theQuery.setParameter("theUserId",userId);

        User theUser = null;

        try {
            theUser = theQuery.getSingleResult();
        }catch (Exception e){
            theUser = null;
        }

        return theUser;
    }

    @Override
    public Boolean subscribeMemberToPackage(Long sub_id, String username) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery("update User set sub_id=:theSubId where username=:theUsername");

        theQuery.setParameter("theSubId",sub_id);

        theQuery.setParameter("theUsername",username);

        theQuery.executeUpdate();

        return true;
    }

}
