package com.joysistvi.recordingapp.repository;

import java.util.List;

import com.joysistvi.recordingapp.model.User;

// Repository Interface
// Defines all database operations available for User objects
public interface UserRepository {

    // --- Read Operation ---

    // Retrieve all users from the database
    List<User> getAllUsers();

    // Search users by username
    List<User> searchUsers(String keyword);

    // --- Create Operation ---

    // Insert a new user into the database
    boolean createUser(User user);

    // --- Update Operation ---

    // Update an existing user
    boolean updateUser(User user);

    // --- Delete Operation ---

    // Permanently delete a user from the database
    boolean deleteUser(int id);

    // Check if a user exists by ID
    boolean userExists(int id);

    // Authenticate a user by username and password
    User login(String username, String password);
}
