package com.david.SpringSecurityWithH2.Service;

import com.david.SpringSecurityWithH2.User.MemberUser;
import com.david.SpringSecurityWithH2.User.TrainerUser;
import com.david.SpringSecurityWithH2.entity.Subscription;
import com.david.SpringSecurityWithH2.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findUserByUsername(String username);
    void saveMember(MemberUser memberUser);
    void saveTrainer(TrainerUser trainerUser);
    User findMemberById(Long userId);
    void memberSubscription(Long id, Subscription subscription);
}
