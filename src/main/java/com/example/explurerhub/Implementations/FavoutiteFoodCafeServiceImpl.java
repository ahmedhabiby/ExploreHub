package com.example.explurerhub.Implementations;

import com.example.explurerhub.Model.FoodCafe;
import com.example.explurerhub.Model.Nile;
import com.example.explurerhub.Model.User;
import com.example.explurerhub.Repository.FavouriteFoodCafeRepo;
import com.example.explurerhub.Repository.FavouriteNileRepo;
import com.example.explurerhub.Repository.UserRepo;
import com.example.explurerhub.Service.FavoutiteFoodCafeService;
import com.example.explurerhub.Service.FavoutiteNileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FavoutiteFoodCafeServiceImpl implements FavoutiteFoodCafeService {


    private UserRepo userRepo;
    private FavouriteFoodCafeRepo favouriteFoodCafeRepo;

    @Autowired
    public FavoutiteFoodCafeServiceImpl(UserRepo userRepo, FavouriteFoodCafeRepo favouriteFoodCafeRepo){
        this.userRepo=userRepo;
        this.favouriteFoodCafeRepo=favouriteFoodCafeRepo;
    }
    @Override
    public void addFoodCafeToFavourite(Long userId, Long foodId) {
        User user=userRepo.findById(userId).orElseThrow();
        FoodCafe foodCafe=favouriteFoodCafeRepo.findById(foodId).orElseThrow();
        boolean alreadyFavourite = foodCafe.getUsers().stream()
                .anyMatch(u -> u.getId().equals(user.getId()));

        if (alreadyFavourite) {
            throw new RuntimeException("ALREADY_FAVOURITE");
        }
        foodCafe.getUsers().add(user);
        favouriteFoodCafeRepo.save(foodCafe);
    }

    @Override
    public List<FoodCafe> getFavouriteFoodCafes(Long userId) {
        User user=userRepo.findById(userId).orElseThrow();
        return user.getFoodCafes();
    }

    @Override
    public void removeFoodCafeFromFavourite(Long userId, Long foodId) {
        List<FoodCafe> foodCafes=getFavouriteFoodCafes(userId);
        for(FoodCafe foodCafe:foodCafes){
            if(Objects.equals(foodCafe.getId(), foodId)){
                foodCafe.getUsers().remove(userRepo.findById(userId).orElseThrow());
                favouriteFoodCafeRepo.save(foodCafe);
            }
        }
    }
}
