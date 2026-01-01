package com.example.explurerhub.Service;

import com.example.explurerhub.Model.RateFoodCafe;
import com.example.explurerhub.Model.RateNile;

import java.util.List;

public interface RatingFoodCafeService {

    void saveRating(Long userId, Long foodId, Double ratingValue);

    List<RateFoodCafe> getAllRating();
    public List<Object[]> getAverageRatingPerFoodCafes();

}
