package com.example.explurerhub.Controllers;

import com.example.explurerhub.Model.User;
import com.example.explurerhub.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        return "login";
    }

    @PostMapping("/saveUser")
    public String saveUser(
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            Model model) {

        // ❌ لو في Validation Errors (required / regex)
        if (result.hasErrors()) {
            return "signup";
        }

        try {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            userService.saveUser(user);

        } catch (RuntimeException ex) {
            // username موجود قبل كده
            result.rejectValue("username", "error.user", ex.getMessage());
            return "signup";
        }

        Long newUserId = jdbcTemplate.queryForObject(
                "SELECT id FROM users WHERE username = ?",
                Long.class,
                user.getUsername()
        );

        final Long roleId = 2L;
        jdbcTemplate.update(
                "INSERT INTO users_roles (user_id, role_id) VALUES (?, ?)",
                newUserId, roleId
        );

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
