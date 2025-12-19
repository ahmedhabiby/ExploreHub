package com.example.explurerhub.Implementations;

import com.example.explurerhub.Model.CairoMusiums;
import com.example.explurerhub.Model.User;
import com.example.explurerhub.Repository.FavouriteMusimRepo;
import com.example.explurerhub.Repository.UserRepo;
import com.example.explurerhub.Service.FavoutiteMusiumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FavoutiteMusiumsServiceImpl implements FavoutiteMusiumsService {


    private UserRepo userRepo;
    private FavouriteMusimRepo favouriteMusimRepo;

    @Autowired
    public FavoutiteMusiumsServiceImpl(UserRepo userRepo, FavouriteMusimRepo favouriteMusimRepo){
        this.userRepo=userRepo;
        this.favouriteMusimRepo=favouriteMusimRepo;
    }
    @Override
    public void addMusiumsToFavourite(Long userId, Long MusiumsId) {
        User user=userRepo.findById(userId).orElseThrow();
        CairoMusiums musiums=favouriteMusimRepo.findById(MusiumsId).orElseThrow();
        musiums.getUsers().add(user);
        favouriteMusimRepo.save(musiums);
    }

    @Override
    public List<CairoMusiums> getFavouriteMusiums(Long userId) {
        User user=userRepo.findById(userId).orElseThrow();
        return user.getCairoMusiums();
    }

    @Override
    public void removeMusiumFromFavourite(Long userId, Long MusiumsId) {
        List<CairoMusiums> cairoMusiums=getFavouriteMusiums(userId);
        for(CairoMusiums cairoMusium:cairoMusiums){
            if(Objects.equals(cairoMusium.getId(), MusiumsId)){
                cairoMusium.getUsers().remove(userRepo.findById(userId).orElseThrow());
                favouriteMusimRepo.save(cairoMusium);
            }
        }
    }
}
