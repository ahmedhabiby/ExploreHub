package com.example.explurerhub.Service;

import com.example.explurerhub.Model.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);
    User findUserByUsername(String username);
    User findUserByID(Long id);
    Long getUserIdByUsername(String username);
    List<User> getAllUser();
    void updateUser(User user);
    void deleteUser(Long id);
}
