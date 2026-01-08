package com.example.explurerhub.Controllers;

import com.example.explurerhub.Model.*;
import com.example.explurerhub.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RatingController {
    private RatingService ratingService;
    private RatingMusuimService ratingMusuimService;
    private RatingOldCairoService ratingOldCairoService;
    private UserService userService;
    private RatingNileService ratingNileService;
    private RatingFoodCafeService ratingFoodCafeService;

    @Autowired
    public RatingController(RatingService ratingService,RatingFoodCafeService ratingFoodCafeService,RatingNileService ratingNileService,RatingOldCairoService ratingOldCairoService, UserService userService, RatingMusuimService ratingMusuimService) {
        this.ratingService = ratingService;
        this.userService = userService;
        this.ratingMusuimService = ratingMusuimService;
        this.ratingOldCairoService = ratingOldCairoService;
        this.ratingNileService = ratingNileService;
        this.ratingFoodCafeService = ratingFoodCafeService;
    }

    @PostMapping("/rate")
    public String saveRating(@RequestParam String username, @RequestParam Long mosqueId,@RequestParam Double ratingValue) {
        Long userId = userService.getUserIdByUsername(username);
        ratingService.saveRating(userId, mosqueId, ratingValue);

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

    @PostMapping("/rate-nile")
    public String saveNileRating(@RequestParam String username, @RequestParam Long nileId,@RequestParam Double ratingValue) {
        Long userId = userService.getUserIdByUsername(username);
        ratingNileService.saveRating(userId, nileId, ratingValue);

        return "redirect:/ratings-nile";
    }

    // ⬅ هنا الميثود المهمة التي طلبتها
    @GetMapping("/ratings-nile")
    public String showNileRatings(Model model) {

        List<RateNile> ratings = ratingNileService.getAllRating();
        List<User> users = userService.getAllUser();
        model.addAttribute("niles", ratings);
        model.addAttribute("users", users);
        List<Object[]> averageRatings = ratingNileService.getAverageRatingPerNiles();
        model.addAttribute("averageRatingsNiles", averageRatings);

        return "rate-nile";
    }
    @PostMapping("/rate-food")
    public String saveFoodRating(@RequestParam String username, @RequestParam Long foodId,@RequestParam Double ratingValue) {
        Long userId = userService.getUserIdByUsername(username);
        ratingFoodCafeService.saveRating(userId, foodId, ratingValue);

        return "redirect:/ratings-foods";
    }

    // ⬅ هنا الميثود المهمة التي طلبتها
    @GetMapping("/ratings-foods")
    public String showFoodRatings(Model model) {

        List<RateFoodCafe> ratings = ratingFoodCafeService.getAllRating();
        List<User> users = userService.getAllUser();
        model.addAttribute("foods", ratings);
        model.addAttribute("users", users);
        List<Object[]> averageRatings = ratingFoodCafeService.getAverageRatingPerFoodCafes();
        model.addAttribute("averageRatingsFoods", averageRatings);

        return "rate-food";
    }
    @PostMapping("/rate-old")
    public String saveOldCairoRating(@RequestParam String username, @RequestParam Long oldId,@RequestParam Double ratingValue) {
        Long userId = userService.getUserIdByUsername(username);
        ratingOldCairoService.saveRating(userId, oldId, ratingValue);

        return "redirect:/ratings-old";
    }

    // ⬅ هنا الميثود المهمة التي طلبتها
    @GetMapping("/ratings-old")
    public String showOldCairoRatings(Model model) {

        List<RateOldCairo> ratings = ratingOldCairoService.getAllRating();
        List<User> users = userService.getAllUser();
        model.addAttribute("ratingsOld", ratings);
        model.addAttribute("users", users);
        List<Object[]> averageRatings = ratingOldCairoService.getAverageRatingPerOldCairos();
        model.addAttribute("averageRatingsOld", averageRatings);

        return "rate-oldCairo";
    }
    @PostMapping("/rate-musuim")
    public String saveMusuimRating(@RequestParam String username, @RequestParam Long musuimId,@RequestParam Double ratingValue) {
        Long userId = userService.getUserIdByUsername(username);
        ratingMusuimService.saveRating(userId, musuimId, ratingValue);

        return "redirect:/ratingsmusuim";
    }

    // ⬅ هنا الميثود المهمة التي طلبتها
    @GetMapping("/ratingsmusuim")
    public String showMusuimRatings(Model model) {
        List<RateMusiums> ratings = ratingMusuimService.getAllRating();
        List<User> users = userService.getAllUser();
        model.addAttribute("ratingsMusuim", ratings);
        model.addAttribute("users", users);
        List<Object[]> averageRatings = ratingMusuimService.getAverageRatingPerMusiums();
        model.addAttribute("averageRatingsMusuims", averageRatings);

        return "rate-musuem";

    }


}
