package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

// Repository Implementation
// Handles all database operations related to User objects
public class UserRepositoryImpl implements UserRepository {

    // Dependency Injection (Constructor Injection)
    private final DbConnection dbConnection;

    public UserRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // ==========================================================
    // Read Operations
    // ==========================================================

    // Retrieve all users from the database
    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        String query = "SELECT * FROM users";

        try (
                Connection connection = dbConnection.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {

            while (resultSet.next()) {

                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password")
                ));

            }

        } catch (SQLException e) {

            System.out.println("Error retrieving users: " + e.getMessage());

        }

        return users;

    }

    // Search users by username
    @Override
    public List<User> searchUsers(String keyword) {

        List<User> users = new ArrayList<>();

        String query = "SELECT * FROM users WHERE username LIKE ?";

        try (
                Connection connection = dbConnection.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {

            preparedStatement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password")
                ));

            }

        } catch (SQLException e) {

            System.out.println("Error searching users: " + e.getMessage());

        }

        return users;

    }

    // ==========================================================
    // Create Operation
    // ==========================================================

    // Insert a new user into the database
    @Override
    public boolean createUser(User user) {

        String query = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (
                Connection connection = dbConnection.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {

            System.out.println("Error creating user: " + e.getMessage());
            return false;

        }

    }

    // ==========================================================
    // Update Operation
    // ==========================================================

    // Update an existing user
    @Override
    public boolean updateUser(User user) {

        String query = "UPDATE users SET username = ?, password = ? WHERE id = ?";

        try (
                Connection connection = dbConnection.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {

            System.out.println("Error updating user: " + e.getMessage());
            return false;

        }

    }

    // ==========================================================
    // Delete Operation
    // ==========================================================

    // Permanently delete a user from the database
    @Override
    public boolean deleteUser(int id) {

        String query = "DELETE FROM users WHERE id = ?";

        try (
                Connection connection = dbConnection.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {

            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {

            System.out.println("Error deleting user: " + e.getMessage());
            return false;

        }

    }

    // Check if a user exists by ID
    @Override
    public boolean userExists(int id) {

        String query = "SELECT COUNT(*) FROM users WHERE id = ?";

        try (
                Connection connection = dbConnection.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }

        } catch (SQLException e) {

            System.out.println("Error checking if user exists: " + e.getMessage());

        }

        return false;

    }

    // Authenticate a user by username and password
    @Override
    public User login(String username, String password) {

        String query =
            "SELECT id, username, password "
            + "FROM users "
            + "WHERE username = ? AND password = ?";

        try (
                Connection connection = dbConnection.connect();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    return new User(
                            resultSet.getInt("id"),
                            resultSet.getString("username"),
                            resultSet.getString("password")
                    );

                }

            }

        } catch (SQLException e) {

            System.out.println("Error during login: " + e.getMessage());

        }

        return null;
    }
}
