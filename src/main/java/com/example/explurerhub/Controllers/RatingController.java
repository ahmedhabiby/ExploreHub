package com.example.explurerhub.Controllers;

import com.example.explurerhub.Model.Rating;
import com.example.explurerhub.Model.User;
import com.example.explurerhub.Service.RatingService;
import com.example.explurerhub.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RatingController {
    private RatingService ratingService;
    private UserService userService;

    @Autowired
    public RatingController(RatingService ratingService, UserService userService) {
        this.ratingService = ratingService;
        this.userService = userService;
    }

    @PostMapping("/rate")
    public String saveRating(@RequestParam String username, @RequestParam Long mosqueID,@RequestParam Double ratingValue) {
        Long userId = userService.getUserIdByUsername(username);
        ratingService.saveRating(userId, mosqueID, ratingValue);

        return "redirect:/ratings";
    }

    // ⬅ هنا الميثود المهمة التي طلبتها
    @GetMapping("/ratings")
    public String showRatings(Model model) {

        List<Rating> ratings = ratingService.getAllRating();
        List<User> users = userService.getAllUser();
        model.addAttribute("ratings", ratings);
        model.addAttribute("users", users);
        List<Object[]> averageRatings = ratingService.getAverageRatingPerMosque();
        model.addAttribute("averageRatings", averageRatings);

        return "rate";
    }
}
