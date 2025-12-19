package com.example.explurerhub.Service;

import com.example.explurerhub.Model.CairoMosques;

import java.util.List;

public interface FavouriteService {
    void addMosqueToFavourite(Long userId,Long MosqueId);
    List<CairoMosques> getFavouriteMosques(Long userId);
    void removeMosqueFromFavourite(Long userId,Long MosqueId);
}
