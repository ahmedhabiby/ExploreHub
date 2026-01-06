package com.example.explurerhub.Controllers;

import com.example.explurerhub.Dto.UserDto;
import com.example.explurerhub.Model.User;
import com.example.explurerhub.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        // Use DTO for the form
        model.addAttribute("user", new UserDto());
        return "signup";
    }

    @PostMapping("/saveUser")
    public String saveUser(
            @Valid @ModelAttribute("user") UserDto userDto, // Validate the DTO
            BindingResult result,
            Model model) {

        // 1. Validation Errors (Regex/Required)
        if (result.hasErrors()) {
            return "signup";
        }

        try {
            // 2. Map DTO to Entity
            User user = new User();
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());

            // 3. Encrypt Password
            String encodedPassword = passwordEncoder.encode(userDto.getPassword());
            user.setPassword(encodedPassword);
            user.setEnabled(true);

            // 4. Save
            userService.saveUser(user);

            // 5. Add Role
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

        } catch (RuntimeException ex) {
            // Handle duplicate username/email errors
            result.rejectValue("username", "error.user", "Username or Email already exists.");
            return "signup";
        }

        return "redirect:/login";
    }

    @GetMapping("/updateUser")
    public String showUpdateForm(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        User user = userService.findUserByUsername(username);
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/updateUser")
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
        return "manage-users";
    }
}