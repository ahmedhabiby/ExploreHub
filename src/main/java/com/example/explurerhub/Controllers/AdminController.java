package com.example.explurerhub.Controllers;

import com.example.explurerhub.Model.*;
import com.example.explurerhub.Repository.*;
import com.example.explurerhub.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private UserRepo userRepo;
    @Autowired private FavouriteRepo mosqueRepo;
    @Autowired private FavouriteMusimRepo museumRepo;
    @Autowired private FavouriteOldCairoRepo oldCairoRepo;
    @Autowired private FavouriteNileRepo nileRepo;
    @Autowired private FavouriteFoodCafeRepo foodRepo;
    @Autowired private SubscriberRepo subscribersRepo; // <--- ADD THIS LINE

    @Autowired private RatingService mosqueRatingService;
    @Autowired private RatingMusuimService museumRatingService;
    @Autowired private RatingOldCairoService oldRatingService;
    @Autowired private RatingNileService nileRatingService;
    @Autowired private RatingFoodCafeService foodRatingService;

    // 1. Convert Empty Strings to NULL
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    // 2. HELPER: CLEAN STRINGS
    private String clean(String text) {
        if (text == null) return null;
        return text.replace("\"", "").replace("'", "").trim();
    }

    // --- DASHBOARD ---
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        // 1. Load Main Content
        model.addAttribute("users", userRepo.findAll());
        model.addAttribute("mosques", mosqueRepo.findAll());
        model.addAttribute("museums", museumRepo.findAll());
        model.addAttribute("oldCairos", oldCairoRepo.findAll());
        model.addAttribute("niles", nileRepo.findAll());
        model.addAttribute("foods", foodRepo.findAll());

        // Inside showDashboard(Model model) { ...
        model.addAttribute("subscribers", subscribersRepo.findAll()); // <--- ADD THIS LINE

        // 2. Load Average Stats (The numbers for the badges)
        model.addAttribute("mosqueRatings", mapRatings(mosqueRatingService.getAverageRatingPerMosque()));
        model.addAttribute("museumRatings", mapRatings(museumRatingService.getAverageRatingPerMusiums()));
        model.addAttribute("oldCairoRatings", mapRatings(oldRatingService.getAverageRatingPerOldCairos()));
        model.addAttribute("nileRatings", mapRatings(nileRatingService.getAverageRatingPerNiles()));
        model.addAttribute("foodRatings", mapRatings(foodRatingService.getAverageRatingPerFoodCafes()));

        // 3. LOAD DETAILED RATINGS (For the new Tabs)
        // These methods must exist in your Services (e.g., findAll())
        model.addAttribute("allMosqueRatings", mosqueRatingService.getAllRating());
        model.addAttribute("allMuseumRatings", museumRatingService.getAllRating());
        model.addAttribute("allOldRatings", oldRatingService.getAllRating());
        model.addAttribute("allNileRatings", nileRatingService.getAllRating());
        model.addAttribute("allFoodRatings", foodRatingService.getAllRating());

        return "adminDashboard";
    }
    // --- SAVE ACTIONS (User Removed) ---
    // NOTE: saveUser method is DELETED.

    @PostMapping("/saveMosque")
    public String saveMosque(@ModelAttribute CairoMosques mosque) {
        mosque.setTitle(clean(mosque.getTitle()));
        mosque.setImageUrl(clean(mosque.getImageUrl()));
        mosque.setMetaData(clean(mosque.getMetaData()));
        if (mosque.getId() != null && mosque.getId() == 0) mosque.setId(null);
        mosqueRepo.save(mosque);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/saveMuseum")
    public String saveMuseum(@ModelAttribute CairoMusiums museum) {
        museum.setTitle(clean(museum.getTitle()));
        museum.setImageUrl(clean(museum.getImageUrl()));
        museum.setMetaData(clean(museum.getMetaData()));
        if (museum.getId() != null && museum.getId() == 0) museum.setId(null);
        museumRepo.save(museum);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/saveOldCairo")
    public String saveOldCairo(@ModelAttribute oldCairo old) {
        old.setTitle(clean(old.getTitle()));
        old.setImageUrl(clean(old.getImageUrl()));
        old.setMetaData(clean(old.getMetaData()));
        if (old.getId() != null && old.getId() == 0) old.setId(null);
        oldCairoRepo.save(old);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/saveNile")
    public String saveNile(@ModelAttribute Nile nile) {
        nile.setTitle(clean(nile.getTitle()));
        nile.setImageUrl(clean(nile.getImageUrl()));
        nile.setMetaData(clean(nile.getMetaData()));
        if (nile.getId() != null && nile.getId() == 0) nile.setId(null);
        nileRepo.save(nile);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/saveFood")
    public String saveFood(@ModelAttribute FoodCafe food) {
        food.setTitle(clean(food.getTitle()));
        food.setImageUrl(clean(food.getImageUrl()));
        food.setMetaData(clean(food.getMetaData()));
        if (food.getId() != null && food.getId() == 0) food.setId(null);
        foodRepo.save(food);
        return "redirect:/admin/dashboard";
    }

    // --- DELETE ACTIONS (User Delete Kept) ---
    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam Long id) {
        userRepo.deleteById(id);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/deleteMosque")
    public String deleteMosque(@RequestParam Long id) { mosqueRepo.deleteById(id); return "redirect:/admin/dashboard"; }
    @PostMapping("/deleteMuseum")
    public String deleteMuseum(@RequestParam Long id) { museumRepo.deleteById(id); return "redirect:/admin/dashboard"; }
    @PostMapping("/deleteOldCairo")
    public String deleteOldCairo(@RequestParam Long id) { oldCairoRepo.deleteById(id); return "redirect:/admin/dashboard"; }
    @PostMapping("/deleteNile")
    public String deleteNile(@RequestParam Long id) { nileRepo.deleteById(id); return "redirect:/admin/dashboard"; }
    @PostMapping("/deleteFood")
    public String deleteFood(@RequestParam Long id) { foodRepo.deleteById(id); return "redirect:/admin/dashboard"; }
    // <--- ADD THIS WHOLE BLOCK
    @PostMapping("/deleteSubscriber")
    public String deleteSubscriber(@RequestParam Long id) {
        subscribersRepo.deleteById(id);
        return "redirect:/admin/dashboard";
    }

    private Map<Long, Double> mapRatings(List<Object[]> rawList) {
        Map<Long, Double> map = new HashMap<>();
        if (rawList != null) {
            for (Object[] obj : rawList) {
                try {
                    map.put((Long) obj[0], (Double) obj[1]);
                } catch (Exception e) { /* ignore */ }
            }
        }
        return map;
    }
}