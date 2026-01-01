package com.example.explurerhub.Service;

import com.example.explurerhub.Model.Nile;
import com.example.explurerhub.Model.oldCairo;

import java.util.List;

public interface FavoutiteNileService {
    void addNileToFavourite(Long userId,Long nileId);
    List<Nile> getFavouriteNiles(Long userId);
    void removeNileFromFavourite(Long userId,Long nileId);
}
