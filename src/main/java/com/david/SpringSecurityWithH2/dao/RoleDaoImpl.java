package com.david.SpringSecurityWithH2.dao;

import com.david.SpringSecurityWithH2.entity.Role;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
@Repository
public class RoleDaoImpl implements RoleDao{
    private final EntityManager entityManager;

    @Autowired
    public RoleDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    public Role findRoleByRoleName(String theRoleName) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Role> theQuery = currentSession.createQuery("from Role where name=:roleName",Role.class);

        theQuery.setParameter("roleName",theRoleName);

        Role theRole = null;

        try{
            theRole = theQuery.getSingleResult();
        }catch (Exception e){
            theRole = null;
        }

        return theRole;
    }
}
