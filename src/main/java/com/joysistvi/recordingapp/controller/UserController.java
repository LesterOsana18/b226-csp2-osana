package com.joysistvi.recordingapp.controller;

import java.util.List;

import com.joysistvi.recordingapp.model.User;
import com.joysistvi.recordingapp.service.UserService;

// Controller Class
// Acts as the bridge between the View and the Service layer
public class UserController {

    // Dependency Injection (Constructor Injection)
    private final UserService userService;

    // Constructor
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Retrieve and return all users
    public List<User> listUsers() {
        return userService.getAllUsers();
    }

    // Search users by name
    public List<User> searchUsers(String keyword) {
        return userService.searchUsers(keyword);
    }

    // Create a new user
    public boolean createUser(User user) {
        return userService.createUser(user);
    }

    // Update an existing user
    public boolean updateUser(User user) {
        return userService.updateUser(user);
    }

    // Permanently delete an user
    public boolean deleteUser(int id) {
        return userService.deleteUser(id);
    }

}
