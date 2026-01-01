package com.example.explurerhub.Implementations;

import com.example.explurerhub.Model.*;
import com.example.explurerhub.Repository.*;
import com.example.explurerhub.Service.RatingMusuimService;
import com.example.explurerhub.Service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingMusuimsServiceImpl implements RatingMusuimService {

    private final RatingMusuimRepo ratingRepo;
    private final UserRepo userRepo;
    private final FavouriteMusimRepo cairoRepo;

    @Autowired
    public RatingMusuimsServiceImpl(RatingMusuimRepo ratingRepo, UserRepo userRepo, FavouriteMusimRepo cairoRepo){
        this.ratingRepo = ratingRepo;
        this.userRepo = userRepo;
        this.cairoRepo = cairoRepo;

    }

    @Override
    public void saveRating(Long userId, Long musuimId, Double ratingValue) {
        if(ratingRepo.findByMuseumIdAndUserId(musuimId,userId).isPresent()){
            throw new RuntimeException("exisiting rating musuim");
        }
        User user = userRepo.findById(userId).orElseThrow();
        CairoMusiums musiums = cairoRepo.findById(musuimId).orElseThrow();
        RateMusiums rating = new RateMusiums();
        rating.setUser(user);
        rating.setMuseum(musiums);
        rating.setRatingValue(ratingValue);

        ratingRepo.save(rating);
    }





    @Override
    public List<RateMusiums> getAllRating() {
        return ratingRepo.findAll();
    }

    @Override
    public List<Object[]> getAverageRatingPerMusiums() {
        List<Object[]> ratings = ratingRepo.findAverageRatingPerMuseum();
        return ratings;
    }



}
