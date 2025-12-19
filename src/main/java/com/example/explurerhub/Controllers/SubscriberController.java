package com.example.explurerhub.Controllers;

import com.example.explurerhub.Model.Subscribers;
import com.example.explurerhub.Model.User;
import com.example.explurerhub.Service.SubscriberService;
import com.example.explurerhub.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SubscriberController {

    private SubscriberService subscriberService;
    private UserService userService;

    @Autowired
    public void setSubscriberService(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/saveSubscriber")
    public String saveSubscriber(@ModelAttribute("subscriber") Subscribers subscriber,
                                 @AuthenticationPrincipal UserDetails loggedUser) {
        if (loggedUser != null) {
            User user = userService.findUserByUsername(loggedUser.getUsername());

            if (user != null) {
                subscriber.setUser(user);
            }
        }

        subscriberService.saveSubscribeers(subscriber);
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("subscriber", new Subscribers());
        return "index";
    }
}
