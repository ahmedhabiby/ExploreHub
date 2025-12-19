package com.example.explurerhub.Controllers;

import com.example.explurerhub.Model.CairoMosques;
import com.example.explurerhub.Model.CairoMusiums;
import com.example.explurerhub.Model.User;
import com.example.explurerhub.Repository.FavouriteMusimRepo;
import com.example.explurerhub.Repository.FavouriteRepo;
import com.example.explurerhub.Service.FavouriteService;
import com.example.explurerhub.Service.FavoutiteMusiumsService;
import com.example.explurerhub.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FavouriteController {

    private FavouriteService favouriteService;
    private UserService userService;
    private FavouriteRepo favouriteRepo;
    private FavoutiteMusiumsService favoutiteMusiumsService;
    private FavouriteMusimRepo favouriteMusimRepo;
    @Autowired
    public FavouriteController(FavouriteService favouriteService,FavouriteMusimRepo favouriteMusimRepo,FavoutiteMusiumsService favoutiteMusiumsService, UserService userService, FavouriteRepo favouriteRepo) {
        this.favouriteService = favouriteService;
        this.userService = userService;
        this.favouriteRepo = favouriteRepo;
        this.favoutiteMusiumsService = favoutiteMusiumsService;
        this.favouriteMusimRepo = favouriteMusimRepo;
    }

    @PostMapping("/add/{username}/{MosqueId}")
    public String addMosqueToFavourite(@PathVariable String username, @PathVariable Long MosqueId) {
        Long userId = userService.getUserIdByUsername(username);
        favouriteService.addMosqueToFavourite(userId, MosqueId);
        return "redirect:/show";

    }



    @GetMapping("/cairo-mosques")
    public String showCairoMosques(Model model, @AuthenticationPrincipal UserDetails user) {
        List<CairoMosques> cairoMosques = favouriteRepo.findAll();
        model.addAttribute("cairoList", cairoMosques);

        // Add logged-in user to model for Thymeleaf
        if (user != null) {
            model.addAttribute("user", user);
        }

        return "cairo-mosques";
    }

    @PostMapping("/add1/{username}/{MusiumsId}")
    public String addMusiumsToFavourite(@PathVariable String username, @PathVariable Long MusiumsId) {
        Long userId = userService.getUserIdByUsername(username);
        favoutiteMusiumsService.addMusiumsToFavourite(userId, MusiumsId);
        return "redirect:/show";

    }



    @GetMapping("/cairo-museums")
    public String showCairoMuseums(Model model, @AuthenticationPrincipal UserDetails user) {
        List<CairoMusiums> cairoMusiums = favouriteMusimRepo.findAll();
        model.addAttribute("cairoList1", cairoMusiums);

        // Add logged-in user to model for Thymeleaf
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "cairo-museums";
    }

    @PostMapping("/remove/{userId}/{MosqueId}")
    public String removeMosquefromFavourite(@PathVariable Long userId, @PathVariable Long MosqueId) {
        favouriteService.removeMosqueFromFavourite(userId, MosqueId);
        return "redirect:/show";

    }
    @PostMapping("/remove1/{userId}/{MusiumsId}")
    public String removeMusiumsfromFavourite(@PathVariable Long userId, @PathVariable Long MusiumsId) {
        favoutiteMusiumsService.removeMusiumFromFavourite(userId, MusiumsId);
        return "redirect:/show";

    }
    @GetMapping("/show")
    public String showFavourite(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails == null) {
            return "redirect:/login";
        }
        String username = userDetails.getUsername();
        Long UserId = userService.getUserIdByUsername(username);
        List<CairoMosques> cairoMosques = favouriteService.getFavouriteMosques(UserId);
        model.addAttribute("mosques", cairoMosques);
        model.addAttribute("userId", UserId);
        List<CairoMusiums> cairoMusiums = favoutiteMusiumsService.getFavouriteMusiums(UserId);
        model.addAttribute("musiums", cairoMusiums);
        return "shopping";
    }
}
