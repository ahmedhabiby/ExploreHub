package com.example.explurerhub.Service;

import com.example.explurerhub.Model.CairoMusiums;
import com.example.explurerhub.Model.oldCairo;

import java.util.List;

public interface FavoutiteOldCairoService {
    void addOldCairoToFavourite(Long userId,Long oldId);
    List<oldCairo> getFavouriteOldCairos(Long userId);
    void removeOldCairoFromFavourite(Long userId,Long oldId);
}
