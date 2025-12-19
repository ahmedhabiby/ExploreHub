package com.example.explurerhub.Controllers;

import com.example.explurerhub.Model.User;
import com.example.explurerhub.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.jdbc.core.JdbcTemplate;

@Controller
@RequestMapping("/show")
public class ShowPagesController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ShowPagesController(UserService userService, PasswordEncoder passwordEncoder, JdbcTemplate jdbcTemplate) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/pages")
    public String pages(){
        return "index";
    }




}
