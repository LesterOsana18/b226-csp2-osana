package com.joysistvi.recordingapp.service;

import java.util.List;

import com.joysistvi.recordingapp.model.User;

// Service Interface
// Defines the business operations available for User objects
public interface UserService {

    // Retrieve all users
    List<User> getAllUsers();

    // Search users by username
    List<User> searchUsers(String keyword);

    // Validate and create a new user
    boolean createUser(User user);

    // Validate and update an existing user
    boolean updateUser(User user);

    // Delete a user permanently
    boolean deleteUser(int id);

    // Authenticate a user by username and password
    User login(String username, String password);
}
