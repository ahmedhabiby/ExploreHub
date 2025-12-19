package com.example.explurerhub.Controllers;

import com.example.explurerhub.Model.User;
import com.example.explurerhub.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SecurityController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SecurityController(UserService userService, PasswordEncoder passwordEncoder, JdbcTemplate jdbcTemplate) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jdbcTemplate = jdbcTemplate;
    }
    @GetMapping("/login")
    public String showLoginPage(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/show/pages";
        }
        return "login";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user){

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userService.saveUser(user);


        Long newUserId = jdbcTemplate.queryForObject(
                "SELECT id FROM users WHERE username = ?", Long.class, user.getUsername());


        final Long roleId = 2L;

        if (newUserId != null) {
            try {

                jdbcTemplate.update(
                        "INSERT INTO users_roles (user_id, role_id) VALUES (?, ?)",
                        newUserId, roleId);
                System.out.println("User " + user.getUsername() + " successfully assigned Role ID: " + roleId);
            } catch (Exception e) {

                System.err.println("Error assigning role " + roleId + " to user " + newUserId + ": " + e.getMessage());
            }
        }

        return "redirect:/login";
    }
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }
    @GetMapping("/updateUser")
    public String showUpdateForm(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username =userDetails.getUsername();
        User user = userService.findUserByUsername(username);
        model.addAttribute("user", user);
        return "update-user"; // Thymeleaf template name
    }

    @PostMapping ("/updateUser")
    public String updateUser(@ModelAttribute("user") User user){
        userService.updateUser(user);
        return "redirect:/manageUsers";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam Long id){
        userService.deleteUser(id);
        return "redirect:/manageUsers";
    }

    @GetMapping("/manageUsers")
    public String showUsers(Model model){
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "manage-users"; // اسم ملف Thymeleaf
    }
}
