package com.example.explurerhub.Repository;

import com.example.explurerhub.Model.Subscribers;
import com.example.explurerhub.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepo extends JpaRepository<Subscribers,Long> {
    @Query("SELECT r FROM Subscribers r WHERE r.user.id = :userId")
    Subscribers findSubscribersByUserId(@Param("userId") long userId);

}
