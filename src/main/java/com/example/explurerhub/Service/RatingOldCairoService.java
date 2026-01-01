package com.example.explurerhub.Service;

import com.example.explurerhub.Model.RateMusiums;
import com.example.explurerhub.Model.RateOldCairo;

import java.util.List;

public interface RatingOldCairoService {

    void saveRating(Long userId, Long oldId, Double ratingValue);

    List<RateOldCairo> getAllRating();
    public List<Object[]> getAverageRatingPerOldCairos();

}
