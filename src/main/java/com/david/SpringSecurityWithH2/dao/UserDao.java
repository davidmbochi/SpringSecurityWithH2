package com.david.SpringSecurityWithH2.dao;

import com.david.SpringSecurityWithH2.entity.User;

public interface UserDao {
    User findUserByUsername(String username);
    void saveMember(User user);
    void saveTrainer(User user);
    User findMemberById(Long userId);
}
