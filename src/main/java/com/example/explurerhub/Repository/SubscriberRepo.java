package com.example.explurerhub.Repository;

import com.example.explurerhub.Model.Subscribers;
import com.example.explurerhub.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepo extends JpaRepository<Subscribers,Long> {
}
