package com.example.explurerhub.Repository;

import com.example.explurerhub.Model.FoodCafe;
import com.example.explurerhub.Model.Nile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteFoodCafeRepo extends JpaRepository<FoodCafe,Long> {

}
