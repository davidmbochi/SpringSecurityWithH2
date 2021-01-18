package com.david.SpringSecurityWithH2.dao;

import com.david.SpringSecurityWithH2.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


@Repository
public class UserDaoImpl implements UserDao{
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
}
