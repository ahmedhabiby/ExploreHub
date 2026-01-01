package com.example.explurerhub.Implementations;

import com.example.explurerhub.Model.*;
import com.example.explurerhub.Repository.*;
import com.example.explurerhub.Service.RatingMusuimService;
import com.example.explurerhub.Service.RatingOldCairoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingOldCairoServiceImpl implements RatingOldCairoService {

    private final RatingOldCairoRepo ratingOldCairoRepo;
    private final UserRepo userRepo;
    private final FavouriteOldCairoRepo favouriteOldCairoRepo;

    @Autowired
    public RatingOldCairoServiceImpl(RatingOldCairoRepo ratingOldCairoRepo, UserRepo userRepo, FavouriteOldCairoRepo favouriteOldCairoRepo){
        this.ratingOldCairoRepo = ratingOldCairoRepo;
        this.userRepo = userRepo;
        this.favouriteOldCairoRepo = favouriteOldCairoRepo;

    }

    @Override
    public void saveRating(Long userId, Long oldId, Double ratingValue) {
        if(ratingOldCairoRepo.findByOldIdAndUserId(oldId,userId).isPresent()){
            throw new RuntimeException("exisiting rating musuim");
        }
        User user = userRepo.findById(userId).orElseThrow();
        oldCairo oldCairo = favouriteOldCairoRepo.findById(oldId).orElseThrow();
        RateOldCairo rating = new RateOldCairo();
        rating.setUser(user);
        rating.setOldCairo(oldCairo);
        rating.setRatingValue(ratingValue);

        ratingOldCairoRepo.save(rating);
    }





    @Override
    public List<RateOldCairo> getAllRating() {
        return ratingOldCairoRepo.findAll();
    }

    @Override
    public List<Object[]> getAverageRatingPerOldCairos() {
        List<Object[]> ratings = ratingOldCairoRepo.findAverageRatingPerOldCairo();
        return ratings;
    }



}
