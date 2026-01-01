package com.example.explurerhub.Service;

import com.example.explurerhub.Model.FoodCafe;
import com.example.explurerhub.Model.Nile;

import java.util.List;

public interface FavoutiteFoodCafeService {
    void addFoodCafeToFavourite(Long userId,Long foodId);
    List<FoodCafe> getFavouriteFoodCafes(Long userId);
    void removeFoodCafeFromFavourite(Long userId,Long foodId);
}
