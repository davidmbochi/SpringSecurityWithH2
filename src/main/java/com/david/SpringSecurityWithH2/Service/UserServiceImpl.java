package com.david.SpringSecurityWithH2.Service;

import com.david.SpringSecurityWithH2.User.MemberUser;
import com.david.SpringSecurityWithH2.User.TrainerUser;
import com.david.SpringSecurityWithH2.dao.RoleDao;
import com.david.SpringSecurityWithH2.dao.SubscriptionDao;
import com.david.SpringSecurityWithH2.dao.UserDao;
import com.david.SpringSecurityWithH2.entity.Role;
import com.david.SpringSecurityWithH2.entity.Subscription;
import com.david.SpringSecurityWithH2.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserDao userDao;

    private final RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final SubscriptionDao subscriptionDao;

    @Autowired
    public UserServiceImpl(UserDao userDao,
                           RoleDao roleDao,
                           SubscriptionDao subscriptionDao){
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.subscriptionDao = subscriptionDao;
    }

    @Override
    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    @Override
    public void saveMember(MemberUser memberUser) {
        User user = new User();
        user.setUserId(memberUser.getId());
        user.setFirstName(memberUser.getFirstName());
        user.setLastName(memberUser.getLastName());
        user.setEmail(memberUser.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(memberUser.getPassword()));
        user.setUsername(memberUser.getUsername());
        Role theRole = roleDao.findRoleByRoleName("ROLE_MEMBER");
        logger.info("the following role was retrieved "+theRole.getName());
        user.setRoles(Arrays.asList(theRole));
        userDao.saveMember(user);
    }

    @Override
    public void saveTrainer(TrainerUser trainerUser) {
        User user = new User();
        user.setFirstName(trainerUser.getFirstName());
        user.setLastName(trainerUser.getLastName());
        user.setEmail(trainerUser.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(trainerUser.getPassword()));
        user.setUsername(trainerUser.getUsername());
        Role theRole = roleDao.findRoleByRoleName("ROLE_TRAINER");
        logger.info("The following role was retrieved "+theRole.getName());
        user.setRoles(Arrays.asList(theRole));
        userDao.saveTrainer(user);
    }

    @Override
    public User findMemberById(Long userId) {
        return userDao.findMemberById(userId);
    }

    @Override
    public void memberSubscription(Long id, Subscription subscription) {
        User theUser = userDao.findMemberById(id);
        logger.info("The user retrieved is "+theUser.getUsername());

        Subscription theSubscription = subscriptionDao.findSubscriptionById(subscription.getId());

        logger.info("The subscription retrieved is "+theSubscription.getDescription());

        if ((theUser != null)&&(theSubscription != null)){
            theUser.setSubscription(theSubscription);
            subscription.setUser(theUser);
            userDao.saveMember(theUser);
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException("username does not exist");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),mapRolesToAuthority(user.getRoles()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthority(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
