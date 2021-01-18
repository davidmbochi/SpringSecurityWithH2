package com.david.SpringSecurityWithH2.controller;

import com.david.SpringSecurityWithH2.Service.UserService;
import com.david.SpringSecurityWithH2.User.MemberUser;
import com.david.SpringSecurityWithH2.User.TrainerUser;
import com.david.SpringSecurityWithH2.entity.Subscription;
import com.david.SpringSecurityWithH2.entity.User;
import com.david.SpringSecurityWithH2.exceptions.UserExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
public class UserController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping("/saveMember")
    public void saveUser(@RequestBody MemberUser memberUser){
        String username = memberUser.getUsername();

        User exists = userService.findUserByUsername(username);

        if (exists != null){
            throw new UserExists("user with that username already exists");
        }

        userService.saveMember(memberUser);

    }
    @PostMapping("/saveTrainer")
    public void saveTrainer(@RequestBody TrainerUser trainerUser){
        String username = trainerUser.getUsername();

        User exists = userService.findUserByUsername(username);

        if (exists != null){
            throw new UserExists("user with that username already exists");
        }

        userService.saveTrainer(trainerUser);
    }

    @PostMapping("/member/{memberId}/subscription")
    public void memberSubscription(@PathVariable("memberId") Long id,
                                   @RequestBody Subscription subscription){
        userService.memberSubscription(id,subscription);
    }
}
