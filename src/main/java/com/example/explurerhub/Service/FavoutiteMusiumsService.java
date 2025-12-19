package com.example.explurerhub.Service;

import com.example.explurerhub.Model.CairoMusiums;

import java.util.List;

public interface FavoutiteMusiumsService {
    void addMusiumsToFavourite(Long userId,Long MusiumsId);
    List<CairoMusiums> getFavouriteMusiums(Long userId);
    void removeMusiumFromFavourite(Long userId,Long MusiumsId);
}
