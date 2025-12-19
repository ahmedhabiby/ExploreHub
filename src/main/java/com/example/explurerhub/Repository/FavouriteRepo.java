package com.example.explurerhub.Repository;

import com.example.explurerhub.Model.CairoMosques;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteRepo extends JpaRepository<CairoMosques,Long> {
}
