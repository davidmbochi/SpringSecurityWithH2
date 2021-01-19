package com.david.SpringSecurityWithH2.controller;

import com.david.SpringSecurityWithH2.Service.SubscriptionService;
import com.david.SpringSecurityWithH2.entity.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService){
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/")
    public String homePage(){
        return "redirect:/findAllSubscriptions";
    }

    @PostMapping("/saveSubscription")
    public void saveSubscription(@RequestBody Subscription subscription){
        subscriptionService.saveSubscription(subscription);
    }

    @GetMapping("/findSubscription/{name}")
    public Subscription findSubscriptionByName(@PathVariable("name") String name){
        return subscriptionService.findSubscriptionByName(name);
    }

    @GetMapping("/findAllSubscriptions")
    public String findAllSubscriptions(Model model){
        model.addAttribute("subscriptionsData",subscriptionService.findAllSubscriptions());

        return "subscriptions";
    }
}
