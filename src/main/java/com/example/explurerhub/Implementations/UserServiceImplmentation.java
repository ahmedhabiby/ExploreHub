package com.example.explurerhub.Implementations;

import com.example.explurerhub.Model.User;
import com.example.explurerhub.Repository.UserRepo;
import com.example.explurerhub.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplmentation implements UserService {

    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public void setUserRepo(UserRepo userRepo , PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void saveUser(User user) {
        userRepo.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        User user =userRepo.findByUsername(username);
        return user;
    }

    @Override
    public User findUserByID(Long id) {
        User user = userRepo.findById(id).orElseThrow();
        return user;
    }

    @Override
    public Long getUserIdByUsername(String username) {
        Long id=userRepo.findByUsername(username).getId();
        return id;
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public void updateUser(User user) {
        User user1=userRepo.findById(user.getId()).orElseThrow();
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user1.setPassword(passwordEncoder.encode(user.getPassword()));
        }        user1.setEmail(user.getEmail());
        user1.setUsername(user.getUsername());
        userRepo.save(user1);
    }

    @Override
    public void deleteUser(Long id) {
        User user=userRepo.findById(id).orElseThrow();
        userRepo.delete(user);
    }
}
