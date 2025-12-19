package com.example.explurerhub.Service;

import com.example.explurerhub.Model.Rating;

import java.util.List;

public interface RatingService {

    void saveRating(Long userId, Long mosqueId, Double ratingValue);

    List<Rating> getAllRating();
    public List<Object[]> getAverageRatingPerMosque();
}
