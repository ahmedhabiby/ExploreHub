package com.example.explurerhub.Implementations;

import com.example.explurerhub.Model.CairoMosques;
import com.example.explurerhub.Model.User;
import com.example.explurerhub.Repository.FavouriteRepo;
import com.example.explurerhub.Repository.UserRepo;
import com.example.explurerhub.Service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FavouriteServiceImplementation implements FavouriteService {

    private FavouriteRepo favouriteRepo;
    private UserRepo userRepo;

    @Autowired
    public  FavouriteServiceImplementation (FavouriteRepo favouriteRepo,UserRepo userRepo){
        this.favouriteRepo=favouriteRepo;
        this.userRepo=userRepo;
    }
    @Override
    public void addMosqueToFavourite(Long userId, Long MosqueId) {
        User user = userRepo.findById(userId).orElseThrow();
        CairoMosques cairoMosques=favouriteRepo.findById(MosqueId).orElseThrow();
        cairoMosques.getUsers().add(user);
        favouriteRepo.save(cairoMosques);
    }

    @Override
    public List<CairoMosques> getFavouriteMosques(Long userId) {
        User user = userRepo.findById(userId).orElseThrow();
        return user.getCairoMosques();
    }

    @Override
    public void removeMosqueFromFavourite(Long userId, Long MosqueId) {
        List<CairoMosques> cairoMosques=getFavouriteMosques(userId);
        for(CairoMosques cairoMosque:cairoMosques){
            if(Objects.equals(cairoMosque.getId(), MosqueId)){
                cairoMosque.getUsers().remove(userRepo.findById(userId).orElseThrow());
                favouriteRepo.save(cairoMosque);
            }
        }
    }
}
