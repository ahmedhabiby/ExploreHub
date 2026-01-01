package com.example.explurerhub.Implementations;

import com.example.explurerhub.Model.*;
import com.example.explurerhub.Repository.*;
import com.example.explurerhub.Service.RatingNileService;
import com.example.explurerhub.Service.RatingOldCairoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingNileServiceImpl implements RatingNileService {

    private final RatingNileRepo ratingNileRepo;
    private final UserRepo userRepo;
    private final FavouriteNileRepo favouriteNileRepo;

    @Autowired
    public RatingNileServiceImpl(RatingNileRepo ratingNileRepo, UserRepo userRepo, FavouriteNileRepo favouriteNileRepo){
        this.ratingNileRepo = ratingNileRepo;
        this.userRepo = userRepo;
        this.favouriteNileRepo = favouriteNileRepo;

    }

    @Override
    public void saveRating(Long userId, Long nileId, Double ratingValue) {
        if(ratingNileRepo.findByNileIdAndUserId(nileId,userId).isPresent()){
            throw new RuntimeException("exisiting rating musuim");
        }
        User user = userRepo.findById(userId).orElseThrow();
        Nile nile = favouriteNileRepo.findById(nileId).orElseThrow();
        RateNile rating = new RateNile();
        rating.setUser(user);
        rating.setNile(nile);
        rating.setRatingValue(ratingValue);

        ratingNileRepo.save(rating);
    }





    @Override
    public List<RateNile> getAllRating() {
        return ratingNileRepo.findAll();
    }

    @Override
    public List<Object[]> getAverageRatingPerNiles() {
        List<Object[]> ratings = ratingNileRepo.findAverageRatingPerNile();
        return ratings;
    }



}
