package com.example.explurerhub.Controllers;

import com.example.explurerhub.Model.*;
import com.example.explurerhub.Repository.*;
import com.example.explurerhub.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class FavouriteController {

    private FavouriteService favouriteService;
    private UserService userService;
    private FavouriteRepo favouriteRepo;
    private FavoutiteMusiumsService favoutiteMusiumsService;
    private FavouriteMusimRepo favouriteMusimRepo;
    private FavouriteOldCairoRepo favouriteOldCairoRepo;
    private FavoutiteOldCairoService favoutiteOldCairoService;
    private FavouriteNileRepo favouriteNileRepo;
    private FavoutiteNileService favoutiteNileService;
    private FavouriteFoodCafeRepo  favouriteFoodCafeRepo;
    private FavoutiteFoodCafeService favoutiteFoodCafeService;
    @Autowired
    public FavouriteController(FavouriteService favouriteService,FavouriteFoodCafeRepo  favouriteFoodCafeRepo,FavoutiteFoodCafeService favoutiteFoodCafeService,FavouriteNileRepo favouriteNileRepo,FavoutiteNileService favoutiteNileService,FavoutiteOldCairoService favoutiteOldCairoService,FavouriteOldCairoRepo favouriteOldCairoRepo,FavouriteMusimRepo favouriteMusimRepo,FavoutiteMusiumsService favoutiteMusiumsService, UserService userService, FavouriteRepo favouriteRepo) {
        this.favouriteService = favouriteService;
        this.userService = userService;
        this.favouriteRepo = favouriteRepo;
        this.favoutiteMusiumsService = favoutiteMusiumsService;
        this.favouriteMusimRepo = favouriteMusimRepo;
        this.favouriteOldCairoRepo = favouriteOldCairoRepo;
        this.favoutiteOldCairoService = favoutiteOldCairoService;
        this.favouriteNileRepo = favouriteNileRepo;
        this.favoutiteNileService = favoutiteNileService;
        this.favouriteFoodCafeRepo = favouriteFoodCafeRepo;
        this.favoutiteFoodCafeService = favoutiteFoodCafeService;
    }

    @PostMapping("/add/{username}/{MosqueId}")
    public String addMosqueToFavourite(@PathVariable String username, @PathVariable Long MosqueId, RedirectAttributes redirectAttributes) {
        Long userId = userService.getUserIdByUsername(username);
        try {
            favouriteService.addMosqueToFavourite(userId, MosqueId);
            redirectAttributes.addFlashAttribute("success",
                    "Added to favourites ❤️");
        } catch (RuntimeException ex) {
            if ("ALREADY_FAVOURITE".equals(ex.getMessage())) {
                redirectAttributes.addFlashAttribute("error",
                        "You already added this before ⭐");
            }
        }
        return "redirect:/show";

    }
    @PostMapping("/add2/{username}/{oldId}")
    public String addOldToFavourite(@PathVariable String username, @PathVariable Long oldId, RedirectAttributes redirectAttributes) {
        Long userId = userService.getUserIdByUsername(username);
        try {
            favoutiteOldCairoService.addOldCairoToFavourite(userId, oldId);
            redirectAttributes.addFlashAttribute("success",
                    "Added to favourites ❤️");
        } catch (RuntimeException ex) {
            if ("ALREADY_FAVOURITE".equals(ex.getMessage())) {
                redirectAttributes.addFlashAttribute("error",
                        "You already added this before ⭐");
            }
        }
        return "redirect:/show";

    }
    @PostMapping("/add3/{username}/{nileId}")
    public String addNileToFavourite(@PathVariable String username, @PathVariable Long nileId, RedirectAttributes redirectAttributes) {
        Long userId = userService.getUserIdByUsername(username);
        try {
            favoutiteNileService.addNileToFavourite(userId, nileId);
            redirectAttributes.addFlashAttribute("success",
                    "Added to favourites ❤️");
        } catch (RuntimeException ex) {
            if ("ALREADY_FAVOURITE".equals(ex.getMessage())) {
                redirectAttributes.addFlashAttribute("error",
                        "You already added this before ⭐");
            }
        }
        return "redirect:/show";

    }
    @PostMapping("/add4/{username}/{foodId}")
    public String addFoodCafeToFavourite(@PathVariable String username, @PathVariable Long foodId, RedirectAttributes redirectAttributes) {
        Long userId = userService.getUserIdByUsername(username);

        try {
            favoutiteFoodCafeService.addFoodCafeToFavourite(userId, foodId);
            redirectAttributes.addFlashAttribute("success",
                    "Added to favourites ❤️");
        } catch (RuntimeException ex) {
            if ("ALREADY_FAVOURITE".equals(ex.getMessage())) {
                redirectAttributes.addFlashAttribute("error",
                        "You already added this before ⭐");
            }
        }
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

    @GetMapping("/cairo-old")
    public String showCairoOldCairo(Model model, @AuthenticationPrincipal UserDetails user) {
        List<oldCairo> oldCairos = favouriteOldCairoRepo.findAll();
        model.addAttribute("oldCairos", oldCairos);

        // Add logged-in user to model for Thymeleaf
        if (user != null) {
            model.addAttribute("user", user);
        }

        return "cairo-old";
    }
    @GetMapping("/cairo-nile")
    public String showNileCairo(Model model, @AuthenticationPrincipal UserDetails user) {
        List<Nile> niles = favouriteNileRepo.findAll();
        model.addAttribute("niles", niles);

        // Add logged-in user to model for Thymeleaf
        if (user != null) {
            model.addAttribute("user", user);
        }

        return "cairo-nile";
    }
    @GetMapping("/cairo-food")
    public String showFoodCafeCairo(Model model, @AuthenticationPrincipal UserDetails user) {
        List<FoodCafe> foods = favouriteFoodCafeRepo.findAll();
        model.addAttribute("foods", foods);

        // Add logged-in user to model for Thymeleaf
        if (user != null) {
            model.addAttribute("user", user);
        }

        return "cairo-food";
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
    @PostMapping("/remove3/{userId}/{nileId}")
    public String removeNilefromFavourite(@PathVariable Long userId, @PathVariable Long nileId) {
        favoutiteNileService.removeNileFromFavourite(userId, nileId);
        return "redirect:/show";
    }
    @PostMapping("/remove4/{userId}/{foodId}")
    public String removeFoodCafefromFavourite(@PathVariable Long userId, @PathVariable Long foodId) {
        favoutiteFoodCafeService.removeFoodCafeFromFavourite(userId, foodId);
        return "redirect:/show";
    }
        @PostMapping("/remove2/{userId}/{oldId}")
        public String removeOldfromFavourite(@PathVariable Long userId, @PathVariable Long oldId) {
            favoutiteOldCairoService.removeOldCairoFromFavourite(userId, oldId);
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
        List<oldCairo> oldCairos = favoutiteOldCairoService.getFavouriteOldCairos(UserId);
        model.addAttribute("oldCairos", oldCairos);
        List<Nile> niles = favoutiteNileService.getFavouriteNiles(UserId);
        model.addAttribute("niles", niles);
        List<FoodCafe> foods = favoutiteFoodCafeService.getFavouriteFoodCafes(UserId);
        model.addAttribute("foods", foods);
        return "shopping";
    }
}
