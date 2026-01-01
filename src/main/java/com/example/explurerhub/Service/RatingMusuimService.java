package com.example.explurerhub.Service;

import com.example.explurerhub.Model.RateMusiums;
import com.example.explurerhub.Model.Rating;

import java.util.List;

public interface RatingMusuimService {

    void saveRating(Long userId, Long musuimId, Double ratingValue);

    List<RateMusiums> getAllRating();
    public List<Object[]> getAverageRatingPerMusiums();

}
