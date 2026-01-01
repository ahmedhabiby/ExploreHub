package com.example.explurerhub.Repository;

import com.example.explurerhub.Model.FoodCafe;
import com.example.explurerhub.Model.RateFoodCafe;
import com.example.explurerhub.Model.RateNile;
import com.example.explurerhub.Model.oldCairo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingFoodCafeRepo extends JpaRepository<RateFoodCafe,Long> {

    Optional<FoodCafe> findByUserId(Long id);

    @Query("SELECT r.foodCafe.id, r.foodCafe.title, AVG(r.ratingValue) " +
            "FROM RateFoodCafe r " +
            "GROUP BY r.foodCafe.id, r.foodCafe.title")
    List<Object[]> findAverageRatingPerFoodCafe();

    @Query("SELECT r FROM RateFoodCafe r WHERE r.foodCafe.id = :foodId AND r.user.id = :userId")
    Optional<RateFoodCafe> findByFoodIdAndUserId(@Param("foodId") Long foodId,
                                                  @Param("userId") Long userId);

}
