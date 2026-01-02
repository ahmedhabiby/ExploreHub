package com.example.explurerhub.Implementations;

import com.example.explurerhub.Model.CairoMusiums;
import com.example.explurerhub.Model.User;
import com.example.explurerhub.Model.oldCairo;
import com.example.explurerhub.Repository.FavouriteMusimRepo;
import com.example.explurerhub.Repository.FavouriteOldCairoRepo;
import com.example.explurerhub.Repository.UserRepo;
import com.example.explurerhub.Service.FavoutiteMusiumsService;
import com.example.explurerhub.Service.FavoutiteOldCairoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FavoutiteOldCairoServiceImpl implements FavoutiteOldCairoService {


    private UserRepo userRepo;
    private FavouriteOldCairoRepo favouriteOldCairoRepo;

    @Autowired
    public FavoutiteOldCairoServiceImpl(UserRepo userRepo, FavouriteOldCairoRepo favouriteOldCairoRepo){
        this.userRepo=userRepo;
        this.favouriteOldCairoRepo=favouriteOldCairoRepo;
    }
    @Override
    public void addOldCairoToFavourite(Long userId, Long oldId) {
        User user=userRepo.findById(userId).orElseThrow();
        oldCairo oldCairo=favouriteOldCairoRepo.findById(oldId).orElseThrow();
        boolean alreadyFavourite = oldCairo.getUsers().stream()
                .anyMatch(u -> u.getId().equals(user.getId()));

        if (alreadyFavourite) {
            throw new RuntimeException("ALREADY_FAVOURITE");
        }
        oldCairo.getUsers().add(user);
        favouriteOldCairoRepo.save(oldCairo);
    }

    @Override
    public List<oldCairo> getFavouriteOldCairos(Long userId) {
        User user=userRepo.findById(userId).orElseThrow();
        return user.getOldCairos();
    }

    @Override
    public void removeOldCairoFromFavourite(Long userId, Long oldId) {
        List<oldCairo> cairoMusiums=getFavouriteOldCairos(userId);
        for(oldCairo oldCairo:cairoMusiums){
            if(Objects.equals(oldCairo.getId(), oldId)){
                oldCairo.getUsers().remove(userRepo.findById(userId).orElseThrow());
                favouriteOldCairoRepo.save(oldCairo);
            }
        }
    }
}
