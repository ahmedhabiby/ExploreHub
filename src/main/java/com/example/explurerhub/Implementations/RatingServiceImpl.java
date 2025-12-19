package com.example.explurerhub.Implementations;

import com.example.explurerhub.Model.CairoMosques;
import com.example.explurerhub.Model.Rating;
import com.example.explurerhub.Model.User;
import com.example.explurerhub.Repository.FavouriteRepo;
import com.example.explurerhub.Repository.RatingRepo;
import com.example.explurerhub.Repository.UserRepo;
import com.example.explurerhub.Service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepo ratingRepo;
    private final UserRepo userRepo;
    private final FavouriteRepo cairoRepo;

    @Autowired
    public RatingServiceImpl(RatingRepo ratingRepo, UserRepo userRepo, FavouriteRepo cairoRepo){
        this.ratingRepo = ratingRepo;
        this.userRepo = userRepo;
        this.cairoRepo = cairoRepo;

    }

    @Override
    public void saveRating(Long userId, Long mosqueId, Double ratingValue) {

        User user = userRepo.findById(userId).orElseThrow();
        CairoMosques mosque = cairoRepo.findById(mosqueId).orElseThrow();

        Rating rating = new Rating();
        rating.setUser(user);
        rating.setCairoMosques(mosque);
        rating.setRatingValue(ratingValue);

        ratingRepo.save(rating);
    }





    @Override
    public List<Rating> getAllRating() {
        return ratingRepo.findAll();
    }

    @Override
    public List<Object[]> getAverageRatingPerMosque() {
        List<Object[]> ratings = ratingRepo.findAverageRatingPerMosque();
        System.out.println("ratings:"+ratings);
        return ratings;
    }

}
