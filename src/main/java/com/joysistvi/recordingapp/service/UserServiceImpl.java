package com.joysistvi.recordingapp.service;

import java.util.List;

import com.joysistvi.recordingapp.model.User;
import com.joysistvi.recordingapp.repository.UserRepository;

// Service Implementation
// Handles the business logic related to User objects
public class UserServiceImpl implements UserService {

    // Dependency Injection (Constructor Injection)
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Retrieve all users
    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    // Search users by name
    @Override
    public List<User> searchUsers(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Search keyword cannot be empty.");
            return List.of();
        }

        return userRepository.searchUsers(keyword.trim());

    }

    // Validate and create a new user
    @Override
    public boolean createUser(User user) {

        if (!isValidUser(user)) {
            return false;
        }

        return userRepository.createUser(user);

    }

    // Validate and update an existing user
    @Override
    public boolean updateUser(User user) {

        if (user.getId() <= 0) {
            System.out.println("Invalid user ID.");
            return false;
        }

        if (!isValidUser(user)) {
            return false;
        }

        if (!userRepository.userExists(user.getId())) {
            System.out.println("\nUser ID does not exist.");
            return false;
        }

        return userRepository.updateUser(user);

    }

    // Delete an user permanently
    @Override
    public boolean deleteUser(int id) {

        if (id <= 0) {
            System.out.println("Invalid user ID.");
            return false;
        }

        if (!userRepository.userExists(id)) {
            System.out.println("\nUser ID does not exist.");
            return false;
        }

        return userRepository.deleteUser(id);

    }

    // Helper method to validate User object
    private boolean isValidUser(User user) {

        if (user == null) {
            System.out.println("User object cannot be null.");
            return false;
        }

        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            System.out.println("User name cannot be empty.");
            return false;
        }

        return true;

    }

}
