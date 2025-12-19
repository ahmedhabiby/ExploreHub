package com.example.explurerhub.Implementations;

import com.example.explurerhub.Model.Subscribers;
import com.example.explurerhub.Model.User;
import com.example.explurerhub.Repository.SubscriberRepo;
import com.example.explurerhub.Service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriberServiceImplementation implements SubscriberService {
    private SubscriberRepo subscriberRepo;
    @Autowired
    public SubscriberServiceImplementation(SubscriberRepo subscriberRepo) {
        this.subscriberRepo = subscriberRepo;
    }
    @Override
    public void saveSubscribeers(Subscribers subscriber) {
        subscriberRepo.save(subscriber);
    }



}
