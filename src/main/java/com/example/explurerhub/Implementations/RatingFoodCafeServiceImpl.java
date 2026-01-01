package com.example.explurerhub.Implementations;

import com.example.explurerhub.Model.*;
import com.example.explurerhub.Repository.*;
import com.example.explurerhub.Service.RatingFoodCafeService;
import com.example.explurerhub.Service.RatingNileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingFoodCafeServiceImpl implements RatingFoodCafeService {

    private final RatingFoodCafeRepo ratingFoodCafeRepo;
    private final UserRepo userRepo;
    private final FavouriteFoodCafeRepo favouriteFoodCafeRepo;

    @Autowired
    public RatingFoodCafeServiceImpl(RatingFoodCafeRepo ratingFoodCafeRepo, UserRepo userRepo, FavouriteFoodCafeRepo favouriteFoodCafeRepo){
        this.ratingFoodCafeRepo = ratingFoodCafeRepo;
        this.userRepo = userRepo;
        this.favouriteFoodCafeRepo = favouriteFoodCafeRepo;

    }

    @Override
    public void saveRating(Long userId, Long foodId, Double ratingValue) {
        if(ratingFoodCafeRepo.findByFoodIdAndUserId(foodId,userId).isPresent()){
            throw new RuntimeException("exisiting rating musuim");
        }
        User user = userRepo.findById(userId).orElseThrow();
        FoodCafe foodCafe = favouriteFoodCafeRepo.findById(foodId).orElseThrow();
        RateFoodCafe rating = new RateFoodCafe();
        rating.setUser(user);
        rating.setFoodCafe(foodCafe);
        rating.setRatingValue(ratingValue);

        ratingFoodCafeRepo.save(rating);
    }





    @Override
    public List<RateFoodCafe> getAllRating() {
        return ratingFoodCafeRepo.findAll();
    }

    @Override
    public List<Object[]> getAverageRatingPerFoodCafes() {
        List<Object[]> ratings = ratingFoodCafeRepo.findAverageRatingPerFoodCafe();
        return ratings;
    }



}
