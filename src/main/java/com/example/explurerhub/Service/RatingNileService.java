package com.example.explurerhub.Service;

import com.example.explurerhub.Model.RateNile;
import com.example.explurerhub.Model.RateOldCairo;

import java.util.List;

public interface RatingNileService {

    void saveRating(Long userId, Long nileId, Double ratingValue);

    List<RateNile> getAllRating();
    public List<Object[]> getAverageRatingPerNiles();

}
