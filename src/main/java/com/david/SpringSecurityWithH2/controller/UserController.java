package com.david.SpringSecurityWithH2.controller;

import com.david.SpringSecurityWithH2.Service.UserService;
import com.david.SpringSecurityWithH2.User.MemberUser;
import com.david.SpringSecurityWithH2.User.TrainerUser;
import com.david.SpringSecurityWithH2.entity.Subscription;
import com.david.SpringSecurityWithH2.entity.User;
import com.david.SpringSecurityWithH2.entity.UserData;
import com.david.SpringSecurityWithH2.exceptions.UserExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/registerMember")
    public String registerMember(Model model){
        User user = new User();
        model.addAttribute("user",user);

        return "registerMember";

    }
    @PostMapping("/saveMember")
    public String saveUser(@Valid @ModelAttribute("user") MemberUser memberUser,
                           BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "registerMember";
        }
        String username = memberUser.getUsername();

        User exists = userService.findUserByUsername(username);

        if (exists != null){
            throw new UserExists("user with that username already exists");
        }

        userService.saveMember(memberUser);

        return "redirect:/";

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

    @GetMapping(value = "/api/v1/subscription")
    public String subscribeMemberToPackage(@RequestParam("name") String name,
                                           Model model){
        Boolean saved = userService.subscribeMemberToPackage(name);
        if (saved){
            return "redirect:/memberPackage";
        }
        return "subscriptions";
    }
    @GetMapping("/memberPackage")
    public String userSubscription(Model model){
        model.addAttribute("details",userService.userSubscription());

        return "memberPackage";
    }

}
