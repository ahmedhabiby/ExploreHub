package com.example.explurerhub.Implementations;

import com.example.explurerhub.Model.Nile;
import com.example.explurerhub.Model.User;
import com.example.explurerhub.Model.oldCairo;
import com.example.explurerhub.Repository.FavouriteNileRepo;
import com.example.explurerhub.Repository.FavouriteOldCairoRepo;
import com.example.explurerhub.Repository.UserRepo;
import com.example.explurerhub.Service.FavoutiteNileService;
import com.example.explurerhub.Service.FavoutiteOldCairoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FavoutiteNileServiceImpl implements FavoutiteNileService {


    private UserRepo userRepo;
    private FavouriteNileRepo favouriteNileRepo;

    @Autowired
    public FavoutiteNileServiceImpl(UserRepo userRepo, FavouriteNileRepo favouriteNileRepo){
        this.userRepo=userRepo;
        this.favouriteNileRepo=favouriteNileRepo;
    }
    @Override
    public void addNileToFavourite(Long userId, Long oldId) {
        User user=userRepo.findById(userId).orElseThrow();
        Nile nile=favouriteNileRepo.findById(oldId).orElseThrow();
        boolean alreadyFavourite = nile.getUsers().stream()
                .anyMatch(u -> u.getId().equals(user.getId()));

        if (alreadyFavourite) {
            throw new RuntimeException("ALREADY_FAVOURITE");
        }
        nile.getUsers().add(user);
        favouriteNileRepo.save(nile);
    }

    @Override
    public List<Nile> getFavouriteNiles(Long userId) {
        User user=userRepo.findById(userId).orElseThrow();
        return user.getNiles();
    }

    @Override
    public void removeNileFromFavourite(Long userId, Long oldId) {
        List<Nile> niles=getFavouriteNiles(userId);
        for(Nile nile:niles){
            if(Objects.equals(nile.getId(), oldId)){
                nile.getUsers().remove(userRepo.findById(userId).orElseThrow());
                favouriteNileRepo.save(nile);
            }
        }
    }
}
