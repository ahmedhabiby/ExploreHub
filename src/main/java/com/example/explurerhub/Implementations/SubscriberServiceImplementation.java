package com.example.explurerhub.Implementations;

import com.example.explurerhub.Model.Subscribers;
import com.example.explurerhub.Model.User;
import com.example.explurerhub.Repository.SubscriberRepo;
import com.example.explurerhub.Repository.UserRepo;
import com.example.explurerhub.Service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SubscriberServiceImplementation implements SubscriberService {
    private SubscriberRepo subscriberRepo;
    private UserRepo userRepo;
    @Autowired
    public SubscriberServiceImplementation(SubscriberRepo subscriberRepo, UserRepo userRepo) {
        this.subscriberRepo = subscriberRepo;
        this.userRepo = userRepo;
    }
    @Override
    public void saveSubscribeers(Subscribers subscriber) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User user=userRepo.findByUsername(username);
        Subscribers subscribers=subscriberRepo.findSubscribersByUserId(user.getId());
        if(subscribers!=null){
            throw new RuntimeException("Subscriber already exists");
        }
        subscriberRepo.save(subscriber);
    }



}
