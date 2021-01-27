package com.david.SpringSecurityWithH2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/logout")
    public String logout(){
        return "login";
    }
    @GetMapping("/accessDenied")
    public String notAuthorized(){
        return "accessDenied";
    }
}
