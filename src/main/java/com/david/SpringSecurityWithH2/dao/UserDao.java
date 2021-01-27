package com.david.SpringSecurityWithH2.dao;

import com.david.SpringSecurityWithH2.entity.Subscription;
import com.david.SpringSecurityWithH2.entity.User;
import com.david.SpringSecurityWithH2.entity.UserData;

import java.util.List;

public interface UserDao {
    User findUserByUsername(String username);
    void saveMember(User user);
    void saveTrainer(User user);
    User findMemberById(Long userId);
    Boolean subscribeMemberToPackage(Long sub_id,String username);
}
